package com.learnwithsaif.educationHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnwithsaif.educationHub.model.Person;
import com.learnwithsaif.educationHub.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardController {


    @Autowired
    PersonRepository personRepository;

     @Autowired
    Environment environment; 
   

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication,HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null != person.getHubClass() && null != person.getHubClass().getName()){
            model.addAttribute("enrolledClass", person.getHubClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }

    

}