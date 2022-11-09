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

    public static double getFederalEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}