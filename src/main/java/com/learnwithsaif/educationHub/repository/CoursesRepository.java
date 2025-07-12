package com.learnwithsaif.educationHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.learnwithsaif.educationHub.model.Courses;

@Repository

@RepositoryRestResource(exported=false)
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}