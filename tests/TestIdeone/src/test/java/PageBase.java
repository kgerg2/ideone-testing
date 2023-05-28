import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    protected WebDriver driver;

    private Cookie cookiesAccepted = new Cookie("settings", "%7B%22ue%22%3A1%7D");

    protected By cookiePopupBy = By.id("cookie-ue");
    protected By cookiePopupAcceptBy = By.xpath("//div[@id='cookie-ue']//button");
    protected By navbarBy = By.id("primary-navigation");
    protected By accountDropdownBy = By.xpath("//div[@id='primary-navigation']//ul[contains(@class, 'nav')]" +
            "//a[@data-toggle='dropdown']");

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
    }

    public void waitForElementToAppear(By by) {
        waitForElementToAppear(by, 10);
    }

    public void waitForElementToAppear(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public WebElement getCookiesPopup() {
        return driver.findElement(cookiePopupBy);
    }

    public boolean cookiePopupExists() {
        return driver.findElements(cookiePopupBy).size() > 0;
    }

    public void clickAcceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookiePopupAcceptBy));
        acceptButton.click();
    }

    public void addCookiesAcceptedCookie() {
        driver.manage().addCookie(cookiesAccepted);
    }

    public String getAccountDropdownText() {
        return driver.findElement(accountDropdownBy).getText();
    }
}
