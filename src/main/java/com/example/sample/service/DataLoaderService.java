package com.example.sample.service;

import com.example.sample.entity.Installment;
import com.example.sample.entity.InstallmentDataWrapper;
import com.example.sample.entity.Parent;
import com.example.sample.entity.ParentDataWrapper;
import com.example.sample.repository.InstallmentRepository;
import com.example.sample.repository.ParentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DataLoaderService {
    private static final Logger logger = Logger.getLogger(String.valueOf(DataLoaderService.class));
    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void dataLoader() throws IOException {
        logger.info("Attempting to load data into database.");
        ObjectMapper objectMapper = new ObjectMapper();
        ParentDataWrapper parentDataWrapper = objectMapper.readValue(ResourceUtils.getFile("classpath:parent.json"), ParentDataWrapper.class);
        parentRepository.saveAll(parentDataWrapper.getParents());
        logger.info("Saved parent data in database successfully.");

        // Load child data
        InstallmentDataWrapper installmentDataWrapper = objectMapper.readValue(ResourceUtils.getFile("classpath:child.json"), InstallmentDataWrapper.class);
        installmentRepository.saveAll(installmentDataWrapper.getInstallments());
        logger.info("Saved installments data in database successfully.");

        // Set parent-child relationships
        for (Installment installment : installmentDataWrapper.getInstallments()) {
            Optional<Parent> optionalParent = parentRepository.findById(installment.getParentId());
            optionalParent.ifPresent(parent -> {
                if (parent.getInstallments() == null) {
                    parent.setInstallments(new ArrayList<>());
                }
                parent.getInstallments().add(installment);
                installment.setParent(parent);
                parentRepository.save(parent);
            });
        }
        logger.info("Saved joined data in database successfully.");
    }
}
