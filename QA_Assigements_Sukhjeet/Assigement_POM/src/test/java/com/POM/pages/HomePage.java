package com.POM.pages;

import com.testBase.TestBase;
import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends TestBase {

    JavascriptExecutor js = (JavascriptExecutor) driver;
    static String value =prop.getProperty("device");
    @FindBy(xpath = "//*[@class='nav-search-field ']//input")
    WebElement searchBar;

    @FindBy(xpath = "//*[@id='nav-search-submit-text']//input[@type='submit']")
    WebElement submitButton;


    @FindBy(xpath = "//span[contains(text(),'Results')]")
    WebElement labelValue;


   @FindBy(xpath = "//span[contains(text(),'Results')]")
   WebElement dismissAlert;

    @FindBy(xpath = "(//div[@class='sg-col-inner']//div[contains(@class,'a-section')]//span)[2]")
    WebElement labelOfSearchString;


   @FindBy(xpath = "//span[contains(@class,'button-dismiss')]//span[@class='a-button-text']")
   WebElement dismissAlertBtn;

    @FindBy(xpath = "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a//span")
    WebElement descriptions;




/////////////////static generic methods/////////////////



    static void CheckAttibute(WebElement ele ,String data){

        String attribute = ele.getAttribute("type");
        Assert.assertEquals(attribute,data);
    }

    static void sendkeysIntoSearchBAr(WebElement ele){

      ele.sendKeys(value);
    }

    static void clickButton(WebElement ele){
        ele.click();
    }

    static boolean statusOfsearchedVAlue(WebElement ele,String expectedValue){
        String value = ele.getText();
        boolean status = value.equals(expectedValue);
        return  status;

    }
    static void waitForDissmissAlert(){
        // Wait for the alert to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the alert and dismiss it
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

    }








    ///////////////////////////////////////////////////////////test cases///////////////////////////

    public HomePage() {
        PageFactory.initElements(driver,this);
    }
    public String verifyTitle() {
        System.out.println("the titlr"+driver.getTitle());
        return driver.getTitle();
    }

//    BEfore click checkbox to verify is it unchecked
    public void verifySearchBarShouldBeAvailable(){

        CheckAttibute(searchBar,"text");
    }

    public void addKeywordForSearchAndclickButton() throws InterruptedException {
    sendkeysIntoSearchBAr(searchBar);
        clickButton(submitButton);
        Thread.sleep(3000);
        statusOfsearchedVAlue(labelValue,"Result");

    }

    public void chechRelevantProduct(){
        String actualValue = labelOfSearchString.getText();
        if(actualValue.equalsIgnoreCase(value) && descriptions.getText().contains(value)){
            System.out.println("result of relevant product");
        }

    }

    public void clickOnProduct(){
        List<WebElement> links = driver.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//a"));

        // Random object to generate random numbers
        Random random = new Random();

        // Define the number of random clicks you want to perform
        int numberOfClicks = 5;

        // Perform random clicks
        for (int i = 0; i < numberOfClicks; i++) {
            // Select a random link from the list
            WebElement randomLink = links.get(random.nextInt(links.size()));

            // Click the random link
            randomLink.click();
    }
    }

    public void verifyLinkOpenAnotherTab(){
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (the last one in the list)
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        // Validate the content of the new tab
        // Here we are checking if the new tab contains a specific element or text
        boolean isContentValid = driver.findElement(By.tagName("body")).getText().contains("Expected Content");

        // Print validation result
        if (isContentValid) {
            System.out.println("The link opened in a new tab and the content is valid.");
        } else {
            System.out.println("The link opened in a new tab but the content is not valid.");
        }
    }

}

