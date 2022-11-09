package com.github.kyleryvn.taxservice.model;

/**
 * <p>
 *     Represents a state tax bracket.
 * </p>
 * <p>
 *     A tax bracket refers to a range of incomes subject to a certain income tax rate. Tax brackets result in a progressive
 *     tax system, in which taxation progressively increases as an individual’s income grows. Low incomes fall into tax
 *     brackets with relatively low income tax rates, while higher earnings fall into brackets with higher rates.
 * </p>
 * @param taxRate Rate for corresponding tax bracket. Will be converted to decimal from percent.
 * @param filingStatus Taxpayer's filing status
 * @param salaryRangeOne Minimum value of salary range
 * @param salaryRangeTwo Maximum value of salary range
 *
 * @author Kyle Schoenhardt
 * @version 2.0.0
 * @since 2022-11-08
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
