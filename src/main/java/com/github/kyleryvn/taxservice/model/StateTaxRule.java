package com.github.kyleryvn.taxservice.model;

/**
 *
 */
public class StateTaxRule {

    /**
     * The tax rate for a specific salary range
     */
    private final double taxRate;

    /**
     * Individual's filing status. Should be one of "S", "MFJ", "MFS", "HH"
     */
    private final String filingStatus;

    /**
     * Minimum value of salary range
     */
    private final double salaryRangeOne;

    /**
     * Maximum value of salary range
     */
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
