package com.github.kyleryvn.taxservice.model;

public record SelfEmployedTaxRule(boolean isChurchEmployee, String filingStatus, double taxRate, double salaryRangeOne,
                                  double salaryRangeTwo) {

    @Override
    public String toString() {
        return "SelfEmployedTaxRule{" +
                "isChurchEmployee=" + isChurchEmployee +
                ", filingStatus='" + filingStatus + '\'' +
                ", taxRate=" + taxRate +
                ", salaryRangeOne=" + salaryRangeOne +
                ", salaryRangeTwo=" + salaryRangeTwo +
                '}';
    }
}
