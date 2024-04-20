package Website;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Launch {
@Test
    void add() throws InterruptedException {
    WebDriverManager.chromedriver().setup();
    WebDriver driver=new ChromeDriver();
    driver.get("https://www.makemytrip.com/");
    Thread.sleep(2000);
   driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
    Thread.sleep(2000);
   driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
   Thread.sleep(2000);
driver.findElement(By.xpath("//span[text()='Departure']")).click();
driver.findElement(By.xpath("")).click();

}

}
