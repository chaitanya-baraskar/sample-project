package com.example.sample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetParentPaidAmount {
    private int id;
    private String sender;
    private String receiver;
    private int totalAmount;
    private int totalAmountPaid;
}
