package com.example.sample.controller;

import com.example.sample.dto.GetInstallmentInfo;
import com.example.sample.dto.GetParentPaidAmount;
import com.example.sample.service.DataLoaderService;
import com.example.sample.service.InstallmentService;
import com.example.sample.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ParentController {
    private static final Logger logger = Logger.getLogger(String.valueOf(ParentController.class));
    @Autowired
    ParentService parentService;

    @Autowired
    InstallmentService installmentService;

    @GetMapping("/parent")
    public List<GetParentPaidAmount> getParents(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        // TODO: Validate if given value exists for sort by.
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        logger.info("Received request to retrieve list of parents with pagination parameters " + pageable);
        return parentService.getAllParents(pageable);
    }

    @GetMapping("/parent/{parentId}/installments")
    public List<GetInstallmentInfo> getInstallmentsByParentId(@PathVariable int parentId) {
        logger.info("Received request to retrieve list of installments with parent ID - " + parentId);
        return installmentService.getInstallmentsByParentId(parentId);
    }

}
