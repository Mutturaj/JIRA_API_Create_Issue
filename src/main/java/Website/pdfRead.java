package Website;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class pdfRead {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();
        driver = new ChromeDriver();
        driver.get("https://www.inkit.com/blog/pdf-the-best-digital-document-management");
    }

    @Test
    public void pdfread() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//a[@id='ecSubmitAgreeButton']")).click();
        Thread.sleep(4000);
        driver.findElement(By.linkText("trillions of PDFs")).click();
        Thread.sleep(3000);

        String url = driver.getCurrentUrl();
        URL pdfurl = new URL(url);
        InputStream ip = pdfurl.openStream();
        BufferedInputStream bf = new BufferedInputStream(ip);
        PDDocument pdfdoc = PDDocument.load(bf);
        int pagecount = pdfdoc.getNumberOfPages();
        System.out.println("The total num of page is " + pagecount);

        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(2);
        stripper.setEndPage(2);
        String contents = stripper.getText(pdfdoc);
        System.out.println(contents);
    }


}


