import org.junit.*;

public class TestMainPageInteractions extends TestBase {
    @Test
    public void testMenuDropdown() {
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.login(username, password);
        
        Assert.assertFalse(mainPage.getDropdownMenu().isDisplayed());
        mainPage.hoverOverDropdown();
        Assert.assertTrue(mainPage.getDropdownMenu().isDisplayed());
    }
}
