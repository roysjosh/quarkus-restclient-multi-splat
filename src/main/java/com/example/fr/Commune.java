package com.example.fr;

import java.util.Set;

import lombok.Data;

@Data
public class Commune {
    String code;
    String nom;
    Set<String> codesPostaux;
    String codeDepartment;
    String codeRegion;
    // departement
    // region
    Integer population;
    Float surface;
}