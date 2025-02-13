package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class EditProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseURL:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void userCreateAndEditProduct(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys("Skibidi");
        quantityInput.sendKeys("20");
        submitButton.click();

        driver.get(baseUrl + "/product/list");

        WebElement editLink = driver.findElement(By.linkText("Edit"));
        editLink.click();

        nameInput = driver.findElement(By.name("productName"));
        quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement updateButton = driver.findElement(By.tagName("button"));

        nameInput.clear();
        nameInput.sendKeys("Sigma");
        quantityInput.clear();
        quantityInput.sendKeys("10");
        updateButton.click();

        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertTrue(pageSource.contains("Sigma"));
        assertTrue(pageSource.contains("10"));
    }
}