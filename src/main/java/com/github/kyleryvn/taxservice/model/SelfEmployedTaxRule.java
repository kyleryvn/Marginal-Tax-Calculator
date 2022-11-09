package com.github.kyleryvn.taxservice.model;

/**
 * <p>
 *     Represents a federal self-employed tax bracket.
 * </p>
 * <p>
 *     A self-employed person does not work for a specific employer who pays them a consistent salary or wage.
 *     Self-employed individuals, or independent contractors, earn income by contracting with a trade or business directly.
 * </p>
 * <p>
 *     The term self-employment tax refers to taxes self-employed individuals and small business owners pay to the
 *     federal government to fund Medicare and Social Security. This tax is due when an individual has net earnings of $400
 *     or more in self-employment income over the course of the tax year or $108.28 or more from a tax-exempt church.
 *     Individuals who make less than these thresholds from self-employment don’t have to pay any tax.
 * </p>
 * @param isChurchEmployee If taxpayer is a church employee.
 * @param filingStatus Taxpayer's filing status.
 * @param taxRate Corresponding tax rate for salary range.
 * @param salaryRangeOne Minimum value in salary range.
 * @param salaryRangeTwo Maximum value in salary range.
 *
 * @author Kyle Schoenhardt
 * @version 1.0.0
 * @since 2022-01-12
 */
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
