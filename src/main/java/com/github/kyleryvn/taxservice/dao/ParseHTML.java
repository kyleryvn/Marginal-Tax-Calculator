package com.github.kyleryvn.taxservice.dao;

import com.github.kyleryvn.taxservice.model.StateTaxRule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Parse HTML</h3>
 * <p>
 *     This class is used to scrape state tax data from the predetermined webpage at
 *     <a href="https://www.incometaxpro.net/tax-rates/">incometaxpro.com</a>. This class
 *     utilizes the Jsoup library to accomplish this.
 * </p>
 */

public class ParseHTML {

    /**
     * <p>
     *     This method
     * </p>
     *
     * @param state State for which taxes are being calculated. Method grabs specific table
     *              from website, and parses the tax data for provided filing status
     * @param filingStatus User's filing status
     * @return List of tax brackets
     */
    public static List<StateTaxRule> parseHtml(String state, String filingStatus) {
        String url = "https://www.incometaxpro.net/tax-rates/" + state.toLowerCase() + ".htm";
        List<StateTaxRule> stateRates = new ArrayList<>();
        Elements data;

        try {
            Document document = Jsoup.connect(url).get();

            data = switch (filingStatus) {
                case "S" -> document.select("table.statebrackets:first-of-type");
                case "MFJ" -> document.select("table.statebrackets:nth-of-type(2)");
                case "MFS" -> document.select("table.statebrackets:nth-of-type(3)");
                case "HH" -> document.select("table.statebrackets:last-of-type");
                default -> throw new IllegalStateException("Unexpected value");
            };

            stateRates = extractData(filingStatus, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stateRates;
    }

    private static List<StateTaxRule> extractData(String filingStatus, Elements elements) {
        List<StateTaxRule> stateData = new ArrayList<>();
        Elements data = elements.select("tbody tr");

        for (int i = 0; i < data.size(); i++) {
            Element rate = data.select("td:eq(3)").get(i);
            Element salaryRange1 = data.select("td:eq(0)").get(i);
            Element salaryRange2 = data.select("td:eq(2)").get(i);

            double taxRate = parseDouble(rate);
            double salaryRangeOne = parseDouble(salaryRange1);
            double salaryRangeTwo = salaryRange2.text().equalsIgnoreCase("∞") ? Double.POSITIVE_INFINITY
                    : parseDouble(salaryRange2);

            stateData.add(new StateTaxRule(taxRate, filingStatus, salaryRangeOne, salaryRangeTwo));
        }

        return stateData;
    }

    private static double parseDouble(Element element) {
        if (element.text().contains("%"))
            return Double.parseDouble(element.text().replace("%", "").strip()) / 100;

        return Double.parseDouble(element.text().replaceAll("[,|$]", "").strip());
    }
}
