package com.mx.aleon.aemployee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.aleon.aemployee.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	Optional<Job> findByTitle(String jobTitle);

}
