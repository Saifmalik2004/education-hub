package com.learnwithsaif.educationHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.learnwithsaif.educationHub.model.Contact;
import com.learnwithsaif.educationHub.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;


@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    // @PostMapping(value = "/saveMsg")
    // public ModelAndView saveMessage(
    //     @RequestParam String name,
    //     @RequestParam String mobileNum,
    //     @RequestParam String email,
    //     @RequestParam String subject,
    //     @RequestParam String message
    // ) {
    //     System.out.println("Name : " + name);
    //     System.out.println("Mobile Number : " + mobileNum);
    //     System.out.println("Email : " + email);
    //     System.out.println("Subject : " + subject);
    //     System.out.println("Message : " + message);
    //     return new ModelAndView("redirect:/contact");
    // }

    
    @PostMapping(value = "/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }

        contactService.saveMessageDetails(contact);

        return "redirect:/contact";
    }


    @RequestMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model,
            @PathVariable(name = "pageNum") int pageNum,@RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    

     @GetMapping(value = "/closeMsg")
    public String closeMsg(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }


}
