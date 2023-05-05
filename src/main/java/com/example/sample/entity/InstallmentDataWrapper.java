package com.example.sample.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Creating wrapper class to read JSON values of child.json.
@Getter
@Setter
public class InstallmentDataWrapper {
    @JsonProperty("data")
    private List<Installment> installments;
}
