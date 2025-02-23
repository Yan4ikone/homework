package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PageFactoryMainMarket {

private WebDriver driver;

@FindBy(how = How.XPATH, using = "//div[@data-zone-name ='catalog']")
    WebElement buttonCatalog;

@FindBy(how = How.XPATH, using = "//a[@href='/catalog--noutbuki/26895412/list?hid=91013']")
    WebElement buttonLaptops;

@FindBy(how = How.XPATH, using = "//li[@data-zone-name='category-link']/a[@href='https://market.yandex.ru/special/electronics_dep']")
    WebElement electronicTab;

@FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Ноутбуки')]")
    WebElement headerAfterClick;

    public PageFactoryMainMarket(WebDriver chromeDriver) {
        this.driver = chromeDriver;
    }

    public void find() {
        Actions actions = new Actions(driver);
        driver.get("https://market.yandex.ru/");
        actions.moveByOffset(10,10).click().perform();
        buttonCatalog.click();
        actions.moveToElement(electronicTab).perform();
        buttonLaptops.click();

    }

    public WebElement getHeaderAfterClick(String title) {
        return headerAfterClick;
    }
}
