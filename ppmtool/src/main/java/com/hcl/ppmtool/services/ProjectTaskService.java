package com.hcl.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ppmtool.domain.Backlog;
import com.hcl.ppmtool.domain.ProjectTask;
import com.hcl.ppmtool.repositories.BacklogRepository;
import com.hcl.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository bRepo;
	@Autowired
	private ProjectTaskRepository pTRepo;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		Backlog backlog = bRepo.findByProjectIdentifier(projectIdentifier);
		projectTask.setBacklog(backlog);
		Integer BacklogSequence = backlog.getPTSequence();
		BacklogSequence++;
		
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		if(projectTask.getPriority()==0||projectTask.getPriority()==null) {
			projectTask.setPriority(3);
		}
		if(projectTask.getStatus()==""||projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		return pTRepo.save(projectTask);
	}
}
