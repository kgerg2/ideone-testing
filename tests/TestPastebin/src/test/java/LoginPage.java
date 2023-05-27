import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {
    public static final String URL = "https://pastebin.com/login";

    private By usernameBy = By.id("loginform-username");
    private By passwordBy = By.id("loginform-password");
    private By loginButtonBy = By.xpath("//form[@action='/login']//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get(URL);
        // wait for the page to load
        waitForElementToAppear(headerBy);
    }

    public void setUsername(String username) {
        driver.findElement(usernameBy).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordBy).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonBy).click();
    }

    public MainPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();

        return new MainPage(driver);
    }
}
