package com.hcl.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.services.MapValidationErrorService;
import com.hcl.ppmtool.services.ProjectService;


@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService pService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;	
		
		Project project1 = pService.saveOrUpdateProject(project);		
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable String id) {
		Project project = pService.findProjectByIdentifier(id);
		
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){return pService.findAllProjects();}
	
	@PutMapping("/{id}")
	public void updateProject(@PathVariable String id, @RequestBody Project project) {
		Project p = pService.findProjectByIdentifier(id.toUpperCase());
		p.setProjectName(project.getProjectName());
		p.setDescription(project.getDescription());
		pService.saveOrUpdateProject(p);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable String id) {
		pService.deleteProjectBytIdentifier(id.toUpperCase());
		
		return new ResponseEntity<String>("Project with ID: '" + id.toUpperCase() +"' was deleted", HttpStatus.OK);
	}
}
