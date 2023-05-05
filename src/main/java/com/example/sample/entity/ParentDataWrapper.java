package com.example.sample.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ParentDataWrapper {
    @JsonProperty("data")
    private List<Parent> parents;
}
