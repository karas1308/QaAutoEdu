package stackoverflowMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QuestionPage {
    private WebDriver driver;

    public QuestionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='qinfo']/.//b[contains(text(),'today')]")
    public List<WebElement> today;

    public boolean today() {
        boolean isToday = today.size() != 0;
        return isToday;
    }
}

