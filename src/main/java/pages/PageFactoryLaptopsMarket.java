package pages;

import helpers.ConfigProperties;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static helpers.Properties.configProperties;
/**
 * Класс страницы ноутбуки ЯндексМаркет.
 * @author Yan
 */
public class PageFactoryLaptopsMarket {
    /** Переменная {@link WebDriver} */
    private WebDriver driver;
    /** Переменная ожидания {@link WebDriverWait} */
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'range-filter-field-glprice') and contains(@id, '_min')]")
    WebElement buttonStartPrice;

    @FindBy(how = How.XPATH, using = "//input[contains(@id, 'range-filter-field-glprice') and contains(@id, '_max')]")
    WebElement buttonEndPrice;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Показать всё')]")
    WebElement buttonShowAll;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Найти']")
    WebElement searchField;

    @FindBy(how = How.XPATH, using = "//span[text()='HP']")
    WebElement buttonHewlett;

    @FindBy(how = How.XPATH, using = "//span[text()='Lenovo']")
    WebElement buttonLenovo;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title' " +
            "and not(ancestor::*[@data-auto='searchIncut'])]")
    List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//*[@id='/marketfrontSerpLayout']")
    List<WebElement> productChoice;

    @FindBy(how = How.XPATH, using = "//*[@id='header-search']")
    private WebElement headerSearch;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Найти')]")
    private WebElement buttonSearch;
    /**
     * Конструктор процедуры инициализации {@link WebDriver}.
     * @param driver - передаваемое значение {@link WebDriver}
     * @see ConfigProperties#timeOut() параметры конфигурации
     */
    public PageFactoryLaptopsMarket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        PageFactory.initElements(driver, this);
    }

    /** Функция ввода значений по параметрам.
     * @param startPrice - цена "от"
     * @param endPrice - цена "до"
     * @param firstProduct - первый производитель
     * @param secondProduct - второй производитель
     */
    public void setParameters(String startPrice, String endPrice,
                              String firstProduct, String secondProduct) {
        buttonStartPrice.sendKeys(startPrice);
        buttonEndPrice.sendKeys(endPrice);
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        buttonShowAll.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchField));
        searchField.sendKeys(firstProduct);
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        this.buttonHewlett = driver.findElement(By.xpath(STR."//span[text()='\{firstProduct}']"));
        buttonHewlett.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        searchField.clear();
        searchField.sendKeys(secondProduct);
        this.buttonHewlett = driver.findElement(By.xpath(STR."//span[text()='\{secondProduct}']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonLenovo));
        buttonLenovo.click();
    }
    /**
     * Функция поиска первого товара
     */
    public void verifyFirstProductSearch() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        String savedProductTitle = getFirstVisibleProductTitle();
        searchForProduct(savedProductTitle); // Используем полное название для поиска
        verifySearchResults(savedProductTitle); // Передаем полное название для сравнения
    }
    /**
     * Функция возвращения заголовка первого товара
     */
    private String getFirstVisibleProductTitle() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return productList.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .map(WebElement::getText)
                .orElseThrow(() -> new NoSuchElementException("Видимый товар не найден на странице"));
    }
    /**
     * Функция для ввода названия товара в поисковую строку
     * @param  fullTitle - наименование товара поиска
     */
    private void searchForProduct(String fullTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(headerSearch));
        headerSearch.click();
        headerSearch.sendKeys(fullTitle); // Используем полное название для поиска
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearch));
        buttonSearch.click();
    }
    /**
     * Функция парсинга заголовка
     * @param fullTitle - полное наименование заголовка {@link }
     */
    private String extractShortTitle(String fullTitle) {
        String titleBeforeComma = fullTitle.split(",")[0];
        String[] words = titleBeforeComma.split(" +");
        String[] firstFourWords = Arrays.copyOf(words, Math.min(5, words.length));
        return String.join(" ", firstFourWords);
    }
    /**
     * Функция сравнения товаров на странице после поиска с поисковым заголовком
     */
    private void verifySearchResults(String searchQuery) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        // Получаем короткое название из строки поиска для сравнения
        String shortSearchTitle = extractShortTitle(searchQuery);
        List<String> visibleProducts = productList.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .map(this::extractShortTitle) // Получаем короткие названия для результатов
                .toList();
        boolean found = visibleProducts.stream()
                .anyMatch(product -> product.equalsIgnoreCase(shortSearchTitle));

        Assertions.assertTrue(found, String.format("Товар '%s' не найден в результатах поиска. " +
                "Полный поисковый запрос: %s Найденные товары: %s",
                shortSearchTitle, searchQuery, String.join(" ", visibleProducts)));
    }
}



