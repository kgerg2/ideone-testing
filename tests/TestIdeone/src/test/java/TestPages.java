import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestPages extends TestBase {
    @Test
    public void testGoingBack() {
        PageBase page = new MainPage(driver);
        String mainPageURL = page.getURL();
        page = new RecentsPage(driver);
        Assert.assertNotEquals(mainPageURL, page.getURL());
        page.goBack();
        Assert.assertEquals(mainPageURL, page.getURL());
    }

    @Test
    public void testTitles() {
        for (Map.Entry<String, String> entry : titles.entrySet()) {
            String url = entry.getKey();
            String title = entry.getValue();
            PageBase page = new PageBase(driver, url);
            Assert.assertEquals(title, page.getTitle());
        }
    }
}
