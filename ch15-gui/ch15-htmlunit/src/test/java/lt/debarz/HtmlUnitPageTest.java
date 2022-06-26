package lt.debarz;

import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HtmlUnitPageTest extends ManagedWebClient{

    @Test
    public void homePage() throws IOException {
        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

        String pageAsXml = page.asXml();
        assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));

        String pageAsText = page.asNormalizedText();
        assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
    }

    @Test
    public void testClassNav() throws IOException{
        HtmlPage mainPage = webClient.getPage("http://htmlunit.sourceforge.net/apidocs/index.html");
        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosedPage();
        HtmlListItem htmlListItem = (HtmlListItem) packagePage.getElementsByTagName("li").item(0);

        assertEquals("AboutURLConnection", htmlListItem.getTextContent());
    }
}
