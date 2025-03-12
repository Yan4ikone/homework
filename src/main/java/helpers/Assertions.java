package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class Assertions {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor scroll;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Ноутбуки')]")
    WebElement headerAfterClick;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title' and not(ancestor::*[@data-auto='searchIncut'])]")
    List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//span[text()='Вперёд']")
    WebElement nextButton;

    public Assertions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        this.scroll = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    /**
     * Проверка заголовка после перехода в раздел "Ноутбуки".
     * @param title - заголовок
     */
    public void checkTitleByLink(String title) {
        wait.until(visibilityOfAllElements(headerAfterClick));
        org.junit.jupiter.api.Assertions.assertTrue(driver.getTitle().contains(title), STR."Ошибка, заголовок не найден, Ваш заголовок: \{title}");
    }
    /**
     * Проверка корректного отображения числа элементов на странице
     * @param findElement - сравниваемое количество элементов
     */
    public void getNumbersOfElements(int findElement) {
        wait.until(visibilityOfAllElements(productList));
        org.junit.jupiter.api.Assertions.assertTrue(productList.size() > findElement);
    }
    /**
     * Проверка корректного отображения всех элементов на странице
     * @param elements - сравниваемые элементы
     */
    public void comparingElementsWithInputParameters(List<String> elements) {
        scroll.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        while (!driver.findElements(By.xpath("//button[text()='Вперёд']")).isEmpty()) {  // Если кнопка "Вперёд" видна, выполняем прокрутку
            wait.until(ExpectedConditions.
                    presenceOfElementLocated(By.xpath("//span[@data-auto='snippet-title' " +
                            "and not(ancestor::*[@data-auto='searchIncut'])]")));
            scroll.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Прокрутка вниз
        }
        org.junit.jupiter.api.Assertions.assertTrue(productList.stream()
                .map(WebElement::getText)
                .allMatch(title -> elements.stream().anyMatch(title::contains)));
    }

}
