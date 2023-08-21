package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class HoverTests extends BaseTest {

    protected WebDriverWait wait;

    @FindBy(xpath = "//*[@id='content']/div/div[1]")
    WebElement user1;

    @FindBy(xpath = "//*[@id='content']/div/div[2]")
    WebElement user2;

    @FindBy(xpath = "//*[@id='content']/div/div[3]")
    WebElement user3;

    @FindBy(xpath = "//div[1]/div/h5")
    WebElement firstUserCaption ;

    @FindBy(xpath = "//div[2]/div/h5")
    WebElement secondUserCaption ;

    @FindBy(xpath = "//div[3]/div/h5")
    WebElement thirdUserCaption ;

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/hovers");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void mouseHoverTests() {
        Actions actions = new Actions(driver);
        actions.moveToElement(user1).build().perform();

        assertEquals(firstUserCaption.getText(), "name: user1");
        assertEquals(secondUserCaption.getText(), "");
        assertEquals(thirdUserCaption.getText(), "");

        actions.moveToElement(user2).build().perform();
        assertEquals(firstUserCaption.getText(), "");
        assertEquals(secondUserCaption.getText(), "name: user2");
        assertEquals(thirdUserCaption.getText(), "");

        actions.moveToElement(user3).build().perform();
        assertEquals(firstUserCaption.getText(), "");
        assertEquals(secondUserCaption.getText(), "");
        assertEquals(thirdUserCaption.getText(), "name: user3");

    }
}
