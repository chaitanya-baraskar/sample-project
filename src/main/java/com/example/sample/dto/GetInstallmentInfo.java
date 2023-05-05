package com.example.sample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInstallmentInfo {
    private int id;
    private String sender;
    private String receiver;
    private int totalAmount;
    private int paidAmount;
}
