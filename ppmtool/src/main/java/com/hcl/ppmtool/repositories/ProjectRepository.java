package com.hcl.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hcl.ppmtool.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	@Override
	Iterable<Project> findAllById(Iterable<Long> iterable);
}
