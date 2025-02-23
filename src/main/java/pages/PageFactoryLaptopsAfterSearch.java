package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageFactoryLaptopsAfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

public PageFactoryLaptopsAfterSearch(WebDriver driver) {
    this.driver = driver;
}

public void comparingElementsWithInputParameters(List<WebElement> productList, String firstProduct, String secondProduct) {
    wait.until(visibilityOfAllElements(productList));
    for (WebElement product : productList) {
        String name = product.getText();
        Assertions.assertTrue(name.contains(firstProduct) || name.contains(secondProduct), "Бренд указан не верно: " + name + " - указан бренд");

    }
  }
}
