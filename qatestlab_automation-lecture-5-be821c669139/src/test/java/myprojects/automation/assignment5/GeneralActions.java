package myprojects.automation.assignment5;


import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import myprojects.automation.assignment5.utils.Properties;
import myprojects.automation.assignment5.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openRandomProduct() {
        List<WebElement> products = driver.findElements(By.xpath("//h1[@class='h3 product-title']"));
        Random generate = new Random();

        products.get(generate.nextInt(products.size())).click();

    }

    public void addToCart() {
        driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']")).click();
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath("//a[@class='btn btn-primary']")))
                .click().build().perform();
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//div[@class = 'cart-detailed-totals']")));

        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
    }

    public void firstStep() {
        driver.findElement(By.xpath("//label[@class='radio-inline']")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(Properties.getFirstName());
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(Properties.getSecondName());
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Properties.getEmail());
        driver.findElement(By.xpath("//button[@name = 'continue']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name = 'address1']")));
    }

    public void secondStep() {
        driver.findElement(By.xpath("//input[@name = 'address1']")).sendKeys(Properties.getAdress());
        driver.findElement(By.xpath("//input[@name = 'postcode']")).sendKeys(Properties.getIndex());
        driver.findElement(By.xpath("//input[@name = 'city']")).sendKeys(Properties.getCity());
        driver.findElement(By.xpath("//button[@name = 'confirm-addresses']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='h6 carrier-name']")));
    }

    public void threeStep() {
        driver.findElement(By.xpath("//button[@name='confirmDeliveryOption']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='checkout-payment-step']")));
    }

    public void fourStep() {
        driver.findElement(By.xpath("//input[@id='payment-option-2']")).click();
        driver.findElement(By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']")).click();
        driver.findElement(By.xpath("//div[@id='payment-confirmation']//button[@type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class = 'material-icons done']")));
    }

    public void assertProduct(ProductData product) {
        Assert.assertEquals(DataConverter.parsePriceValue(driver.findElement(By.xpath("//div[@class = 'col-xs-5 text-sm-right text-xs-left']"))
                .getText()),product.getPrice());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'col-xs-2']"))
                .getText(),String.valueOf(product.getQty()));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-sm-4 col-xs-9 details']/span"))
                .getText().toLowerCase().contains(product.getName().toLowerCase()));
    }




    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        return saveProductData();
    }

    private ProductData saveProductData() {

       // WebElement wait = new WebDriverWait(driver,15)
               wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='container']")));
        String name;
        String price;
        if(!isMobileTesting) {
            name = driver.findElement(By.xpath("//h1[@class='h1']")).getText();
            price=driver.findElement(By.xpath("span[@class='product-price']/strong")).getText();
        }
        else {
            name = driver.findElement(By.xpath("//div[@class='product-line-grid-body col-md-4 col-xs-8']//a[@class='label']")).getText();
            price = driver.findElement(By.xpath("//span[@itemprop='price']")).getText();
        }

      //  String qty =  driver.findElement(By.xpath("//div[@class='product-quantities']//span")).getText();

        return new ProductData(name, 1, DataConverter.parsePriceValue(price));

    }
}
