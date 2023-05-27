import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends PageBase {
    public static final String URL = "https://pastebin.com";

    private By textareaBy = By.id("postform-text");
    private By pasteExpirationBy = By.id("postform-expiration");
    private By noticeBy = By.xpath("//div[contains(@class, 'notice')]");
    private By cookiePopupBy = By.xpath("//div[@class='popup-box -cookies']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get(URL);
    }

    public String getNoticeText() {
        return driver.findElement(noticeBy).getText();
    }

    public WebElement getCookiePopup() {
        return driver.findElement(cookiePopupBy);
    }

    public WebElement getTextarea() {
        return driver.findElement(textareaBy);
    }

    public void setPasteExpiration(String value) {
        // Validate that the value is a valid option
        WebElement pasteExpiration = driver.findElement(pasteExpirationBy);
        pasteExpiration.findElement(By.xpath("//option[value='" + value + "']"));

        pasteExpiration.sendKeys(value);
    }
}
