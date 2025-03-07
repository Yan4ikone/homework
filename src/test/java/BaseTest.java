import helpers.ConfigProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static helpers.Properties.configProperties;

/**
 * Базовый класс для тестов.
 * Выполняет настройку и завершение работы WebDriver.
 * @author Yan
 */
public class BaseTest {

    protected WebDriver chromeDriver;
/**
* Настройка WebDriver перед каждым тестом.
*/
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
        chromeDriver = new ChromeDriver(capabilities);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(configProperties.timeOut(), TimeUnit.SECONDS);
    }
/**
* Завершение работы WebDriver после каждого теста.
*/
    @AfterEach
    public void after(){
        chromeDriver.quit();
    }
}


