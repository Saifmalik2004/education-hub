package com.learnwithsaif.educationHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnwithsaif.educationHub.constants.EducationHubConstants;
import com.learnwithsaif.educationHub.model.Person;
import com.learnwithsaif.educationHub.model.Roles;
import com.learnwithsaif.educationHub.repository.PersonRepository;
import com.learnwithsaif.educationHub.repository.RolesRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

      @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;

        Roles role = rolesRepository.getByRoleName(EducationHubConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}