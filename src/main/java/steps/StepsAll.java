package steps;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.PageFactoryLaptopsMarket;
import pages.PageFactoryMainMarket;

import java.util.List;
/**
 * Класс все шаги.
 * @author Yan
 */
public class StepsAll {
    /** Инициализация статической переменной {@link WebDriver}  */
    private static WebDriver driver;
    /** Переход на сайт.
     * @see PageFactoryMainMarket#find
     * @param url - начальный url "<a href="https://market.yandex.ru/">...</a>"
     * @param currentDriver - экземпляр веб-драйвера
     */
    @Step("Переход на сайт: {url}")
    public static void openSite(String url, WebDriver currentDriver) {
        driver = currentDriver;
        driver.get(url);
        PageFactoryMainMarket pageFactoryMainMarket = new PageFactoryMainMarket(driver);
        pageFactoryMainMarket.find(url);
    }
    /** Проверка заголовка на странице.
     * @see helpers.Assertions#checkTitleByLink
     * @param title - ожидаемый заголовок страницы
     */
    @Step("Проверка перехода на страницу Ноутбуки {title}")
    public static void checkHeaderLaptops(String title) {
        Assertions assertions = new Assertions(driver);
        assertions.checkTitleByLink(title);
    }
    /** Ввод параметров поиска.
     * @param startPrice - цена "от"
     * @param endPrice - цена "до"
     * @param firstProduct - первый производитель
     * @param secondProduct - второй производитель
     * @see pages.PageFactoryLaptopsMarket#setParameters
     */
    @Step("Ввод заданных параметров для поиска")
    public static void setFinderParameters(String startPrice,  String endPrice,
                                           String firstProduct, String secondProduct) {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        pageFactoryLaptopsMarket.setParameters(startPrice, endPrice, firstProduct, secondProduct);
    }
    /** Проверка соответствия количества элементов.
     * @param findElement - ожидаемое количество сравниваемых элементов
     * @see helpers.Assertions#getNumbersOfElements
     */
    @Step("Проверка поиска количества элементов, с сравнением {findElement}")
    public static void checkNumbersOfElements(int findElement) {
        Assertions assertions = new Assertions(driver);
        assertions.getNumbersOfElements(findElement);
    }
    /** Проверка соответствия найденных элементов, заданным параметрам
     *  @param elements - наименование фирм-производителей
     *  @see Assertions#comparingElementsWithInputParameters
     */
    @Step("Проверка соответствия элементов заданному поиску со значениями {elements}")
    public static void checkAllElementsEqualsChoice(List<String> elements) {
        Assertions assertions = new Assertions(driver);
        assertions.comparingElementsWithInputParameters(elements);
    }
    /**
     * Возвращение к первому значению поиска и сравнение названия с результатами.
     */
    @Step("Возврат к первому значению поиска и сравнение названия с результатами ")
    public static void returnFirstElementToSearch() {
        PageFactoryLaptopsMarket pageFactoryLaptopsMarket = new PageFactoryLaptopsMarket(driver);
        pageFactoryLaptopsMarket.verifyFirstProductSearch();
    }
}





















