package lt.debarz;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class WindowConfirmTest extends ManagedWebClient {
    @Test
    public void testWindowConfirm() throws FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title></head> " +
                "<body onload='confirm(\"Confirm Message\")'> </body></html>";
        URL testURL = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        final List<String> confirmMessages = new ArrayList<>();

        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });

        mockConnection.setResponse(testURL, html);
        webClient.setWebConnection(mockConnection);
        HtmlPage firstPage = webClient.getPage(testURL);

        WebAssert.assertTitleEquals(firstPage, "Hello");
        assertArrayEquals(new String[] { "Confirm Message" }, confirmMessages.toArray());
    }

    @Test
    public void testWindowConfirmAndAlert() throws
            FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title> <script>function go(){ alert(confirm('Confirm Message')) }</script>\n" +
        "</head><body onload='go()'></body></html>";

        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockConnection = new MockWebConnection();
        final List<String> confirmMessages = new ArrayList<String>();

        webClient.setAlertHandler(new CollectingAlertHandler());
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);
            return true;
        });
        mockConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockConnection);
        HtmlPage firstPage = webClient.getPage(testUrl);

        WebAssert.assertTitleEquals(firstPage, "Hello");
        assertArrayEquals(new String[] { "Confirm Message" }, confirmMessages.toArray());
        assertArrayEquals(new String[] { "true" },
                ((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts().toArray());
    }

}
