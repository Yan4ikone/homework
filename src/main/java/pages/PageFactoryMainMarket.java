package pages;

import helpers.ConfigProperties;
import helpers.Properties;
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

/**
 * Класс начальной страницы ЯндексМаркет.
 * @author Yan
 */
public class PageFactoryMainMarket {
/** Переменная {@link WebDriver} */
private WebDriver driver;
/** Переменная ожидания {@link WebDriver} */
private WebDriverWait wait;
/** Переменная действия {@link WebDriver} */
private Actions actions;
    /** Кнопка каталога типа {@link WebElement}, по типу поиска XPath */
@FindBy(how = How.XPATH, using = "//div[@data-zone-name ='catalog']")
    WebElement buttonCatalog;
    /** Кнопка ноутбуки типа {@link WebElement}, по типу поиска XPath */
@FindBy(how = How.XPATH, using = "//a[text()='Ноутбуки']")
    WebElement buttonLaptops;
    /** Вкладка электроника типа {@link WebElement}, по типу поиска XPath */
@FindBy(how = How.XPATH, using = "//li[@data-zone-name='category-link']" +
        "/a[@href='https://market.yandex.ru/special/electronics_dep']")
    WebElement electronicTab;
    /** Всплывающее окно типа {@link WebElement}, по типу поиска XPath */
@FindBy(how = How.XPATH, using = "//div[@data-baobab-name='login_popup']")
    WebElement popup;
    /** Область перекрытия страницы, при всплывающем окне типа {@link WebElement}, по типу поиска XPath */
@FindBy(how = How.XPATH, using = "//div[@aria-label='Закрыть']")
    WebElement closeArea;

    /**
     * Конструктор процедуры инициализации {@link WebDriver}.
     * @param driver - передаваемое значение {@link WebDriver}
     * @see ConfigProperties#timeOut() параметры конфигурации
     */
    public PageFactoryMainMarket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    /**
     * Функция перехода на страницу {@link pages.PageFactoryLaptopsMarket}
     * @param url - передаваемый URL {@link steps.StepsAll#openSite(String, WebDriver)}
     * Функция включает в себя:
     * Открытие сайта {@link ConfigProperties#baseUrl()};
     * Нажатие на кнопку открытия каталога {@link pages.PageFactoryMainMarket#buttonCatalog};
     * Наведение на вкладку электроника {@link pages.PageFactoryMainMarket#electronicTab};
     * Нажатие на ссылку ноутбуки {@link pages.PageFactoryMainMarket#buttonLaptops}.
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
