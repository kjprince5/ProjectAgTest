package com.valforma.projectag.common.util;

import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component
public class AGUtil {

	public boolean isThisPathContainsNoFiles(String path) {
		String[] parameters = path.split("\\|");
		String protocol = parameters[0];
		String user = parameters[1];
		String password = parameters[2];
		String host = parameters[3];
		int port = new Integer(parameters[4]);
		String baseDir = parameters[5];
		String dir = parameters[6];
		if ("FTP".equals(protocol))
			return isFtpPathContainsNoFile(user, password, host, port, baseDir, dir);
		return isSftpPathContainsNoFile(user, password, host, port, baseDir, dir);
	}

	private boolean isFtpPathContainsNoFile(String user, String password, String host, int port, String baseDir,
			String dirArr) {
		boolean noFile = false;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(host, port);
			ftpClient.enterLocalPassiveMode();
			ftpClient.login(user, password);
			if (ftpClient.isConnected()) {
				String[] folders = dirArr.split(",");
				noFile = areAllFtpFoldersContainNoFile(folders, baseDir, ftpClient);
				ftpClient.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noFile;
	}

	public boolean areAllFtpFoldersContainNoFile(String[] folders, String baseDir, FTPClient ftpClient) {
		int totalFileCount = 0;
		FTPFile[] ftpFiles = null;
		try {
			for (String folder : folders) {
				ftpFiles = ftpClient.listFiles(baseDir + "/" + folder);
				totalFileCount += getFtpTotalFileCount(ftpFiles);
			}
		} catch (Exception e) {
			totalFileCount = -1;
			e.printStackTrace();
		}
		return totalFileCount == 0;
	}

	public int getFtpTotalFileCount(FTPFile[] ftpFiles) {
		int totalFiles = 0;
		if (ftpFiles != null) {
			for (FTPFile aFile : ftpFiles) {
				if (aFile.isFile()) {
					totalFiles++;
				}
			}
		}
		return totalFiles;
	}

	// SFTP Methods

	private boolean isSftpPathContainsNoFile(String user, String password, String host, int port, String baseDir,
			String dirArr) {
		boolean noFile = false;
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			sftpChannel.cd(baseDir);
			String[] folders = dirArr.split(",");
			noFile = areAllSftpFoldersContainNoFile(folders, baseDir, sftpChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noFile;
	}

	private boolean areAllSftpFoldersContainNoFile(String[] folders, String baseDir, ChannelSftp sftpChannel) {

		int totalFileCount = 0;
		Vector<ChannelSftp.LsEntry> allFiles = null;
		try {
			for (String folder : folders) {
				sftpChannel.cd(baseDir + "/" + folder);
				allFiles = sftpChannel.ls(".");
				totalFileCount += getSftpTotalFileCount(allFiles);
			}
		} catch (Exception e) {
			totalFileCount = -1;
			e.printStackTrace();
		}
		return totalFileCount == 0;
	}

	private int getSftpTotalFileCount(Vector<ChannelSftp.LsEntry> allFiles) {
		int totalFiles = 0;
		if (allFiles != null) {
			for (ChannelSftp.LsEntry lsSingleFile : allFiles) {
				if (!lsSingleFile.getAttrs().isDir())
					totalFiles++;
			}
		}
		return totalFiles;
	}

}
