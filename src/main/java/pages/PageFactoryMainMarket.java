package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class PageFactoryMainMarket {

private WebDriver driver;
private WebDriverWait wait;
private Actions actions;

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
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);

    }

    /**
     * Открытие каталога, наведение на "Электроника" и переход в "Ноутбуки".
     */
     public void find(String url) {
        driver.get(url);
        wait.until(visibilityOfElementLocated(By.xpath("//*[@id='/marketfrontDynamicPopupLoader42/content']")));
        actions.moveByOffset(10,10).click().perform();
        wait.until(visibilityOfAllElements(buttonCatalog));
        buttonCatalog.click();
        actions.moveToElement(electronicTab).perform();
        buttonLaptops.click();
     }

    /**
     * Проверка заголовка после перехода в раздел "Ноутбуки".
     */
    public void checkTitleByLink(String title) {
        wait.until(visibilityOfAllElements(headerAfterClick));
        Assertions.assertTrue(driver.getTitle().contains(title), "Ошибка, заголовок не найден, Ваш заголовок: " +title);
    }
}
