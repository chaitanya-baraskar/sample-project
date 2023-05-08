package com.example.sample.service;

import com.example.sample.dto.GetParentPaidAmountResponse;
import com.example.sample.entity.Installment;
import com.example.sample.entity.Parent;
import com.example.sample.dto.GetParentPaidAmount;
import com.example.sample.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ParentService {
    private static final Logger logger = Logger.getLogger(String.valueOf(ParentService.class));

    @Autowired
    ParentRepository parentRepository;

    // Retrieve all parents as per given pagination.
    public GetParentPaidAmountResponse getAllParents(Pageable pageable) {
        GetParentPaidAmountResponse response = new GetParentPaidAmountResponse();

        // Retrieve data requested as per Pageable object.
        List<GetParentPaidAmount> paidAmounts = new ArrayList<>();
        Page<Parent> parents = parentRepository.findAll(pageable);
        for (Parent parent : parents) {
            paidAmounts.add(convertFromParentToGetAllParent(parent));
        }
        logger.info("Retrieved " + paidAmounts.size() + " parent records.");
        response.setData(paidAmounts);

        // Retrieve page information
        Page<Parent> parentPage = parentRepository.findAll(pageable);
        response.setPageSize(parentPage.getPageable().getPageSize());
        response.setTotalPages(parentPage.getTotalPages());
        response.setCurrentPage(parentPage.getNumber());
        response.setTotalElements(parentPage.getTotalElements());

        return response;
    }

    // Converts Parent to dto GetParentAmount.
    // TODO: Utilize ObjectMapper to remove this redundant code.
    private GetParentPaidAmount convertFromParentToGetAllParent(Parent parent) {
        GetParentPaidAmount parentPaidAmount = new GetParentPaidAmount();
        parentPaidAmount.setId(parent.getId());
        parentPaidAmount.setSender(parent.getSender());
        parentPaidAmount.setReceiver(parent.getReceiver());
        parentPaidAmount.setTotalAmount(parent.getTotalAmount());

        int paidAmount = parent.getInstallments().stream().mapToInt(Installment::getPaidAmount).sum();
        parentPaidAmount.setTotalAmountPaid(paidAmount);

        return parentPaidAmount;
    }
}
