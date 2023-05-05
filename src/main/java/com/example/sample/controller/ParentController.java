package com.example.sample.controller;

import com.example.sample.model.GetParentPaidAmount;
import com.example.sample.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParentController {

    @Autowired
    ParentService parentService;

    @GetMapping("/parent")
    public List<GetParentPaidAmount> getParents(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return parentService.getAllParents(pageable);
    }

}
