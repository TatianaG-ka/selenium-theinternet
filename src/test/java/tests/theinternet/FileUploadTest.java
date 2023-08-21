package tests.theinternet;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class FileUploadTest extends BaseTest {
    protected WebDriverWait wait;

    @FindBy(id = "file-upload")
    WebElement uploadFile;

    @FindBy(id = "file-submit")
    WebElement uploadButton;
    @FindBy(id = "uploaded-files")
    WebElement uploadedFiles;

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://the-internet.herokuapp.com/upload");

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void fileUploadTest() {
        uploadFile.sendKeys("C:/Stopki/testFile.txt");
        uploadButton.click();
        assertEquals(uploadedFiles.getText(), "testFile.txt");
    }
}
