package com.example.sample.service;

import com.example.sample.entity.Installment;
import com.example.sample.entity.Parent;
import com.example.sample.model.GetParentPaidAmount;
import com.example.sample.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentService {

    @Autowired
    ParentRepository parentRepository;

    public List<GetParentPaidAmount> getAllParents(Pageable pageable) {
        List<GetParentPaidAmount> paidAmounts = new ArrayList<>();
        Page<Parent> parents = parentRepository.findAll(pageable);
        for (Parent parent : parents) {
            paidAmounts.add(convertFromParentToGetAllParent(parent));
        }
        return paidAmounts;
    }

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
