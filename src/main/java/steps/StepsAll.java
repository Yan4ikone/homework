package steps;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;

public class StepsAll {

    private static WebDriver driver;

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, WebDriver driver) {
        PageFactoryMainMarket pageFactoryMainMarket = new PageFactoryMainMarket(driver);
        pageFactoryMainMarket.find(url);
    }

    @Step("Проверка перехода на страницу Ноутбуки {title}")
    public static void checkHeaderLaptops(String title) {
        Assertions assertions = new Assertions();
        assertions.checkTitleByLink(title);
    }

    @Step("Ввод заданных параметров для поиска {startPrice}")
    public static void setFinderParameters(String startPrice,  String endPrice,
                                           String firstProduct, String secondProduct) {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        pageFactoryLaptopsMarket.setParameters(startPrice, endPrice, firstProduct, secondProduct);
    }

    @Step("Проверка поиска количества элементов больше 12")
    public static void checkNumbersOfElements(String findElement) {
        Assertions assertions = new Assertions();
        assertions.getNumbersOfElements(findElement);
    }

    @Step("Проверка соответствия элементов заданному поиску")
    public static void checkAllElementsEqualsChoice() {
        Assertions assertions = new Assertions();
        assertions.comparingElementsWithInputParameters();
    }

    @Step("Возврат к первому значению поиска и сравнение названия с результатами ")
    public static void returnFirstElementToSearch() {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        pageFactoryLaptopsMarket.verifyFirstProductSearch();
    }
}





















