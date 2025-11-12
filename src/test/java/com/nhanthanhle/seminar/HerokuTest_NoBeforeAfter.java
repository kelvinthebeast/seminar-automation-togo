package com.nhanthanhle.seminar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class HerokuTest_NoBeforeAfter {
    private WebDriver myDriver; // Không dùng static: phải tạo lại driver mỗi lần chạy test mới

    @Test
    void shouldLoginSuccessful() {
        // Tạo driver trực tiếp trong test
        myDriver = new EdgeDriver();
        myDriver.get("https://the-internet.herokuapp.com/login");
        myDriver.findElement(By.id("username")).sendKeys("tomsmith");
        myDriver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        myDriver.findElement(By.cssSelector("button[type='submit']")).click();

        Assertions.assertEquals(
            "https://the-internet.herokuapp.com/secure",
            myDriver.getCurrentUrl()
        );

        // Không đóng driver
    }

    @Test
    void shouldLoginUnsuccessful() {
        // Tạo driver lại lần nữa
        myDriver = new EdgeDriver();
        myDriver.get("https://the-internet.herokuapp.com/login");
        myDriver.findElement(By.id("username")).sendKeys("abc");
        myDriver.findElement(By.id("password")).sendKeys("abc");
        myDriver.findElement(By.cssSelector("button[type='submit']")).click();

        Assertions.assertEquals(
            "https://the-internet.herokuapp.com/login",
            myDriver.getCurrentUrl()
        );
        // Không đóng driver
    }
}
