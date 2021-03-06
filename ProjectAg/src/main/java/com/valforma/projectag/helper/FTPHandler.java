package com.valforma.projectag.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.valforma.projectag.common.util.StepSettingConstants;
import com.valforma.projectag.contracts.ObjectHolder;
import com.valforma.projectag.model.Step;
import com.valforma.projectag.model.StepSettings;
import com.valforma.projectag.service.StepSettingsService;

@Component
public class FTPHandler implements FileHandler {

	@Autowired
	StepSettingsService stepSettingsService;

	@Autowired
	TemplateParser templateParser;

	@Autowired
	ClientTranslationHelper clientTranslationHelper;

	@Override
	public List<Object[]> handle(Step step, String user, String host, int port, String password, String baseDir,
			String newDir, String processedDir, ObjectHolder objectHolder, Integer level, String ppkPath) {

		List<Object[]> objects = new ArrayList<>();

		try {
			FTPClient client = new FTPClient();
			client.connect(host, port);
			client.enterLocalPassiveMode();
			client.login(user, password);

			if (client.isConnected()) {
				System.out.println("Connection established.");
				client.changeWorkingDirectory(baseDir);
				client.changeWorkingDirectory("/" + newDir);
				FTPFile[] allFiles = client.listFiles(baseDir + "/" + newDir);
				StepSettings stepSettings = new StepSettings();
				stepSettings.setStepId(step.getId());
				stepSettings.setClientId(step.getClientId());
				List<StepSettings> stepSettingsList = stepSettingsService.getListByCriteria(stepSettings, -1, 0);
				String processOnlyOneFile = stepSettingsService.getValue(step.getClientId(), step.getId(),
						StepSettingConstants.CsvSettings.PROCESS_ONE_FILE_AT_A_TIME.name());
				if (step.getRequestTemplate() == null || step.getRequestTemplate().isEmpty()) {
					for (FTPFile lsSingleFile : allFiles) {
						String singleFile = lsSingleFile.getName();
						if (singleFile.equals(".") || singleFile.equals("..") || lsSingleFile.isDirectory())
							continue;
						InputStream singleFileInputStream = client.retrieveFileStream(baseDir + "/" + newDir+ "/" +singleFile);
						
						
						CSVFormat csvFormat = CSVFormat.newFormat(stepSettingsService.getValue(step.getClientId(),
								step.getId(), StepSettingConstants.CsvSettings.CSV_DELIMITER.name()).charAt(0));
						CSVParser csvParser = new CSVParser(new InputStreamReader(singleFileInputStream), csvFormat);
						List<CSVRecord> records = csvParser.getRecords();
						boolean isFirst = true;
						int lineNo = 1;
						for (CSVRecord csvRecord : records) {
							String[] fields = new String[csvRecord.size() + 2];
							for (int i = 0; i < csvRecord.size(); i++) {
								fields[i] = csvRecord.get(i);
							}
							fields[csvRecord.size()] = singleFile;
							fields[csvRecord.size() + 1] = "" + lineNo;

							if (isFirst) {
								String headerPresent = stepSettingsService.getValue(step.getClientId(), step.getId(),
										StepSettingConstants.CsvSettings.CSV_HEADER_PRESENT.name());
								if (headerPresent.equals("NO")) {
									objects.add(fields);
								}
							} else {
								objects.add(fields);
							}

							isFirst = false;
							lineNo++;
						}
						csvParser.close();

						client.setFileType(FTP.BINARY_FILE_TYPE);// binary files
						client.changeWorkingDirectory("./../" + processedDir);
						client.rnfr("/" + baseDir + "/" + newDir + "/" + singleFile);
						client.rnto("/" + baseDir + "/" + processedDir + "/" + singleFile);
						if (processOnlyOneFile.equals("YES")) {
							break;
						} else if (allFiles.length > 1) {
							client.connect(host, port);
							client.enterLocalPassiveMode();
							client.login(user, password);
							client.changeWorkingDirectory("/" + newDir);
						}

					}
				} else {
					clientTranslationHelper.fillTranslatorObject(step, stepSettingsList, objectHolder, level);
					String fileContent = templateParser.parse(step.getClientId().toString(),
							"step_path_template_" + step.getId().toString(), step.getRequestTemplate(), objectHolder);
					// client.put(new
					// ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8)),
					// "Sales_order.csv");

					client.setFileType(FTP.BINARY_FILE_TYPE);// binary files
					client.changeWorkingDirectory("./../" + processedDir);
					// client.rnfr("/" + baseDir + "/" + newDir + "/" +
					// singleFile);
					// client.rnto("/" + baseDir + "/" + processedDir + "/" +
					// singleFile);
					String filePrefix = stepSettingsService.getValue(step.getClientId(), step.getId(),
							"SAP_FILE_PREFIX");
					client.storeFile(filePrefix + "_" + new Date().getTime() + ".csv",
							new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8)));

				}
			}
			
			client.logout();
			client.disconnect();
		} catch (IOException e) {
			System.out.println(e);
			return new ArrayList<>();
		} catch (TranslatorNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		return objects;
	}
}
