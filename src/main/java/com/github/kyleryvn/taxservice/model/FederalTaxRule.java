package com.github.kyleryvn.taxservice.model;

public class FederalTaxRule {
    private final double taxRate;
    private final String filingStatus;
    private final double salaryRangeOne;
    private final double salaryRangeTwo;

    public FederalTaxRule(double taxRate, String filingStatus, double salaryRangeOne, double salaryRangeTwo) {
        this.taxRate = taxRate;
        this.filingStatus = filingStatus;
        this.salaryRangeOne = salaryRangeOne;
        this.salaryRangeTwo = salaryRangeTwo;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public double getSalaryRangeOne() {
        return salaryRangeOne;
    }

    public double getSalaryRangeTwo() {
        return salaryRangeTwo;
    }

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
