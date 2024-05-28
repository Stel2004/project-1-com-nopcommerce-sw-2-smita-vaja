package selenium_week_2.testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium_week_2.browserfactory.BaseTest;

/**
 * 5. Write down the following test into ‘RegisterTest’
 * class
 * 1. userShouldNavigateToRegisterPageSuccessful
 * ly
 * * click on the ‘Register’ link
 * * Verify the text ‘Register’
 * 2. userSholdRegisterAccountSuccessfully *
 * click on the ‘Register’ link
 * * Select gender radio button
 * * Enter First name
 * * Enter Last name
 * * Select Day Month and Year
 * * Enter Email address
 * * Enter Password
 * * Enter Confirm password
 * * Click on REGISTER button
 * * Verify the text 'Your registration
 * completed’
 */
public class RegisterTest extends BaseTest {
    //Base url
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    //Browser open code
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    //Navigate to register page test
    public void userShouldNavigateToRegisterPageSuccessfully() {
        //Find register element and click on that
        WebElement registerElement = driver.findElement(By.linkText("Register"));
        registerElement.click();
        String expectedText = "Register";
        String actualText = driver.findElement(By.xpath("//h1[text()='Register']")).getText();
        //Comparing 2 strings
        Assert.assertEquals("User is not navigated to register page", expectedText, actualText);
    }

    @Test
    //Register successfully test
    public void userShouldRegisterAccountSuccessfully() {
        String userName = "" + (int) (Math.random() * Integer.MAX_VALUE);            //Create random username
        String emailID = "User" + userName + "@example.com";                        //Create random email Id
        //Find register element and click on that
        WebElement registerElement = driver.findElement(By.linkText("Register"));
        registerElement.click();
        driver.findElement(By.id("gender-female")).click();                                                                //Click on gender
        driver.findElement(By.id("FirstName")).sendKeys("Smita");                                                       //Enter first name
        driver.findElement(By.id("LastName")).sendKeys("Vaja");                                                     //Enter last name
        driver.findElement(By.name("DateOfBirthDay")).findElement(By.xpath("//option[text()='22']")).click();             //Select day
        driver.findElement(By.name("DateOfBirthMonth")).findElement(By.xpath("//option[text()='June']")).click();       //Select month
        driver.findElement(By.name("DateOfBirthYear")).findElement(By.xpath("//option[text()='1992']")).click();         //Select year
        driver.findElement(By.id("Email")).sendKeys(emailID);                                                            //Enter email
        driver.findElement(By.id("Password")).sendKeys("smita@123");                                                   //Enter password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("smita@123");                                            //Confirm password
        driver.findElement(By.id("register-button")).click();                                                            //Click on register button
        String expectedText = "Your registration completed";
        String actualText = driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText();
        //Comparing 2 strings
        Assert.assertEquals("User not registered successfully.", expectedText, actualText);
    }

    @After
    //Browser closing code
    public void tearDown() {
        closeBrowser();
    }
}
