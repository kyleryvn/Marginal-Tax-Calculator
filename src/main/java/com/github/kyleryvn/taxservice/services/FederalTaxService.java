package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.model.FederalTaxRule;
import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class FederalTaxService {
    private static final List<FederalTaxRule> fedTaxRules;
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
            double rangeTwo = Math.min(taxRule.getSalaryRangeTwo(), income);
            return (rangeTwo - taxRule.getSalaryRangeOne()) * taxRule.getTaxRate();
        };

        return fedTaxRules.stream()
                .filter(taxRule -> taxRule.getFilingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> income > taxRule.getSalaryRangeOne())
                .mapToDouble(map)
                .sum();
    }

    public static double getFederalSelfEmploymentTaxDue(String filingStatus, double income, boolean isChurchEmployee) {
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

    public static double getFederalEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}


/*
double tax = 0.124 * 142_800;

            if (taxableIncome <= 142_800) {
                tax += taxableIncome * 0.029;
            } else {
                tax += (taxableIncome * 0.029) + (taxableIncome * 0.009);
            }

            taxDue += tax;

            return taxDue;
 */