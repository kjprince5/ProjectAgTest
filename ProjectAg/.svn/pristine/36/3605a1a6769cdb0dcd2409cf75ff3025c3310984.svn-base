package com.valforma.projectag.contracts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ObjectHolder {
	
	public ObjectHolder() {
		jobRunningId= UUID.randomUUID().toString();
	}
	
	String jobRunningId;
	
	boolean isDryRun;
	
	boolean runAgain=false;
	
	String startSequence;
	
	String endSequence;
	
	String batchSize;
	
	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	Date currentDate= new Date();
	
	Date lastScheduledStartTime;
	
	Date nextRunTime;

	Map<String, Config> configs = new HashMap<>();


	public Map<String, Config> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, Config> configs) {
		this.configs = configs;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getJobRunningId() {
		return jobRunningId;
	}

	public void setJobRunningId(String jobRunningId) {
		this.jobRunningId = jobRunningId;
	}

	public boolean isDryRun() {
		return isDryRun;
	}

	public void setDryRun(boolean isDryRun) {
		this.isDryRun = isDryRun;
	}

	public Date getLastScheduledStartTime() {
		return lastScheduledStartTime;
	}

	public void setLastScheduledStartTime(Date lastScheduledStartTime) {
		this.lastScheduledStartTime = lastScheduledStartTime;
	}

	public Date getNextRunTime() {
		return nextRunTime;
	}

	public void setNextRunTime(Date nextRunTime) {
		this.nextRunTime = nextRunTime;
	}
	
	public boolean isRunAgain() {
		return runAgain;
	}

	public void setRunAgain(boolean runAgain) {
		this.runAgain = runAgain;
	}

	public String getStartSequence() {
		return startSequence;
	}

	public void setStartSequence(String startSequence) {
		this.startSequence = startSequence;
	}

	public String getEndSequence() {
		return endSequence;
	}

	public void setEndSequence(String endSequence) {
		this.endSequence = endSequence;
	}

	
}
