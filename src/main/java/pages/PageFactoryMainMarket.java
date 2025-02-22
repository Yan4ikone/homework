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

@FindBy(how = How.XPATH, using = "//div[@data-baobab-name='headerPromo']")
    WebElement falseButton;

@FindBy(how = How.XPATH, using = "//div[@data-zone-name ='catalog']")
    WebElement buttonCatalog;

@FindBy(how = How.XPATH, using = "//a[@href='/catalog--noutbuki/26895412/list?hid=91013']")
    WebElement buttonLaptops;

@FindBy(how = How.XPATH, using = "//li[@data-zone-name='category-link']/a[@href='https://market.yandex.ru/special/electronics_dep']")
    WebElement electronicTab;

@FindBy(how = How.XPATH, using = "https://market.yandex.ru/catalog--noutbuki/")
    WebElement headerAfterClick;

    public PageFactoryMainMarket(WebDriver chromeDriver) {
        this.driver = chromeDriver;
    }

    public void find(String word) {
        driver.get("https://market.yandex.ru/");

        Actions actions = new Actions(driver);

        falseButton.click();
        buttonCatalog.click();
        actions.moveToElement(electronicTab).perform();
        buttonLaptops.click();
        word = driver.getTitle();
    }

    public WebElement getButtonCatalog() {
        return buttonCatalog;
    }

    public WebElement getButtonLaptops() {
        return buttonLaptops;
    }

    public WebElement getFalseButton() {
        return falseButton;
    }

    public WebElement getElectronicTab() {
        return electronicTab;
    }

    public WebElement getLinkAfterClick() {
        return headerAfterClick;
    }
}
