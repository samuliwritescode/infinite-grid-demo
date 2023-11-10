import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeIT {

    private RemoteWebDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions().addArguments(
                "--ignore-certificate-errors",
                "--ignore-ssl-errors=yes"
        );
        driver = new RemoteWebDriver(new URL("http://localhost:4444"),
                options
        );
        driver.get("http://demo:8080/");
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }

    @Test
    public void checkThatGridsArePresent() {
        WebElement element = new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("infinite-grid")));

        assertTrue(element.isDisplayed());
        assertEquals(4, driver.findElements(By.tagName("infinite-grid")).size());
    }
}
