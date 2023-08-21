package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckboxTests extends BaseTest {
    protected WebDriverWait wait;
    @FindBy(xpath = "//*[@id='checkboxes']/input[1]")
    WebElement checkBox1;
    @FindBy(xpath = "//*[@id='checkboxes']/input[2]")
    WebElement checkBox2;

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/checkboxes");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testCheckboxes() {
        assertFalse(checkBox1.isSelected());
        assertTrue(checkBox2.isSelected());

        checkBox1.click();
        checkBox2.click();

        assertTrue(checkBox1.isSelected());
        assertFalse(checkBox2.isSelected());
    }
}
