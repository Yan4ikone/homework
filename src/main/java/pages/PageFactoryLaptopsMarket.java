package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static helpers.Properties.configProperties;

public class PageFactoryLaptopsMarket {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    private String savedProductTitle;

    @FindBy(how = How.XPATH, using = "//*[@id='range-filter-field-glprice_25563_min']")
    WebElement buttonStartPrice;

    @FindBy(how = How.XPATH, using = "//*[@id='range-filter-field-glprice_25563_max']")
    WebElement buttonEndPrice;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Показать всё')]")
    WebElement buttonShowAll;

    @FindBy(how = How.XPATH, using = "//*[@placeholder='Найти']")
    WebElement searchField;

    @FindBy(how = How.XPATH, using = "//span[text()='HP']")
    WebElement buttonHewlett;

    @FindBy(how = How.XPATH, using = "//span[text()='Lenovo']")
    WebElement buttonLenovo;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//*[@id='/marketfrontSerpLayout']")
    List<WebElement> productChoice;

    @FindBy(how = How.XPATH, using = "//*[@id='header-search']")
    private WebElement headerSearch;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Найти')]")
    private WebElement buttonSearch;



    public PageFactoryLaptopsMarket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    //Ввод заданных параметров поиска
    public void setParameters(String startPrice, String endPrice,
                              String firstProduct, String secondProduct) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='range-filter-field-glprice_25563_min']")));
        buttonStartPrice.click();
        buttonStartPrice.sendKeys(startPrice);
        buttonEndPrice.click();
        buttonEndPrice.sendKeys(endPrice);
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        buttonShowAll.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchField));
        searchField.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchField));
        searchField.sendKeys(firstProduct);
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        buttonHewlett.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productChoice));
        searchField.clear();
        searchField.sendKeys(secondProduct);
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonLenovo));
        buttonLenovo.click();
    }


    //Функция для поднятия страницы вверх до заголовка
    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void verifyFirstProductSearch() {
        scrollToTop();
        savedProductTitle = getFirstVisibleProductTitle();
        searchForProduct(savedProductTitle); // Используем полное название для поиска
        verifySearchResults(savedProductTitle); // Передаем полное название для сравнения
    }
    //Возвращаем заголовок первого видимого товара
    private String getFirstVisibleProductTitle() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return productList.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .map(WebElement::getText)
                .orElseThrow(() -> new NoSuchElementException("Видимый товар не найден на странице"));
    }
    //Функция для ввода названия товара в поисковую строку
    private void searchForProduct(String fullTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(headerSearch));
        headerSearch.click();
        headerSearch.clear();
        headerSearch.sendKeys(fullTitle); // Используем полное название для поиска
        wait.until(ExpectedConditions.elementToBeClickable(buttonSearch));
        buttonSearch.click();
    }

    //Сокращаем заголовок до первых 5 слов после запятой
    private String extractShortTitle(String fullTitle) {
        String titleBeforeComma = fullTitle.split(",")[0];
        String[] words = titleBeforeComma.split("\s+");
        String[] firstFourWords = Arrays.copyOf(words, Math.min(5, words.length));
        return String.join(" ", firstFourWords);
    }
    //Сравниваем товары на странице после поиска с поисковым заголовком
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

        Assertions.assertTrue(found, String.format("Товар '%s' не найден в результатах поиска. Полный поисковый запрос: %s Найденные товары: %s", shortSearchTitle, searchQuery, String.join(" ", visibleProducts)));
    }


}



