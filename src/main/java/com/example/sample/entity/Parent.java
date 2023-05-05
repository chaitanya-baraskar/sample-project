package com.example.sample.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    private int id;
    private String sender;
    private String receiver;
    private int totalAmount;

    // TODO: this could be Lazy loading but as I am loading data at start hence need to use Eager.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Installment> installments;

}
