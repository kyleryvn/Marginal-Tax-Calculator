import com.github.kyleryvn.taxservice.dao.ParseHTML;
import com.github.kyleryvn.taxservice.model.StateTaxRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Parse HTML Test")
public class TestParseHTML {

    @Test
    void testParseHTML() {
        String filingStatus = "S";
        String state = "Arizona";
        List<StateTaxRule> taxRules;

        taxRules = ParseHTML.parseHtml(state, filingStatus);
        for (StateTaxRule taxRule : taxRules) {
            System.out.println(taxRule);
        }
    }


}
