package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageFactoryFirstElementAfterSearch;
import pages.PageFactoryLaptopsAfterSearch;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;

public class StepsAll {

    private static WebDriverWait wait;
    private static WebDriver driver;

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, WebDriver driver) {
        PageFactoryMainMarket pageFactoryMainMarket = new PageFactoryMainMarket(driver);
        pageFactoryMainMarket.find(url);
    }

    @Step("Проверка перехода на страницу Ноутбуки {title}")
    public static void checkHeaderLaptops(String title, WebDriver driver) {
        PageFactoryMainMarket pageFactoryMainMarket = new PageFactoryMainMarket(driver);
        pageFactoryMainMarket.checkTitleByLink(title);
    }

    @Step("Ввод заданных параметров для поиска {title}")
    public static void setFinderParameters(String startPrice, String endPrice,
                                           String firstProduct, String secondProduct,
                                           WebDriver driver) {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        pageFactoryLaptopsMarket.setParameters(startPrice, endPrice, firstProduct, secondProduct);
    }

    @Step("Проверка поиска количества элементов больше 12 {title}")
    public static boolean checkNumbersOfElements(WebDriver driver) {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        return pageFactoryLaptopsMarket.getNumbersOfElements();
    }

    @Step("Проверка соответствия элементов заданному поиску {title}")
    public static boolean checkAllElementsEqualsChoice(WebDriver driver) {
        PageFactoryLaptopsAfterSearch pageFactoryLaptopsAfterSearch = new PageFactoryLaptopsAfterSearch(driver);
        return pageFactoryLaptopsAfterSearch.comparingElementsWithInputParameters();
    }

   @Step("Возврат к первому значению поиска {title}")
    public static void returnFirstElementToSearch(WebDriver driver) {
        PageFactoryFirstElementAfterSearch pageFactoryFirstElementAfterSearch = new PageFactoryFirstElementAfterSearch(driver);
        pageFactoryFirstElementAfterSearch.getFirstElement();
    }
}





















