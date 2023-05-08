package com.example.sample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;
}
