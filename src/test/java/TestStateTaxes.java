import com.github.kyleryvn.taxservice.dao.StateDAO;
import com.github.kyleryvn.taxservice.services.StateTaxService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test: State Data")
public class TestStateTaxes {

    @Test
    @Order(1)
    @DisplayName("Test getting data from HashSet")
    void testGettingStatesWithoutIncomeTax() {
        Set<String> set = StateDAO.getStates();

        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Test state tax service")
    void testStateTaxService() {
        String state = "New York";
        String filingStatus = "S";
        double income = 140_000;
        double taxDue = StateTaxService.getStateTaxDue(state, filingStatus, income);

        String expected = "$8,213.53";
        String taxFormatted = NumberFormat.getCurrencyInstance(Locale.US).format(taxDue);

        assertEquals(expected, taxFormatted);
    }

    @Test
    @Order(2)
    @DisplayName("Test state tax service, no income tax")
    void testStateTaxServiceNoIncomeTax() {
        String state = "Florida";
        String filingStatus = "S";
        double income = 140_000;
        double taxDue = StateTaxService.getStateTaxDue(state, filingStatus, income);

        String expected = "$0.00";
        String taxFormatted = NumberFormat.getCurrencyInstance(Locale.US).format(taxDue);

        assertEquals(expected, taxFormatted);
    }
}
