package org.example;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test (dataProvider = "data")
    public void testSiteTitle(String site) {
        driver.get("https://www."+site+".com");
        String title = driver.getTitle().toLowerCase();
        assertTrue(title.contains(site), "Title should contain "+site);
    }

    @DataProvider(name = "data")
    public Object[] getData() {
        return new Object [] {"google","facebook" };
    }

    @Test
    public void testGoogleTitleFail1() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("Firefox"), "Title should contain Firefox");
    }

    @Test
    public void testGoogleTitleFail2() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("NotGoogleFireFox"), "Title should contain Google");
    }

    @Test
    public void testScrollAndWaitForElement() {
        driver.get("https://httpbin.org/delay/3");
        
        // Wait for page to load after delay
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement bodyElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        
        // Navigate to a page with more content
        driver.get("https://www.wikipedia.org");
        
        // Various assert statements for page validation
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.wikipedia.org/", "URL should match expected");
        Assert.assertTrue(currentUrl.contains("wikipedia"), "URL should contain wikipedia");
        Assert.assertFalse(currentUrl.contains("google"), "URL should not contain google");
        
        String pageTitle = driver.getTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertNotEquals(pageTitle, "", "Page title should not be empty");
        
        // Wait for search box to be visible
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        
        // Element assertions
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be visible");
        Assert.assertTrue(searchBox.isEnabled(), "Search box should be enabled");
        Assert.assertEquals(searchBox.getTagName(), "input", "Element should be input tag");
        Assert.assertNotNull(searchBox.getAttribute("placeholder"), "Placeholder should exist");
        
        // Scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        // Wait for footer element to be visible
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("footer")));
        
        // More element assertions
        Assert.assertTrue(footer.isDisplayed(), "Footer should be visible");
        Assert.assertNotEquals(footer.getText(), "", "Footer should have text content");
        
        // Scroll back to search box
        js.executeScript("arguments[0].scrollIntoView(true);", searchBox);
        
        // Final validations
        Assert.assertTrue(searchBox.getLocation().getY() >= 0, "Search box should be in viewport");
        Assert.assertTrue(driver.getPageSource().length() > 1000, "Page should have substantial content");
    }

    @Test
    public void testFlipkartDiscountedProducts() throws Exception {
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        int productCount = 0;
        // Assert that we found at least some discounted products
        String xPath = "//*[contains(text(),'% Off')]/parent::*/child::*[not(contains(text(),'%'))]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        List<WebElement> li = driver.findElements(By.xpath(xPath));
        for(WebElement e: li) {
            System.out.println("Name "+e.getText());
            productCount++;
        }
        System.out.println("Total Products "+ productCount);
    }

    @Test
    public void testGetDiscountedItems() {
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/buttons");

        Actions actions = new Actions(driver);

        // Right-click
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickBtn).perform();

        // Double-click
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickBtn).perform();

        // Hover over element
        driver.get("https://demoqa.com/tool-tips");
        WebElement hoverBtn = driver.findElement(By.id("toolTipButton"));
        actions.moveToElement(hoverBtn).perform();

        // Drag and drop
        driver.get("https://demoqa.com/droppable");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(source, target).perform();
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
