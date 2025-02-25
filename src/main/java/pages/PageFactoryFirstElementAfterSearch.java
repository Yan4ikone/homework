package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Properties.configProperties;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageFactoryFirstElementAfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//span[@data-auto='snippet-title']")
    List<WebElement> productList;

    @FindBy(how = How.XPATH, using = "//*[@id='header-search']")
    WebElement headerSearch;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Найти')]")
    WebElement buttonSearch;


    public PageFactoryFirstElementAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, configProperties.timeOut());
        PageFactory.initElements(driver, this);
    }

    public void getFirstElement() {
        wait.until(visibilityOfAllElements(productList));
        headerSearch.click();
        headerSearch.sendKeys(productList.getFirst().getText());
        String textProduct = productList.getFirst().getText();
        buttonSearch.click();
        wait.until(visibilityOfAllElements(productList));
        if (productList.getFirst().getText().equals(textProduct)) {
            System.out.println("Мои поздравления, отработано верно!");
        } else {
            System.out.println("Доработай" + textProduct);
        }
    }
}