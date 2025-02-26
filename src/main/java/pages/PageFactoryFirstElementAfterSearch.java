package pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static helpers.Properties.configProperties;

public class PageFactoryFirstElementAfterSearch {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private String savedProductTitle;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    private List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//*[@id='header-search']")
    private WebElement headerSearch;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Найти')]")
    private WebElement buttonSearch;

    public PageFactoryFirstElementAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        PageFactory.initElements(driver, this);
    }
    //Функция для поднятия страницы вверх до заголовка
    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
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

    public void verifyFirstProductSearch() {
        scrollToTop();
        savedProductTitle = getFirstVisibleProductTitle();
        searchForProduct(savedProductTitle); // Используем полное название для поиска
        verifySearchResults(savedProductTitle); // Передаем полное название для сравнения
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
        // Логируем для отладки
        System.out.println("Полный поисковый запрос: " + searchQuery);
        System.out.println("Сокращенное название для сравнения: " + shortSearchTitle);
        System.out.println("Найденные товары: " + String.join(" ", visibleProducts));

        boolean found = visibleProducts.stream()
                .anyMatch(product -> product.equalsIgnoreCase(shortSearchTitle));

        Assertions.assertTrue(found, String.format("Товар '%s' не найден в результатах поиска. Полный поисковый запрос: %s Найденные товары: %s", shortSearchTitle, searchQuery, String.join(" ", visibleProducts)));
    }
}

