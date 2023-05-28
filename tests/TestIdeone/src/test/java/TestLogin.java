import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestLogin extends TestBase {
    @Test
    public void testSignInWithUsername() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(username, password);
        mainPage.waitForElementToAppear(mainPage.accountDropdownBy);
        Assert.assertEquals("my account", mainPage.getAccountDropdownText());
    }

    @Test
    public void testSignInWithEmail() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(email, password);
        mainPage.waitForElementToAppear(mainPage.accountDropdownBy);
        Assert.assertEquals("my account", mainPage.getAccountDropdownText());
    }

    @Test
    public void testSignOut() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(username, password);
        mainPage.waitForElementToAppear(mainPage.accountDropdownBy);
        Assert.assertEquals("my account", mainPage.getAccountDropdownText());

        mainPage.signOut();
        mainPage.waitForElementToAppear(mainPage.accountDropdownBy);
        Assert.assertEquals("sign in", mainPage.getAccountDropdownText());
    }

    @Test
    public void testRunCodeSignedIn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(username, password);
        mainPage.waitForElementToAppear(mainPage.accountDropdownBy);
        Assert.assertEquals("my account", mainPage.getAccountDropdownText());

        Map.Entry<String, String> entry = programs.entrySet().iterator().next();
        String language = entry.getKey();
        int num1 = new Random().nextInt(100);
        int num2 = new Random().nextInt(100);
        String code = String.format(entry.getValue(), num1, num2);

        mainPage.selectLanguage(language);
        mainPage.enterCode(code);
        RunResultPage resultPage = mainPage.submit();

        resultPage.waitForSuccessfulRun();
        Assert.assertEquals(String.valueOf(num1 + num2), resultPage.getStdout());

        String runId = resultPage.getRunId();

        RecentsPage recentsPage = new RecentsPage(driver);
        Assert.assertTrue(recentsPage.isRunIdPresent(runId));

        recentsPage.filterForToday();
        Assert.assertTrue(recentsPage.isRunIdPresent(runId));
    }
}
