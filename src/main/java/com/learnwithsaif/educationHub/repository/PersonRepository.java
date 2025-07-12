package com.learnwithsaif.educationHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnwithsaif.educationHub.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    
    Person readByEmail(String email);
    
}
