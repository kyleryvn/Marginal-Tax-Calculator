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
 * <h3>HTML Parser</h3>
 * <p>
 *     This class parses HTML data from a given <a href="https://www.incometaxpro.net/tax-rates/">website</a> into
 *     a {@link StateTaxRule} object.
 * </p>
 *
 * @author Kyle Schoenhardt
 * @since 2022-11-09
 * @version 2.0.0
 */
public class ParseHTML {

    /**
     * <p>
     *     Validates URL for given state and filters data to be parsed by applying individual's filing status.
     * </p>
     * @param state The state to get tax brackets from
     * @param filingStatus The filing status of the individual
     * @return A {@link  List} containing the state's tax brackets
     */
    public static List<StateTaxRule> parseHtml(String state, String filingStatus) {
        // Two word states have '-' in the url instead of a space
        // Example: new-york.htm
        if (state.contains(" ")) {
            state = state.replace(" ", "-");
        }

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

    /**
     * <p>
     *     Extracts the text values from HTML tags.
     * </p>
     * @param filingStatus The filing status of the individual
     * @param elements Array containing various HTML tags
     * @return A {@link List} containing {@link StateTaxRule} objects
     */
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

    /**
     * <p>
     *     Converts parameter into a double.
     * </p>
     * @param element Value to be converted
     * @return Converted element
     */
    private static double parseDouble(Element element) {
        if (element.text().contains("%"))
            return Double.parseDouble(element.text().replace("%", "").strip()) / 100;

        return Double.parseDouble(element.text().replaceAll("[,|$]", "").strip());
    }
}
