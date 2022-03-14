package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.model.FederalTaxRule;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class FederalTaxService {
    private static final List<FederalTaxRule> fedTaxRules;

    static {
        Gson gson = new Gson();

        // Create FederalTaxRule objects from JSON data
        Function<String, FederalTaxRule> convert = json -> gson.fromJson(json, FederalTaxRule.class);
        fedTaxRules = ResourceUtility.getResourceAsList("docs/fedTaxRules.txt", 0, convert);
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

    public static double getFederalSelfEmploymentTaxDue(double income, boolean isChurchEmployee) {
        if (income >= 400 && !isChurchEmployee) {
            return income * 0.153;
        } else if (income >= 108.28 && isChurchEmployee) {
            return income * 0.153;
        } else {
            return 0;
        }
    }

    public static double getFederalEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }
}
