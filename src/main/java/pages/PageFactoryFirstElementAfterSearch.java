package pages;

import org.openqa.selenium.By;
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
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        List<WebElement> productList1 = driver.findElements(By.xpath("//span[@data-auto='snippet-title']"));
        String textBefore = productList1.getFirst().getText();
        headerSearch.click();
        headerSearch.sendKeys(textBefore);
        buttonSearch.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        PageFactory.initElements(driver, this);
        List<WebElement>productList2 = driver.findElements(By.xpath("//span[@data-auto='snippet-title']"));
        String textAfter = productList2.getFirst().getText();
        if (textAfter.equals(textBefore)) {
            System.out.println("Мои поздравления, отработано верно!");
        } else {
            System.out.println("Доработай " + textBefore + " нужен: " + textAfter);
        }
    }
}