import com.github.kyleryvn.taxservice.model.SelfEmployedTaxRule;
import com.github.kyleryvn.taxservice.services.FederalTaxService;
import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

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
        double income = 50_000;
        String filingStatus = "S";
        boolean isChurchEmployee = false;
        double taxDue = FederalTaxService.getFederalSelfEmploymentTaxDue(filingStatus, income, isChurchEmployee);
        System.out.println(taxDue);
    }
}
