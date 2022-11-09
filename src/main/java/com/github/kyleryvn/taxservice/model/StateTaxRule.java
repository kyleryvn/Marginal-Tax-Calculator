package com.github.kyleryvn.taxservice.model;

/**
 * <p>
 *     Represents a state tax bracket.
 * </p>
 * @param taxRate Rate for corresponding tax bracket
 * @param filingStatus Individual's filing status
 * @param salaryRangeOne Minimum value of salary range
 * @param salaryRangeTwo Maximum value of salary range
 */
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
