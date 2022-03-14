package com.github.kyleryvn.taxservice.model;

public class SelfEmployedTaxRule {
    private final boolean isChurchEmployee;
    private final String filingStatus;
    private final double taxRate;
    private final double salaryRangeOne;
    private final double salaryRangeTwo;

    public SelfEmployedTaxRule(boolean isChurchEmployee, String filingStatus, double taxRate, double salaryRangeOne, double salaryRangeTwo) {
        this.isChurchEmployee = isChurchEmployee;
        this.filingStatus = filingStatus;
        this.taxRate = taxRate;
        this.salaryRangeOne = salaryRangeOne;
        this.salaryRangeTwo = salaryRangeTwo;
    }

    public boolean isChurchEmployee() {
        return isChurchEmployee;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getSalaryRangeOne() {
        return salaryRangeOne;
    }

    public double getSalaryRangeTwo() {
        return salaryRangeTwo;
    }

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
