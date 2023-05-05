package com.example.sample.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


// For the sake of better understanding. I have renamed this from Child to Installment
@Entity
@Getter
@Setter
public class Installment {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Parent parent;

    @JsonProperty("parentId")
    private int parentId;

    private int paidAmount;

}
