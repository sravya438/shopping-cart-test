package fujitsu.shopping;

import java.lang.annotation.ElementType;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.SearchContext;
import org.junit.Assert;

import io.github.sukgu.*;


public class ShoppingTest {
	
@Test
public void testMyshoppingCart() {
	WebDriver driver = new ChromeDriver();

    // Open a website
    driver.get("https://shop.polymer-project.org/");
    driver.manage().window().maximize();
    
    Shadow shadow = new Shadow(driver);
    WebElement root = driver.findElement(By.tagName("shop-app"));	
   
    WebElement ironPages = shadow.findElement(root, "iron-pages[role='main']");
    WebElement shotButton =  shadow.findElement(root, "div:nth-child(2) > shop-button > a");
    shotButton.click();
    //Shop list
    WebElement shopList = shadow.findElement(root, "shop-list[name='list']");
    WebElement firstItem = shadow.findElement(shopList,"ul > li > a");
    firstItem.click();

    //Item page
    WebElement shopDetail = shadow.findElement(ironPages, "shop-detail[name='detail']");
    WebElement sizeSelect  = shadow.findElement(shopDetail, "select[id='sizeSelect']");
    Select sizeDropDown = new Select(sizeSelect);
    sizeDropDown.selectByValue("XL");
    
    WebElement quantitySelect  = shadow.findElement(shopDetail, "select[id='quantitySelect']");
    Select quantityDropDown = new Select(quantitySelect);
    quantityDropDown.selectByValue("3");
    
    WebElement addToCart  = shadow.findElement(shopDetail, "shop-button");
    addToCart.click();
    
    //Cart added popup
    WebElement cartAddedPopUp = shadow.findElement(root, "shop-cart-modal");
    
    WebElement cartAddedText =  shadow.findElement(cartAddedPopUp, "div:nth-child(1)");    
    WebElement viewCart =  shadow.findElement(cartAddedPopUp, "div:nth-child(2) > shop-button:nth-child(1) > a[href='/cart']");
    WebElement checkout =  shadow.findElement(cartAddedPopUp, "div:nth-child(2) > shop-button:nth-child(2) > a[href='/checkout']");

    Assert.assertEquals("Cart added test not matched!", "Added to cart", cartAddedText.getText());
    Assert.assertEquals("View Cart is not available!", "VIEW CART", viewCart.getText());
    Assert.assertEquals("Checkout not available!", "CHECKOUT", checkout.getText());
    checkout.click();

}


}

 
       