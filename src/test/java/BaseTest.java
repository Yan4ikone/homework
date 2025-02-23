import helpers.ConfigProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static helpers.Properties.configProperties;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
        //System.setProperty("webdriver.chrome.driver","C:\\tmp\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
        chromeDriver = new ChromeDriver(capabilities);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(configProperties.timeOut(), TimeUnit.SECONDS);
        //Actions actions = new Actions(chromeDriver);
        //chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after(){
        chromeDriver.quit();

    }


}


