package selenium_week_2.testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium_week_2.browserfactory.BaseTest;

/**
 * 3. Write down the following test into ‘LoginTest’ class
 *  * 1. userShouldNavigateToLoginPageSuccessfully *
 *  * click on the ‘Login’ link
 *  * * Verify the text ‘Welcome, Please Sign In!’
 *  * 2. userShouldLoginSuccessfullyWithValidCredentials
 *  * * click on the ‘Login’ link
 *  * * Enter valid username
 *  * * Enter valid password
 *  * * Click on ‘LOGIN’ button
 *  * * Verify the ‘Log out’ text is display
 *  * 3. verifyTheErrorMessage
 *  * * click on the ‘Login’ link
 *  * * Enter invalid username
 *  * * Enter invalid password
 *  * * Click on Login button
 *  * * Verify the error message ‘Login was unsuccessful.
 *  * Please correct the errors and try again. No customer account found’
 */
public class LoginTest extends BaseTest {
    static String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setUpBrowser(){
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){
        // find login link and click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        String expectedText = "Welcome, Please Sign In!";
        WebElement welcomeTextElement = driver.findElement(By.xpath("//h1"));
        //WebElement welcomeTextElement = driver.findElement(By.xpath("//h1[text() = '" + expectedText + "']"));
        String actualText = welcomeTextElement.getText();
        Assert.assertEquals(expectedText,actualText);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        // Find the user should log in successfully with valid credentials
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("Email")).sendKeys("smitavaja.uk@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("smita@123");
        driver.findElement(By.xpath("//button[text() = 'Log in']")).click();   // Find the Login button element and click
        String expectedLoginText = "Log out"; //expected Result
        WebElement logoutText = driver.findElement(By.linkText("Log out"));
        String actualLoginText = logoutText.getText(); //Actual Result
        Assert.assertEquals("Login user is not valid", expectedLoginText, actualLoginText); //Compare both Resul
    }

    @Test
    public void verifyTheErrorMessage(){
        // To verify the error message
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.id("Email")).sendKeys("Prime123@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Prime@123");
        driver.findElement(By.xpath("//button[text() = 'Log in']")).click();

        // Find the error message
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class = 'message-error validation-summary-errors']")).getText();
        Assert.assertEquals("Error message is not displayed", expectedErrorMessage, actualErrorMessage);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
