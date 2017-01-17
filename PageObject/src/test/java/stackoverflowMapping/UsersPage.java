package stackoverflowMapping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class UsersPage {
    private WebDriver driver;

    public UsersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='user-browser']/.//div[contains(@class,'user-info')]")
    public List<WebElement> userInfo;

    @FindBy(xpath = ".//*[@id='user-browser']/div/a/span[contains(text(),'next')]")
    public WebElement nextButton;

    public void clickNextButton() {
        nextButton.click();
    }

    public double statJavaUsers() {
        double countJava = 0;
        double countNotJava = 0;
        double proc = 0;

        for (WebElement e : userInfo) {
            if (e.getText().contains("java")) {
                countJava++;
            } else {
                countNotJava++;
            }
        }
        proc = Math.round(countJava / (countJava + countNotJava) * 100);
        return proc;
    }
}
