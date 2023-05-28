import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestCodeRunning extends TestBase {
    @Test
    public void testPrograms() {
        // Loop through the programs and run them
        for (Map.Entry<String, String> entry : programs.entrySet()) {
            String language = entry.getKey();
            int num1 = new Random().nextInt(100);
            int num2 = new Random().nextInt(100);
            String code = String.format(entry.getValue(), num1, num2);

            MainPage mainPage = new MainPage(driver);

            mainPage.selectLanguage(language);
            mainPage.enterCode(code);

            RunResultPage resultPage = mainPage.submit();

            Assert.assertEquals(code, resultPage.getSourceCode());

            resultPage.waitForSuccessfulRun();

            String expectedOutput = String.valueOf(num1 + num2);
            Assert.assertEquals(expectedOutput, resultPage.getStdout());
        }
    }
}
