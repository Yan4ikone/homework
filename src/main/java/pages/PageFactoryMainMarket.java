package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Properties.configProperties;

public class PageFactoryMainMarket {

private WebDriver driver;
private WebDriverWait wait;
private Actions actions;

@FindBy(how = How.XPATH, using = "//div[@data-zone-name ='catalog']")
    WebElement buttonCatalog;

@FindBy(how = How.XPATH, using = "//a[text()='Ноутбуки']")
    WebElement buttonLaptops;

@FindBy(how = How.XPATH, using = "//li[@data-zone-name='category-link']" +
        "/a[@href='https://market.yandex.ru/special/electronics_dep']")
    WebElement electronicTab;

@FindBy(how = How.XPATH, using = "//div[@data-baobab-name='login_popup']")
    WebElement popup;

@FindBy(how = How.XPATH, using = "//div[@aria-label='Закрыть']")
    WebElement closeArea;


    public PageFactoryMainMarket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    /**
     * Открытие каталога, наведение на "Электроника" и переход в "Ноутбуки".
     */
     public void find(String url) {
        driver.get(url);
        if (popup.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(closeArea));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeArea); //JavaScript выполняет клик напрямую, игнорируя возможные перекрытия.
        }
        buttonCatalog.click();
        actions.moveToElement(electronicTab).perform();
        buttonLaptops.click();
     }
}
