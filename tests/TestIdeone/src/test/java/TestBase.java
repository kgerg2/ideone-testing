import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    protected WebDriver driver;
    protected static Properties properties;
    protected static String username;
    protected static String password;
    protected static String email;
    protected static Map<String, String> programs;
    protected static Map<String, String> titles;

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, IOException {
        properties = new Properties();
        InputStream input = new FileInputStream("config.properties");
        properties.load(input);

        username = properties.getProperty("ideone.username");
        password = properties.getProperty("ideone.password");
        email = properties.getProperty("mailsac.email");

        programs = (Map<String, String>) properties.entrySet().stream()
                .filter(entry -> entry.getKey().toString().startsWith("programs."))
                .collect(java.util.stream.Collectors.toMap(entry -> entry.getKey().toString().substring(9),
                        entry -> entry.getValue().toString()));

        titles = new HashMap<String, String>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("pages.") && key.endsWith(".url")) {
                String url = properties.getProperty(key);
                String titleKey = key.replace(".url", ".title");
                String title = properties.getProperty(titleKey);
                titles.put(url, title);
            }
        }

        input.close();
    }

    @Before
    public void setUp() throws java.net.MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Exception while quitting driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
