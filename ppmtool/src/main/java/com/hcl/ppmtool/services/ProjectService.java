package com.hcl.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.repositories.ProjectRepository;

public class ProjectService {
	@Autowired
	private ProjectRepository pRepo;
	
	public Project saveOrUpdateProject(Project project) {
		
		
		
		return pRepo.save(project);
	}
}
