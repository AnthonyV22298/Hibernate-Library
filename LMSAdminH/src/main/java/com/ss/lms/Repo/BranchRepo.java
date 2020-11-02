package com.ss.lms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ss.lms.Entity.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer>{
	@Query(" FROM Branch where branchName =:branchName")
	public List<Branch> readBranchesByTitle(@Param("branchName") String branchName);
}
