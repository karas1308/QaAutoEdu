package amazoneMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class UKPage {
    private WebDriver driver;
    public UKPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
