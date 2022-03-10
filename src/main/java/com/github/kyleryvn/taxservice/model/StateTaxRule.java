package com.github.kyleryvn.taxservice.model;

public class StateTaxRule {
    private final double taxRate;
    private final String filingStatus;
    private final double salaryRangeOne;
    private final double salaryRangeTwo;

    public StateTaxRule() {
        this.taxRate = 0.0;
        this.filingStatus = null;
        this.salaryRangeOne = 0.0;
        this.salaryRangeTwo = 0.0;
    }

    public StateTaxRule(double taxRate, String filingStatus, double salaryRangeOne, double salaryRangeTwo) {
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
        return "StateTaxRule{" +
                "taxRate=" + taxRate +
                ", filingStatus='" + filingStatus + '\'' +
                ", salaryRangeOne=" + salaryRangeOne +
                ", salaryRangeTwo=" + salaryRangeTwo +
                '}';
    }
}
