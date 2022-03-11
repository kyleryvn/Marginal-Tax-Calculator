package com.github.kyleryvn.taxservice.services;

public class TaxService {

    public static double getEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}
