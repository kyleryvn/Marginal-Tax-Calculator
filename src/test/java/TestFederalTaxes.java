import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.services.FederalTaxService;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFederalTaxes {

    @Test
    void printSelfTaxRules() {
        Gson gson = new Gson();
        Function<String, SelfEmployedTaxRule> convert = json -> gson.fromJson(json, SelfEmployedTaxRule.class);
        List<SelfEmployedTaxRule> list = ResourceUtility.getResourceAsList("docs/fedSelfEmployedTaxRules.txt", 0, convert);
        list.forEach(System.out::println);
    }

    @Test
    void testSelfEmployedTaxes() {
        double expected = 182.271195;
        double taxDue = FederalTaxService.getFederalSelfEmploymentTaxDue("S", 1290, false, false);

        assertEquals(expected, taxDue);
    }
}
