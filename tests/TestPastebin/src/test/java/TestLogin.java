import org.junit.Assert;
import org.junit.Test;

public class TestLogin extends TestBase {
    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);

        MainPage mainPage = loginPage.login(username, password);

        Assert.assertEquals(username, mainPage.getUsername());
    }
}
