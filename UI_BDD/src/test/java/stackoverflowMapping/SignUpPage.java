package stackoverflowMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='openid-buttons']/.//span[contains(text(),'Google')]")
    public WebElement buttonGoogle;

    @FindBy(xpath = ".//*[@id='openid-buttons']/.//span[contains(text(),'Facebook')]")
    public WebElement buttonFacebook;

    public Boolean isGoogle() {
        boolean isGoogle = buttonGoogle.isDisplayed();
        return isGoogle;
    }

    public Boolean isFacebook() {
        boolean isFacebook = buttonFacebook.isDisplayed();
        return isFacebook;
    }
}
