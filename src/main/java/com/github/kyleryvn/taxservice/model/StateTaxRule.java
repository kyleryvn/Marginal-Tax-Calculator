package com.github.kyleryvn.taxservice.model;

public record StateTaxRule(double taxRate, String filingStatus, double salaryRangeOne, double salaryRangeTwo) {

    @Override
    public String toString() {
        return "StateTaxRule{" +
                "taxRate=" + taxRate +
                ", filingStatus='" + filingStatus + '\'' +
                ", salaryRangeOne=" + salaryRangeOne +
                ", salaryRangeTwo=" + salaryRangeTwo +
                '}';
    }
}
