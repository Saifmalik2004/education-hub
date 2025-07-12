package com.learnwithsaif.educationHub.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EduhubInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> eazyMap = new HashMap<String, String>();
        eazyMap.put("App Name", "Eduhub");
        eazyMap.put("App Description", "Education Hub Web Application for Students and Admin");
        eazyMap.put("App Version", "1.0.0");
        eazyMap.put("Contact Email", "info@eduhub.com");
        eazyMap.put("Contact Mobile", "+1(21) 673 4587");
        builder.withDetail("eduhub-info", eazyMap);
    }

}