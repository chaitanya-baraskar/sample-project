package com.example.sample.controller;

import com.example.sample.entity.Installment;
import com.example.sample.entity.Parent;
import com.example.sample.model.GetInstallmentInfo;
import com.example.sample.model.GetParentPaidAmount;
import com.example.sample.repository.InstallmentRepository;
import com.example.sample.service.InstallmentService;
import com.example.sample.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParentController {

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
        return parentService.getAllParents(pageable);
    }

    @GetMapping("/parent/{parentId}/installments")
    public List<GetInstallmentInfo> getInstallmentsByParentId(@PathVariable int parentId) {
        return installmentService.getInstallmentsByParentId(parentId);
    }

}
