package pages;

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

import java.util.List;

import static helpers.Properties.configProperties;

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
        JavascriptExecutor scroll = (JavascriptExecutor)driver;
        scroll.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        List<WebElement> productList1 = driver.findElements(By.xpath("//span[@data-auto='snippet-title']"));

        for (WebElement product : productList1){
            if (product.isDisplayed()) {
                String textBefore = product.getText();
                headerSearch.click();
                headerSearch.sendKeys(textBefore);
                buttonSearch.click();
                PageFactory.initElements(driver, this);
                wait.until(ExpectedConditions.visibilityOfAllElements(productList));
                List<WebElement>productList2 = driver.findElements(By.xpath("//span[@data-auto='snippet-title']"));
                for (WebElement product2 : productList2) {
                    if (product2.getText().contains(textBefore)) {
                        System.out.println("Всё тип-топ");
                    } else {
                        System.out.println("Получили: " + textBefore + "не то" );
                    }
                }
                break;
                }
        }
    }
}

