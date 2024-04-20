package Website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class sample {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000).getSeconds());
        driver.findElement(By.xpath("//li[@class='dropdown']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[.='Login']")).click();


    }
}
