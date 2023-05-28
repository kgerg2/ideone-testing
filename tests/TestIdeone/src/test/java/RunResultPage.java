import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RunResultPage extends PageBase {
    protected By sourceCodeBy = By.id("source");
    protected By stdinBy = By.xpath("//div[@id='view_stdin']//pre");
    protected By stdoutBy = By.id("output-text");
    protected By statusSuccessBy = By.xpath("//div[@id='view_status']//span[contains(@class, 'green')]");

    public RunResultPage(WebDriver driver) {
        super(driver);
        waitForElementToAppear(sourceCodeBy);
    }

    public RunResultPage(WebDriver driver, String runId) {
        super(driver, "https://ideone.com/" + runId);
    }

    public String getSourceCode() {
        return driver.findElement(sourceCodeBy).getText();
    }

    public String getStdin() {
        return driver.findElement(stdinBy).getText();
    }

    public String getStdout() {
        return driver.findElement(stdoutBy).getText();
    }

    public void waitForSuccessfulRun() {
        waitForElementToAppear(statusSuccessBy, 60);
    }

    public String getRunId() {
        String url = driver.getCurrentUrl();
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}
