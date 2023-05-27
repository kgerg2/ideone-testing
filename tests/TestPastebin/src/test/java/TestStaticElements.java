import java.net.URL;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestStaticElements extends TestBase {

    // @Test
    // public void testOpen() {
    // // Verify that the page is loaded
    // Assert.assertNotNull(driver);

    // // Wait for 5 minutes
    // try {
    // Thread.sleep(300000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }

    @Test
    public void testTitle() {
        MainPage mainPage = new MainPage(driver);

        // Verify that the title is "Pastebin.com - #1 paste tool since 2002!"
        Assert.assertEquals("Pastebin.com - #1 paste tool since 2002!", mainPage.getTitle());
    }

    @Test
    public void testTextarea() {
        MainPage mainPage = new MainPage(driver);
        // Verify that the textarea with id "postform-text" exists and is empty
        WebElement textarea = mainPage.getTextarea();

        Assert.assertNotNull(textarea);
        Assert.assertEquals("", textarea.getText());
    }

    @Test
    public void testNotLoggedIn() {
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.getNoticeText().contains("You are currently not logged in"));

        // Verify that the username is not displayed
        try {
            mainPage.getUsername();
            Assert.fail("The username should not be displayed");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }

    @Test
    public void testCookiePopupExists() {
        MainPage mainPage = new MainPage(driver);

        WebElement popup = mainPage.getCookiePopup();
        Assert.assertNotNull(popup);
    }

    @Test
    public void testCookiePopupDoesntExist() {
        MainPage mainPage = new MainPage(driver);

        mainPage.acceptCookies();
        mainPage.refresh();

        try {
            mainPage.getCookiePopup();
            Assert.fail("The cookie popup should not exist");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }
}
