package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.dao.StateDAO;
import com.github.kyleryvn.taxservice.model.StateTaxRule;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class StateTaxService {
    private static final Set<String> statesWithoutIncomeTax = new HashSet<>();

    public static double getStateTaxDue(String stateAbbreviation, String filingStatus, double income) {
        Map<String, String> stateURLS = new StateDAO().getStateURLS();
        List<StateTaxRule> stateTaxRules;
        setStatesWithoutIncomeTax();

        if (stateURLS.containsKey(stateAbbreviation)) {
            Gson gson = new Gson();
            String filename = stateURLS.get(stateAbbreviation);
            Function<String, StateTaxRule> convert = json -> gson.fromJson(json, StateTaxRule.class);
            stateTaxRules = ResourceUtility.getResourceAsList(filename, 0, convert);

        } else if (stateAbbreviation.equalsIgnoreCase("NH")) {
            // NH taxes 5% on interest and dividends only
            return income * 0.05;

        } else if (stateAbbreviation.equalsIgnoreCase("WA")) {
            // WA taxes 7% on capital gains income only
            return income * 0.07;

        } else if (statesWithoutIncomeTax.contains(stateAbbreviation)) {
            System.out.println("This state does not collect income tax");
            return 0;

        } else {
            System.out.println("ERROR: Invalid state");
            return 0;
        }

        ToDoubleFunction<StateTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.getSalaryRangeTwo(), income);
            return (rangeTwo - taxRule.getSalaryRangeOne()) * taxRule.getTaxRate();
        };

        return stateTaxRules.stream()
                .filter(taxRule -> taxRule.getFilingStatus().equalsIgnoreCase(filingStatus))
                .filter(taxRule -> income > taxRule.getSalaryRangeOne())
                .mapToDouble(map)
                //.peek(System.out::println)
                .sum();
    }

    public static double getStateEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }

    private static void setStatesWithoutIncomeTax() {
        // Alaska, Florida, Nevada, South Dakota, Texas, Tennessee,
        // Washington, and Wyoming do not collect income taxes
        statesWithoutIncomeTax.add("AK");
        statesWithoutIncomeTax.add("FL");
        statesWithoutIncomeTax.add("NV");
        statesWithoutIncomeTax.add("SD");
        statesWithoutIncomeTax.add("TX");
        statesWithoutIncomeTax.add("TN");
        statesWithoutIncomeTax.add("WY");
    }
}
