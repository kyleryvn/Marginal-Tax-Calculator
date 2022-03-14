import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.services.FederalTaxService;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test Federal Taxes")
public class TestFederalTaxes {

    @Test
    @DisplayName("Test Reading File Data")
    void printSelfTaxRules() {
        Gson gson = new Gson();
        Function<String, SelfEmployedTaxRule> convert = json -> gson.fromJson(json, SelfEmployedTaxRule.class);
        List<SelfEmployedTaxRule> list = ResourceUtility.getResourceAsList("docs/fedSelfEmployedTaxRules.txt", 0, convert);
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("Test Self-Employed Taxes")
    void testSelfEmployedTaxes() {
        double expected = 182.271195;
        double taxDue = FederalTaxService.getFederalSelfEmploymentTaxDue("S", 1290, false, false);

        assertEquals(expected, taxDue);
    }
}
