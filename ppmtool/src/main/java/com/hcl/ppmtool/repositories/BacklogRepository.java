package com.hcl.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hcl.ppmtool.domain.Backlog;

public interface BacklogRepository extends CrudRepository <Backlog, Long>{
	Backlog findByProjectIdentifier(String Identifier);
}
