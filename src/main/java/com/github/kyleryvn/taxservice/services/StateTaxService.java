package com.github.kyleryvn.taxservice.services;

import com.github.kyleryvn.taxservice.dao.ParseHTML;
import com.github.kyleryvn.taxservice.dao.StateDAO;
import com.github.kyleryvn.taxservice.model.StateTaxRule;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class StateTaxService {
    private static Set<String> statesWithoutIncomeTax = new HashSet<>();

    public static double getStateTaxDue(String state, String filingStatus, double income) {
        List<StateTaxRule> stateTaxRules;
        setStatesWithoutIncomeTax();


        ToDoubleFunction<StateTaxRule> map = taxRule -> {
            double rangeTwo = Math.min(taxRule.salaryRangeTwo(), income);
            return (rangeTwo - taxRule.salaryRangeOne()) * taxRule.taxRate();
        };

        if (!statesWithoutIncomeTax.contains(state)) {
            stateTaxRules = ParseHTML.parseHtml(state, filingStatus);
            //System.out.println(stateTaxRules);

            return stateTaxRules.stream()
                    .filter(taxRule -> taxRule.filingStatus().equalsIgnoreCase(filingStatus))
                    .filter(taxRule -> income > taxRule.salaryRangeOne())
                    .mapToDouble(map)
                    .sum();

        } else if (state.equalsIgnoreCase("NH")) {
            // NH taxes 5% on interest and dividends only
            return income * 0.05;

        } else if (state.equalsIgnoreCase("WA")) {
            // WA taxes 7% on capital gains income only
            return income * 0.07;

        } else {
            System.out.println("ERROR: Invalid state or state does not collect income tax");
            return 0;
        }
    }

    public static double getStateEffectiveRate(double totalTaxes, double income) {
        return (totalTaxes / income) * 100;
    }

    // Alaska, Florida, Nevada, South Dakota, Texas, Tennessee,
    // Washington, and Wyoming do not collect income taxes
    private static void setStatesWithoutIncomeTax() {
        statesWithoutIncomeTax = StateDAO.getStates();
    }
}
