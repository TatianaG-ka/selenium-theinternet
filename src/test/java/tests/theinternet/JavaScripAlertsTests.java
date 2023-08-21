package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class JavaScripAlertsTests extends BaseTest {
    protected WebDriverWait wait;

    @FindBy(css = "button[onclick='jsAlert()']")
    WebElement jsAlertButton;

    @FindBy(id = "result")
    WebElement result;
    @FindBy(css="button[onclick='jsConfirm()']")
    WebElement jsConfirmButton;

    @FindBy(css="button[onclick='jsPrompt()']")
    WebElement jsConfirmPromptButton;
    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void javaScriptAlertTest() {
        jsAlertButton.click();
        driver.switchTo().alert().accept();

        assertEquals(result.getText(), "You successfully clicked an alert");
    }

    @Test
    public void javaScriptAlertConfirmTest() {
        jsConfirmButton.click();
        driver.switchTo().alert().accept();
        assertEquals(result.getText(), "You clicked: Ok");
        jsConfirmButton.click();
        driver.switchTo().alert().dismiss();

        assertEquals(result.getText(), "You clicked: Cancel");
    }

    @Test
    public void javaScriptAlertPromptTest() {
        jsConfirmPromptButton.click();
        String typedText = "Selenium is cool";
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(typedText);
        alert.accept();

        assertEquals(result.getText(), "You entered: " + typedText);
    }


}

