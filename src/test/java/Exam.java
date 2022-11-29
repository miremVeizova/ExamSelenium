import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exam {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://shop.pragmatic.bg");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void addUserTest() {
        WebElement myAccount = driver.findElement(By.xpath("//*[@class='fa fa-user']//.."));
        myAccount.click();
        driver.findElement(By.xpath("//*[@class='dropdown-menu dropdown-menu-right']//li[1]//a/.")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("Mirem");
        driver.findElement(By.id("input-lastname")).sendKeys("Veizova");
//        driver.findElement(By.id("input-email")).sendKeys("miremka_88@abv.bg");
        String prefix = RandomStringUtils.randomAlphabetic(7);
        String domainPrefix = RandomStringUtils.randomAlphabetic(5);
        String mainDomain = RandomStringUtils.randomAlphabetic(3);
        String emailAddress = prefix + "@" + domainPrefix + "." + mainDomain;
        driver.findElement(By.id("input-email")).sendKeys(emailAddress);
        driver.findElement(By.id("input-telephone")).sendKeys("08847652");
        driver.findElement(By.id("input-password")).sendKeys("98645372");
        driver.findElement(By.id("input-confirm")).sendKeys("98645372");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Your Account Has Been Created!"));
    }
}