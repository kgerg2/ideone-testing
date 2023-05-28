import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PageBase {
    private static final String URL = "https://ideone.com/";

    protected By signinDropdownBy = By.id("signin-dropdown");
    protected By signinDropdownUsernameBy = By.id("username");
    protected By signinDropdownPasswordBy = By.id("password");
    protected By signinDropdownSubmitBy = By.xpath("//ul[@id='signin-dropdown']//form//button[@type='submit']");
    protected By signoutButtonBy = By.xpath("//div[@id='primary-navigation']//a[contains(@href, '/logout')]");

    protected By mainFormBy = By.id("main_form");
    protected By signedOutMainFormTextBy = By.xpath("//form[@id='main_form']//textarea[@class='ace_text-input']");
    protected By signedInMainFormTextBy = By.xpath("//form[@id='main_form']//textarea[@id='file']");
    protected By mainFormLanguageSelectorBy = By.xpath("//form[@id='main_form']//a[@id='lang-dropdown-menu-button']");
    protected By mainFormSelectedLanguageBy = By.xpath("//form[@id='main_form']" +
            "//a[@id='lang-dropdown-menu-button']/span");
    protected By mainFormLanguageSelectorItemsBy = By.xpath("//form[@id='main_form']//div[@id='lang-dropdown-menu']" +
            "//a[contains(@class, 'lang')][@data-label]");
    protected By mainFormSubmitBy = By.xpath("//form[@id='main_form']//button[@type='submit']");

    public MainPage(WebDriver driver) {
        super(driver, URL);
    }

    public WebElement getSigninDropdown() {
        return driver.findElement(signinDropdownBy);
    }

    public boolean isSignedIn() {
        return getAccountDropdownText().equals("my account");
    }

    public void signIn(String usernameOrEmail, String password) {
        driver.findElement(accountDropdownBy).click();

        driver.findElement(signinDropdownUsernameBy).sendKeys(usernameOrEmail);
        driver.findElement(signinDropdownPasswordBy).sendKeys(password);
        driver.findElement(signinDropdownSubmitBy).click();
    }

    public void signOut() {
        driver.findElement(accountDropdownBy).click();
        driver.findElement(signoutButtonBy).click();
    }

    public List<WebElement> getMainFormLanguageSelectorItems() {
        return driver.findElements(mainFormLanguageSelectorItemsBy);
    }

    public void selectLanguage(String language) {
        driver.findElement(mainFormLanguageSelectorBy).click();
        List<WebElement> languageItems = getMainFormLanguageSelectorItems();
        for (WebElement languageItem : languageItems) {
            if (languageItem.getAttribute("data-label").equals(language)) {
                languageItem.click();
                return;
            }
        }
        throw new RuntimeException("Language not found: " + language);
    }

    public String getSelectedLanguage() {
        return driver.findElement(mainFormSelectedLanguageBy).getText();
    }

    public WebElement getMainFormTextElement() {
        if (isSignedIn()) {
            return driver.findElement(signedInMainFormTextBy);
        } else {
            return driver.findElement(signedOutMainFormTextBy);
        }
    }

    public void enterCode(String code) {
        WebElement mainFormTextElement = getMainFormTextElement();

        Actions actions = new Actions(driver);
        actions.moveToElement(mainFormTextElement).click().perform();
        mainFormTextElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        mainFormTextElement.sendKeys(Keys.DELETE);
        mainFormTextElement.sendKeys(code);
    }

    public RunResultPage submit() {
        driver.findElement(mainFormSubmitBy).click();
        return new RunResultPage(driver);
    }
}
