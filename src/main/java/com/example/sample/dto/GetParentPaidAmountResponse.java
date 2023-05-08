package com.example.sample.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetParentPaidAmountResponse extends PageInfo {
    List<GetParentPaidAmount> data;
}
