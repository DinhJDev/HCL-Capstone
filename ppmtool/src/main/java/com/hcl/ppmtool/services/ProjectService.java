package com.hcl.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository pRepo;
	
	public Project saveOrUpdateProject(Project project) {
		
		
		
		return pRepo.save(project);
	}
}
