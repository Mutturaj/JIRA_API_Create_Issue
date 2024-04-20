package Website;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShareMarket {
    WebDriver driver;

    @Test
    public void Launch() {
        driver = new ChromeDriver();
        driver.get("https://trade.share.market/login?from=%2Fmarkets");
        driver.findElement(By.xpath("//span[.='Continue with PhonePe']")).click();
        driver.findElement(By.xpath("//input[@id='mobileNumber']")).sendKeys("9343550736");
        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }
}
