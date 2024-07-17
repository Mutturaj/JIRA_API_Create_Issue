package Website;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class profileUpdate {
    @Test(priority = 0)
    void add() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.naukri.com/mnjuser/profile?id=&altresid");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys("mutturaja1996@gmail.com");
        driver.findElement(By.xpath("//input[@id='passwordField']")).sendKeys("15Muttu@05");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[.='Login']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='widgetHead']//span[@class='edit icon' and .='editOneTheme']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[.='Save']")).click();
        Thread.sleep(3000);
        Assert.assertEquals("Success", driver.findElement(By.xpath("//p[.='Success']")).getText());
Thread.sleep(2000);
driver.close();
    }
    @Test(priority = 1)
    void update() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/uas/login?session_redirect=https%3A%2F%2Fwww.linkedin.com%2Fnotifications%2F%3Ffilter%3Dall");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("mutturaja1996@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("15Muttu@05");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
        driver.navigate().to("https://www.linkedin.com/in/mutturaj-annigeri/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@id='ember38']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='Save']")).click();
        Thread.sleep(2000);
        Assert.assertEquals("Save was successful.", driver.findElement(By.xpath("//span[text()='Save was successful.']")).getText());
        Thread.sleep(2000);
        driver.close();


    }
}
