package com.github.kyleryvn.taxservice.model;

/**
 * <p>
 *     Represents a state tax bracket.
 * </p>
 * <p>
 *     Tax brackets consist of several salary ranges, each with a corresponding tax rate to be applied to that range. For example,
 *     Arizona has 4 tax brackets for each filing status, and
 * </p>
 * @param taxRate Rate for corresponding tax bracket. Will be converted to decimal from percent.
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
