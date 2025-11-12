package com.nhanthanhle.seminar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class HerokuPageTest {
    private static WebDriver myDriver;
    @BeforeAll
    public static void setUpWebDriver() {
        myDriver = new EdgeDriver();
        myDriver.get("https://the-internet.herokuapp.com/login");
        myDriver.manage().window().fullscreen();
        
    }



    @Test
    void shouldLoginSuccessful() throws InterruptedException {
        // locators 
        WebElement usrnameField = myDriver.findElement(By.cssSelector("#username"));
        WebElement pwdField = myDriver.findElement(By.cssSelector("#password"));
        WebElement loginBtn = myDriver.findElement(By.cssSelector("button[type='submit']"));

        usrnameField.sendKeys("tomsmith");
        pwdField.sendKeys("SuperSecretPassword!");
        loginBtn.click();


        // verify
        String myUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = myDriver.getCurrentUrl();


        Assertions.assertEquals(myUrl, actualUrl);
        Thread.sleep(10000);
    }

    @Test void shouldLoginUnSuccessful() {
        WebElement usrnameField = myDriver.findElement(By.cssSelector("#username"));
        WebElement pwdField = myDriver.findElement(By.cssSelector("#password"));
        WebElement loginBtn = myDriver.findElement(By.cssSelector("button[type='submit']"));

        usrnameField.sendKeys("abc");
        pwdField.sendKeys("abc");
        loginBtn.click();


        // verify
        String myUrl = "https://the-internet.herokuapp.com/login";
        String actualUrl = myDriver.getCurrentUrl();


        Assertions.assertEquals(myUrl, actualUrl);

    }


    @AfterAll
    public static void cleanUpDriver() {
        myDriver.quit(); // giai phong
    }
}
// .jar