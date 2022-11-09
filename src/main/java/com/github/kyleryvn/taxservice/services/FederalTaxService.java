package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.model.FederalTaxRule;
import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * <h3>Federal Tax Service</h3>
 * <p>
 *      This class calculates the federal tax brackets.
 * </p>
 *
 * @author Kyle Schoenhardt
 * @version 1.1.0
 * @since 2022-11-08
 */

public class FederalTaxService {
    /**
     * List containing federal tax brackets
     */
    private static final List<FederalTaxRule> fedTaxRules;

    /**
     * List containing federal tax brackets for self-employed individuals
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
     * Calculates federal taxes due by utilizing Stream library to filter filing status through tax brackets and
     * applying corresponding rate.
     *
     * @param filingStatus Individual's filing status
     * @param income Individual's income
     * @return Federal tax due
     */
    public static double getFederalTaxDue(String filingStatus, double income) {
        ToDoubleFunction<FederalTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.getSalaryRangeTwo(), income);
            return (rangeTwo - taxRule.getSalaryRangeOne()) * taxRule.getTaxRate();
        };

        return fedTaxRules.stream()
                .filter(taxRule -> taxRule.getFilingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> income > taxRule.getSalaryRangeOne())
                .mapToDouble(map)
                .sum();
    }

    /**
     * Calculates federal self-employed taxes due by utilizing Stream library to filter filing status through tax brackets and
     * applying corresponding rate.
     *
     * @param filingStatus Individual's filing status
     * @param income Individual's income
     * @param isChurchEmployee If individual works for a church
     * @param claimDeduction If user claims deduction
     * @return Federal self-employment tax due
     */
    public static double getFederalSelfEmploymentTaxDue(String filingStatus, double income, boolean isChurchEmployee,
                                                        boolean claimDeduction) {
        double taxableIncome = income * 0.9235;

        ToDoubleFunction<SelfEmployedTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.getSalaryRangeTwo(), taxableIncome);
            return rangeTwo * taxRule.getTaxRate();
        };

        return selfEmployedTaxRules.stream()
                .filter(taxRule -> taxRule.isChurchEmployee() == isChurchEmployee)
                .filter(taxRule -> taxRule.getFilingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> taxableIncome > taxRule.getSalaryRangeOne())
                .mapToDouble(map)
                .sum();
    }

    /**
     * Calculates the individual's effective tax rate
     *
     * @param totalTaxes Taxes owed
     * @param income Individual's income
     * @return Individual's effective tax rate
     */
    public static double getFederalEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}