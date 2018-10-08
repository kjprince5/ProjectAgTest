package com.valforma.projectag.rest;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valforma.projectag.common.util.JobException;
import com.valforma.projectag.contracts.Config;
import com.valforma.projectag.contracts.ObjectHolder;
import com.valforma.projectag.model.AsyncRequest;
import com.valforma.projectag.model.AsyncRequest.Status;
import com.valforma.projectag.model.IntegrationInstanceFailure;
import com.valforma.projectag.model.JobDetail;
import com.valforma.projectag.model.Step;
import com.valforma.projectag.service.AsyncRequestService;
import com.valforma.projectag.service.GenericCron;
import com.valforma.projectag.service.IntegrationInstanceFailureService;
import com.valforma.projectag.service.JobDetailService;
import com.valforma.projectag.service.StepService;

@RestController
@RequestMapping("{clientCode}/invoke")
public class JobInvoker {

	@Autowired
	GenericCron genericCron;

	@Autowired
	JobDetailService jobDetailService;

	@Autowired
	StepService stepService;

	@Autowired
	AsyncRequestService asyncRequestService;

	@Autowired
	IntegrationInstanceFailureService integrationInstanceFailureService;

	@Autowired

	@RequestMapping()
	public String invoke() {
		genericCron.startCron();
		return "ok";
	}

	@RequestMapping(value = "{id}")
	public String invoke(@PathVariable("clientCode") String clientCode, @PathVariable("id") BigInteger id) throws Exception {
		System.out.println("Cron Started");

		JobDetail jobDetail = new JobDetail();
		jobDetail.setId(id);
		jobDetail.setStatus(JobDetail.Status.STOPPED);

		List<JobDetail> jobDetails = jobDetailService.getListByCriteria(jobDetail, -1, 0);
		genericCron.processJobDetails(jobDetails);
		genericCron.startCron();
		return "ok";
	}

	@RequestMapping(value = "retry/{id}")
	public ResponseEntity<Void> retry(@PathVariable("clientCode") String clientCode,
			@PathVariable("id") BigInteger id) throws Exception {
		IntegrationInstanceFailure integrationInstanceFailure = integrationInstanceFailureService.get(id);
		Step olderSep = stepService.get(integrationInstanceFailure.getStepId());
		JobDetail jobDetail = jobDetailService.get(olderSep.getFailureJobDetailId());
		Step stepSeearch = new Step();
		stepSeearch.setJobDetailId(olderSep.getFailureJobDetailId());
		stepSeearch.setParentNull(true);
		Map<String, Boolean> orderBy = new HashMap<>();
		orderBy.put("sequence", Boolean.TRUE);
		List<Step> steps = stepService.getListByCriteria(stepSeearch, 0, 10, orderBy, false, null);
		ObjectHolder objectHolder = new ObjectHolder();
		objectHolder.getConfigs().put("0", new Config());
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectHolder.getConfigs().get("0").getValues().put("0",
					objectMapper.readValue(integrationInstanceFailure.getNextRequestJson(), Object.class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectHolder.getConfigs().get("0").getValues().put("integrationInstanceFailureId", id);
		objectHolder.getConfigs().get("0").getValues().put("failureStepId", olderSep.getFailureStepId());
		for (Step step : steps) {
			try {
				genericCron.processStep(jobDetail, step, objectHolder, 0);
			} catch (JobException e) {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "test", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public String test() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<Response>\r\n    <Result>1</Result>\r\n    <Reason/>\r\n</Response>";
	}

	@RequestMapping(value = "job-async/{id}")
	public ResponseEntity<Void> jobAsync(@PathVariable("clientCode") String clientCode,
			@PathVariable("id") BigInteger jobId, @RequestBody LinkedHashMap<?, ?> body) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		AsyncRequest asyncRequest = new AsyncRequest();
		try {
			asyncRequest.setRequestJson(objectMapper.writeValueAsString(body));
		} catch (JsonProcessingException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		asyncRequest.setJobDetailId(jobId);
		asyncRequest.setStatus(Status.PENDING);
		asyncRequest.setCreationDate(new Date());
		asyncRequest.setLastUpdateDate(new Date());
		asyncRequestService.save(asyncRequest);
		genericCron.processAsyncRequest(asyncRequest, jobId, body);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "dry-run/{id}/{stepId}")
	public ResponseEntity<ObjectHolder> dryRun(@PathVariable("clientCode") String clientCode,
			@PathVariable("stepId") BigInteger stepId, @PathVariable("id") BigInteger jobId,
			@RequestParam(value = "uniqueKey", required = false) String uniqueKey,
			@ModelAttribute LinkedHashMap<?, ?> body) throws Exception {
		Step stepSeearch = new Step();
		stepSeearch.setJobDetailId(jobId);
		stepSeearch.setParentNull(true);
		Map<String, Boolean> orderBy = new HashMap<>();
		orderBy.put("sequence", Boolean.TRUE);
		JobDetail jobDetail = jobDetailService.get(jobId);
		List<Step> steps = stepService.getListByCriteria(stepSeearch, 0, 10, orderBy, false, null);
		ObjectHolder objectHolder = new ObjectHolder();
		objectHolder.setDryRun(true);
		objectHolder.setJobRunningId(UUID.randomUUID().toString());
		objectHolder.getConfigs().put("0", new Config());
		objectHolder.getConfigs().get("0").getValues().put("0", body);
		objectHolder.getConfigs().get("0").getValues().put("dryRunStepId", body);
		if (uniqueKey != null) {
			objectHolder.getConfigs().get("0").getValues().put("dryRunStepUniqueId", uniqueKey);
		}
		for (Step step : steps) {
			try {
				genericCron.processStep(jobDetail, step, objectHolder, 0);
			} catch (JobException e) {
				e.printStackTrace();
				return new ResponseEntity<ObjectHolder>(objectHolder, HttpStatus.OK);
			}
		}
		return new ResponseEntity<ObjectHolder>(objectHolder, HttpStatus.OK);

	}

	@RequestMapping(value = "job-sync/{id}")
	public ResponseEntity<Object> jobSync(@PathVariable("clientCode") String clientCode,
			@PathVariable("id") BigInteger jobId, @RequestBody LinkedHashMap<?, ?> body) throws Exception {
		Step stepSeearch = new Step();
		stepSeearch.setJobDetailId(jobId);
		stepSeearch.setParentNull(true);
		Map<String, Boolean> orderBy = new HashMap<>();
		orderBy.put("sequence", Boolean.TRUE);
		JobDetail jobDetail = jobDetailService.get(jobId);
		List<Step> steps = stepService.getListByCriteria(stepSeearch, 0, 10, orderBy, false, null);
		ObjectHolder objectHolder = new ObjectHolder();
		objectHolder.getConfigs().put("0", new Config());
		objectHolder.getConfigs().get("0").getValues().put("0", body);
		Object lastResponse = null;
		for (Step step : steps) {
			try {
				lastResponse = genericCron.processStep(jobDetail, step, objectHolder, 0);
			} catch (JobException e) {
				return new ResponseEntity<Object>(e.getBody(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>(lastResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "retryAll")
	public ResponseEntity<List<IntegrationInstanceFailure>> retryAll(@PathVariable("clientCode") String clientCode,
			@RequestParam(required = false) List<BigInteger> stepIdList) {
		for (BigInteger stepId : stepIdList) {
			try {
				retry(clientCode, stepId);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
