package com.github.kyleryvn.taxservice.model;

/**
 * <p>
 *     Represents a federal sel-employed tax bracket.
 * </p>
 * <p>
 *     The term self-employment tax refers to taxes self-employed individuals and small business owners pay to the
 *     federal government to fund Medicare and Social Security. This tax is due when an individual has net earnings of $400
 *     or more in self-employment income over the course of the tax year or $108.28 or more from a tax-exempt church.
 *     Individuals who make less than these thresholds from self-employment don’t have to pay any tax.
 * </p>
 * @param isChurchEmployee If individual is a church employee.
 * @param filingStatus Individual's filing status.
 * @param taxRate Corresponding tax rate for salary range.
 * @param salaryRangeOne Minimum value in salary range.
 * @param salaryRangeTwo Maximum value in salary range.
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
