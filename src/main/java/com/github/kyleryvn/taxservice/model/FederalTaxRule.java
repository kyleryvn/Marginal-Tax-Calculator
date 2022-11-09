package com.github.kyleryvn.taxservice.model;

public record FederalTaxRule(double taxRate, String filingStatus, double salaryRangeOne, double salaryRangeTwo) {

    @Override
    public String toString() {
        return "FederalTaxRule{" +
                "taxRate=" + taxRate +
                ", filingStatus='" + filingStatus + '\'' +
                ", salaryRangeOne=" + salaryRangeOne +
                ", salaryRangeTwo=" + salaryRangeTwo +
                '}';
    }
}
