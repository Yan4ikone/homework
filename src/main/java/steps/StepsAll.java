package steps;

import helpers.DataProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageFactoryLaptopsAfterSearch;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;

import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class StepsAll {

    private static WebDriverWait wait;
    private static WebDriver driver;

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, String title, WebDriver currentDriver) {
        driver = currentDriver;
        driver.get(url);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs(title));
    }

    @Step("Проверка перехода на страницу Ноутбуки {title}")
    public static void checkHeaderLaptops(String title) {
        PageFactoryMainMarket pageFactoryMainMarket = new PageFactoryMainMarket(driver);
        pageFactoryMainMarket.checkTitleByLink(title);
    }

    @Step("Проверка поиска количества элементов больше 12 {title}")
    public static boolean checkNumbersOfElements() {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        return pageFactoryLaptopsMarket.getNumbersOfElements();

    }

    @Step("Проверка соответствия элементов заданному поиску")
    public static boolean checkAllElementsEqualsChoice() {
        PageFactoryLaptopsAfterSearch pageFactoryLaptopsAfterSearch = new PageFactoryLaptopsAfterSearch(driver);
        return pageFactoryLaptopsAfterSearch.comparingElementsWithInputParameters();
    }
}





















