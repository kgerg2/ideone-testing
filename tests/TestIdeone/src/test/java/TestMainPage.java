import org.junit.Assert;
import org.junit.Test;

public class TestMainPage extends TestBase {
    @Test
    public void testNotSignedIn() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals("sign in", mainPage.getAccountDropdownText());
    }

    @Test
    public void testCookiePopup() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.cookiePopupExists());
        Assert.assertTrue(mainPage.getCookiesPopup().isDisplayed());
    }

    @Test
    public void testAcceptCookiesWithButton() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.cookiePopupExists());

        mainPage.clickAcceptCookies();
        Assert.assertFalse(mainPage.getCookiesPopup().isDisplayed());

        mainPage.refresh();
        Assert.assertFalse(mainPage.cookiePopupExists());
    }

    @Test
    public void testAcceptCookiesWithCookie() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.cookiePopupExists());

        mainPage.addCookiesAcceptedCookie();
        mainPage.refresh();
        Assert.assertFalse(mainPage.cookiePopupExists());
    }
}
