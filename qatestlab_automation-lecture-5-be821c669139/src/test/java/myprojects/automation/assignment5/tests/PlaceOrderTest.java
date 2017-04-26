package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import myprojects.automation.assignment5.utils.Properties;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {


public ProductData product;

    @Test(dependsOnMethods = "createNewOrder")
    public void checkSiteVersion() {
     driver.get(Properties.getBaseUrl());
       if(!isMobileTesting)//десктоп
       {
        //если отображается слайдер
        Assert.assertTrue(driver.findElement(By.xpath("//ui[@class ='carousel-inner'")).isDisplayed());
       }
    }

    @Test
    public void createNewOrder() {
        driver.get(Properties.getBaseUrl());

        // open random product
         actions.openRandomProduct();

        // save product parameters
         product = actions.getOpenedProductInfo();

        // add product to Cart and validate product information in the Cart
         actions.addToCart();

        // proceed to order creation, fill required information
         actions.firstStep();
         actions.secondStep();
         actions.threeStep();
         actions.fourStep();
         actions.assertProduct(product);




        // place new order and validate order summary

        // check updated In Stock value
    }



}
