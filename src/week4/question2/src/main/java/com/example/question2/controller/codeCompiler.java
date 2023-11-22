package com.example.question2.controller;

import com.sun.tools.jconsole.JConsoleContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class codeCompiler{
    EdgeOptions options;
    WebDriver driver;
    WebDriverWait wait;

    public codeCompiler(){
        System.setProperty("webdriver.edge.driver", "./msedgedriver.exe");
        this.options = new EdgeOptions();
        // options.addArguments("--headless");
        this.driver = new EdgeDriver(options);
        this.wait = new WebDriverWait(driver, Duration .ofSeconds(10));


    }
    @PostMapping("/compile")
    public ResponseEntity<String> compile() {
        try {
            driver.get("https://leetcode.com");

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            Thread.sleep(5000);

            jsExecutor.executeScript("window.scrollBy(0, 1500);");

            driver.switchTo().frame(0);

            WebElement javaButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='lang-btn-set']/button[text()='Java']")));
            javaButton.click();

            String code = "public class testing{ public static void main(String[] args){ System.out.println(\"testing\"); }}";

            WebElement codeEditor = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"CodeMirror-lines\"]//div")));
            wait.until(ExpectedConditions.visibilityOf(codeEditor));

            Actions actions = new Actions(driver);
            actions.moveToElement(codeEditor).click().build().perform();
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();

            actions.sendKeys(code).build().perform();


            WebElement runButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn btn-success run-code-btn']")));
            runButton.click();

            Thread.sleep(30000);

            return new ResponseEntity<>("Tested", HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Oops!!! Some Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }finally{
            driver.quit();
        }
    }
}