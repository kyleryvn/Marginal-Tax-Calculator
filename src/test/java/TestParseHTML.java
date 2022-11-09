import com.github.kyleryvn.taxservice.dao.HTMLParser;
import com.github.kyleryvn.taxservice.model.StateTaxRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Parse HTML Test")
public class TestHTMLParser {

    @Test
    void testParseHTML() {
        String filingStatus = "S";
        String state = "Arizona";
        List<StateTaxRule> taxRules;

        taxRules = HTMLParser.parseHtml(state, filingStatus);
        for (StateTaxRule taxRule : taxRules) {
            System.out.println(taxRule);
        }
    }

}
