package com.example.parcial.dtos;

import com.example.parcial.validators.ValidDna;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
