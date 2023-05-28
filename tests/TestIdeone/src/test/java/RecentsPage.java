import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecentsPage extends PageBase {
    private static final String URL = "https://ideone.com/myrecent";

    protected By runIdsBy = By.xpath("//div[@id='myrecent']/table/tbody/tr/td[2]/a");
    protected By filterTodayRadioBy = By.id("time_4");
    protected By filterAlertBy = By.xpath("//div[@id='myrecent']/div[contains(@class, 'alert')]");

    public RecentsPage(WebDriver driver) {
        super(driver, URL);
    }

    public List<String> getRunIds() {
        return driver.findElements(runIdsBy).stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public boolean isRunIdPresent(String runId) {
        return getRunIds().contains(runId);
    }

    public void filterForToday() {
        driver.findElement(filterTodayRadioBy).click();
        waitForElementToAppear(filterAlertBy);
    }
}
