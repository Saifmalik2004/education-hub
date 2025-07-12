package com.learnwithsaif.educationHub.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learnwithsaif.educationHub.config.EduHubProps;
import com.learnwithsaif.educationHub.constants.EducationHubConstants;
import com.learnwithsaif.educationHub.model.Contact;
import com.learnwithsaif.educationHub.repository.ContactRepository;




@Service
public class ContactService{
   
    @Autowired
    private ContactRepository contactRepository;
    

    @Autowired
    private EduHubProps eduhub;

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EducationHubConstants.OPEN);
        
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
  public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = eduhub.getPageSize();
        if(null!=eduhub.getContact() && null!=eduhub.getContact().get("pageSize")){
            pageSize = Integer.parseInt(eduhub.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(
                EducationHubConstants.OPEN,pageable);
        return msgPage;
    }

  
    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatusNative(EducationHubConstants.CLOSE,contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }

}


