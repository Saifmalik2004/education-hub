package com.learnwithsaif.educationHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learnwithsaif.educationHub.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}