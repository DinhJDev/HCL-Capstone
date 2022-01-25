package com.hcl.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hcl.ppmtool.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository <ProjectTask, Long> {

}
