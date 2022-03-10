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

    public static double getFederalTaxDue(String filingStatus, double salary) {
        ToDoubleFunction<FederalTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.getSalaryRangeTwo(), salary);
            return (rangeTwo - taxRule.getSalaryRangeOne()) * taxRule.getTaxRate();
        };

        return fedTaxRules.stream()
                .filter(taxRule -> taxRule.getFilingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> salary > taxRule.getSalaryRangeOne())
                .mapToDouble(map)
                .sum();
    }
}
