package com.jalin.myapp.jalin_myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelFormatBank {

    private String sortName;
    private String mp;
    private int codeBank;
    private String bankName;
    private String status;
}
