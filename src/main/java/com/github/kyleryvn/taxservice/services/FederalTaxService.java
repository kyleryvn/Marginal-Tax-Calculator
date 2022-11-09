package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.model.FederalTaxRule;
import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * <p>
 *     This class calculates federal taxes due.
 * </p>
 * <p>
 *     Text
 * </p>
 */
public class FederalTaxService {

    /**
     * {@link List} containing federal tax brackets
     */
    private static final List<FederalTaxRule> fedTaxRules;

    /**
     * {@link List} containing federal self-employment tax brackets
     */
    private static final List<SelfEmployedTaxRule> selfEmployedTaxRules;

    static {
        Gson gson = new Gson();

        // Create FederalTaxRule objects from JSON data
        Function<String, FederalTaxRule> convertTaxes = json -> gson.fromJson(json, FederalTaxRule.class);
        fedTaxRules = ResourceUtility.getResourceAsList("docs/fedTaxRules.txt", 0, convertTaxes);

        // Create SelfEmployedTaxRule object from JSON data
        Function<String, SelfEmployedTaxRule> convertSelfTaxes = json -> gson.fromJson(json, SelfEmployedTaxRule.class);
        selfEmployedTaxRules = ResourceUtility.getResourceAsList("docs/fedSelfEmployedTaxRules.txt", 0, convertSelfTaxes);
    }

    /**
     * <p>
     *     Calculates federal taxes due.
     * </p>
     * <p>
     *     kk
     * </p>
     * @param filingStatus Taxpayer's filing status
     * @param income Taxpayer's gross annual income
     * @return Federal taxes due
     */
    public static double getFederalTaxDue(String filingStatus, double income) {
        ToDoubleFunction<FederalTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.salaryRangeTwo(), income);
            return (rangeTwo - taxRule.salaryRangeOne()) * taxRule.taxRate();
        };

        return fedTaxRules.stream()
                .filter(taxRule -> taxRule.filingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> income > taxRule.salaryRangeOne())
                .mapToDouble(map)
                .sum();
    }

    /**
     * <p>
     *     yy
     * </p>
     * @param filingStatus Taxpayer's filing status
     * @param income Taxpayer's gross annual income
     * @param isChurchEmployee If taxpayer is church employee or not
     * @param claimDeduction If taxpayer claimed deduction or not
     * @return Federal self-employment taxes due
     */
    public static double getFederalSelfEmploymentTaxDue(String filingStatus, double income, boolean isChurchEmployee,
                                                        boolean claimDeduction) {
        double taxableIncome = income * 0.9235;

        ToDoubleFunction<SelfEmployedTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.salaryRangeTwo(), taxableIncome);
            return rangeTwo * taxRule.taxRate();
        };

        return selfEmployedTaxRules.stream()
                .filter(taxRule -> taxRule.isChurchEmployee() == isChurchEmployee)
                .filter(taxRule -> taxRule.filingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> taxableIncome > taxRule.salaryRangeOne())
                .mapToDouble(map)
                .sum();
    }

    /**
     * <p>
     *     Calculates the taxpayer's effective rate.
     * </p>
     * <p>
     *     A taxpayer’s tax bracket does not necessarily reflect the percentage of their income that they will pay in
     *     taxes; the term for this is the effective tax rate. The term effective tax rate refers to the percent of income
     *     that an individual or corporation owes/pays in taxes. The effective tax rate for individuals is the average
     *     rate at which their earned income, such as wages, and unearned income, such as stock dividends, are taxed.
     *     The effective tax rate for a corporation is the average rate at which its pre-tax profits are taxed, while
     *     the statutory tax rate is the legal percentage established by law.
     * </p>
     * @param totalTaxes Taxpayer's total taxes due
     * @param income Taxpayer's gross annual income
     * @return Taxpayer's effective rate
     */
    public static double getEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}