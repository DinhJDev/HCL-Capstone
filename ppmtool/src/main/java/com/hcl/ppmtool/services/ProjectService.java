package com.hcl.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ppmtool.domain.Backlog;
import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.exceptions.ProjectIdException;
import com.hcl.ppmtool.repositories.BacklogRepository;
import com.hcl.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository pRepo;
	@Autowired
	private BacklogRepository bRepo;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId()!=null) {
				project.setBacklog(bRepo.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			return pRepo.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = pRepo.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project ID '" + projectId +"' does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects() {
		return pRepo.findAll();
	}
	
	public void deleteProjectBytIdentifier(String projectId) {
		Project project = pRepo.findByProjectIdentifier(projectId);
		
		if(project ==null) {
			throw new ProjectIdException("Cannot delete project with '" + projectId +"' Project ID, because it does not exist");
		}
		
		pRepo.delete(project);
	}
}
