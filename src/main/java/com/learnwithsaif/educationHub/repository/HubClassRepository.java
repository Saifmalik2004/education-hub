package com.learnwithsaif.educationHub.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learnwithsaif.educationHub.model.HubClass;

@Repository
public interface HubClassRepository extends JpaRepository<HubClass, Integer> {

}
