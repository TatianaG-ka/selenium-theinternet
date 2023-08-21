package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class DynamicControlsTest extends BaseTest {
    public WebDriverWait wait;

    @FindBy(id="checkbox")
    WebElement checkbox;
    @FindBy(css="[onclick='swapCheckbox()']")
    WebElement removeButton;
    @FindBy(id="message")
    WebElement messageLabel;
    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
    @Test
    public void waitForDisappearingElement() {

        assertTrue(checkbox.isDisplayed());
        assertFalse(checkbox.isSelected());

        removeButton.click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.invisibilityOf(checkbox));

        assertEquals(messageLabel.getText(), "It's gone!");
    }
}
