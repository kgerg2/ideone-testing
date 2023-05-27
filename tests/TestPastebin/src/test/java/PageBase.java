import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    protected WebDriver driver;

    private Cookie cookiesAccepted = new Cookie("l2c_1", "true");

    protected By headerBy = By.xpath("//div[@class='header']");
    protected By usernameBy = By.xpath("//div[@class='header']//div[@class='header__user-name']");
    protected By dropdownIconBy = By.xpath("//div[@class='header']//div[@class='dropdown js-header-dropdown']");
    protected By dropdownMenuBy = By.xpath("//div[@class='header']//div[contains(@class, 'dropdown-menu')]");

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void acceptCookies() {
        driver.manage().addCookie(cookiesAccepted);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public String getUsername() {
        return driver.findElement(usernameBy).getText();
    }

    public void logout() {
        driver.get("https://pastebin.com/site/logout");
    }

    public void hoverOverDropdown() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(dropdownIconBy);
        actions.moveToElement(element).perform();
    }

    public WebElement getDropdownMenu() {
        return driver.findElement(dropdownMenuBy);
    }
}
