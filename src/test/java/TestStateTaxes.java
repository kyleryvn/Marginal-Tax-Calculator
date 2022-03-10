import com.github.kyleryvn.taxservice.dao.StateDAO;
import com.github.kyleryvn.taxservice.services.StateTaxService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

@DisplayName("Test: State Data")
public class TestStateTaxes {

    @Test
    @DisplayName("Test getting data from HashMap")
    void t1() {
        Map<String, String> map = new StateDAO().getStateURLS();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

    @Test
    @DisplayName("Test Arizona")
    void testArizona() {
        double salary = 450_000;
        double taxDue = StateTaxService.getStateTaxDue("AZ", "S", salary);
        String finalDue = NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(taxDue);
        System.out.println("Tax due: " + finalDue);
    }

    @Test
    @DisplayName("Test Arkansas")
    void testArkansas() {
        double salary = 90_000;
        String filingStatus = "S";
        double taxDue = StateTaxService.getStateTaxDue("AR", filingStatus, salary);
        String taxFormatted = NumberFormat.getCurrencyInstance(Locale.US).format(taxDue);
        System.out.println("Tax due: " + taxFormatted);
    }

    @Test
    @DisplayName("Test New Hampshire")
    void testNewHampshire() {
        double salary = 145_942;
        String filingStatus = "S";
        double taxDue = StateTaxService.getStateTaxDue("NH", filingStatus, salary); // NH taxes 5% on interest and dividends only
        String taxFormatted = NumberFormat.getCurrencyInstance(Locale.US).format(taxDue);
        System.out.println("Tax due: " + taxFormatted);
    }

    @Test
    @DisplayName("Test Florida")
    void testFlorida() {
        double salary = 50_000;
        double taxDue = StateTaxService.getStateTaxDue("FL", "HH", salary);
        String taxFormatted = NumberFormat.getCurrencyInstance(Locale.US).format(taxDue);
        System.out.println("Tax due: " + taxFormatted);
    }
}
