package com.varchenko.productservice.entity;

import lombok.Data;

@Data
public class Inventory {
    private String uniqId;
    private int availability;
}
