package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DropdownTests extends BaseTest {
    protected WebDriverWait wait;
    @FindBy(id="dropdown")
    WebElement dropDownElement;

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/dropdown");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void dropDownListingTest() {

        Select select = new Select(dropDownElement);

        List<WebElement> options = select.getOptions();
        List<String> namesOfOptions = new ArrayList<>();

        for (WebElement option : options) {
            namesOfOptions.add(option.getText());
            System.out.println(option.getText());
        }

        List<String> expectedNamesOfOptions = new ArrayList<>();
        expectedNamesOfOptions.add("Please select an option");
        expectedNamesOfOptions.add("Option 1");
        expectedNamesOfOptions.add("Option 2");
        assertEquals(expectedNamesOfOptions, namesOfOptions);

        String optionFirst = select.getFirstSelectedOption().getText();
        assertEquals("Please select an option", optionFirst);

        select.selectByIndex(1);
        String optionSelectedByIndex = select.getFirstSelectedOption().getText();
        assertEquals("Option 1", optionSelectedByIndex );

        select.selectByValue("2");
        String optionSelectedByValue = select.getFirstSelectedOption().getText();
        assertEquals("Option 2", optionSelectedByValue);
    }
}
