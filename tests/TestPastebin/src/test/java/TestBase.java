import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    protected WebDriver driver;
    protected static Properties properties;
    protected static String username;
    protected static String password;
    protected static String email;
    protected static String emailApiKey;

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, IOException {
        properties = new Properties();
        InputStream input = new FileInputStream("config.properties");
        properties.load(input);

        username = properties.getProperty("pastebin.username");
        password = properties.getProperty("pastebin.password");
        email = properties.getProperty("mailsac.email");
        emailApiKey = properties.getProperty("mailsac.api_key");

        input.close();
    }

    @Before
    public void setUp() throws java.net.MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
