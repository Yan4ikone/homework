package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainMarketBeforeSearch {


    private WebElement electronicTab;
    private WebElement buttonCatalog;
    private WebDriver driver;
    private WebElement buttonLaptops;
    private WebElement falseButton;
    private String title;

    private WebDriverWait wait;

    public MainMarketBeforeSearch(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, 10);
        wait.until(visibilityOfElementLocated(By.xpath("//div[@data-zone-name ='catalog']")));
        this.electronicTab = driver.findElement(By.xpath("//li[@data-zone-name='category-link']/a[@href='https://market.yandex.ru/special/electronics_dep']"));
        this.buttonCatalog = driver.findElement(By.xpath("//div[@data-zone-name ='catalog']"));
        this.buttonLaptops = driver.findElement(By.xpath("//a[@href='/catalog--noutbuki/26895412/list?hid=91013']"));
        this.falseButton = driver.findElement(By.xpath("//div[@data-baobab-name='headerPromo']"));
    }

    public void find(String title) {
        driver.get(" https://market.yandex.ru/");
        this.title = driver.getTitle();
        Actions actions = new Actions(driver);
        wait.until(visibilityOfElementLocated(By.xpath("//div[@data-baobab-name='login_popup']")));
        falseButton.click();
        buttonCatalog.click();
        actions.moveToElement(electronicTab).perform();
        wait.until(visibilityOfElementLocated(By.xpath("//a[@href='/catalog--noutbuki/26895412/list?hid=91013']")));
        buttonLaptops.click();
    }
}
