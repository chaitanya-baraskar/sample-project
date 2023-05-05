package com.example.sample.service;

import com.example.sample.entity.Installment;
import com.example.sample.model.GetInstallmentInfo;
import com.example.sample.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentService {

    @Autowired
    InstallmentRepository installmentRepository;

    public List<GetInstallmentInfo> getInstallmentsByParentId(int parentId) {
        List<Installment> installments = installmentRepository.findByParentId(parentId);
        List<GetInstallmentInfo> installmentInfoList = new ArrayList<>();
        for (Installment installment : installments) {
            installmentInfoList.add(convertInstallmentToGetInstallmentInfo(installment));
        }
        return installmentInfoList;
    }

    // TODO: Utilize ObjectMapper here.
    private GetInstallmentInfo convertInstallmentToGetInstallmentInfo(Installment installment) {
        GetInstallmentInfo getInstallmentInfo = new GetInstallmentInfo();
        getInstallmentInfo.setId(installment.getId());
        getInstallmentInfo.setSender(installment.getParent().getSender());
        getInstallmentInfo.setReceiver(installment.getParent().getReceiver());
        getInstallmentInfo.setTotalAmount(installment.getParent().getTotalAmount());
        getInstallmentInfo.setPaidAmount(installment.getPaidAmount());
        return getInstallmentInfo;
    }
}
