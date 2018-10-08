package com.valforma.projectag.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.valforma.projectag.contracts.ObjectHolder;
import com.valforma.projectag.model.Step;

@Component
public interface FileHandler {
	public List<Object[]> handle(Step step, String user, String host, int port, String password, String baseDir,
			String newDir, String processedDir, ObjectHolder objectHolder, Integer level, String ppkPath);

}
