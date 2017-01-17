package stackoverflowMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StartPageOver {
    private WebDriver driver;

    public StartPageOver(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='tabs']/a/span[@class='bounty-indicator-tab']")
    public WebElement featured;

    @FindBy(id = "tell-me-more")
    public WebElement signUp;

    @FindBy(xpath = ".//*[@id='qlist-wrapper']//h3/a[contains(@href,'questions')]")
    public List<WebElement> topQuestions;

    @FindBy(xpath = ".//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'€')] | .//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'$')] | .//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'£')]")
    public List<WebElement> salary;

    @FindBy(id = "nav-users")
    public WebElement users;

    public int featured() {
        int test = Integer.valueOf(featured.getText());
        return test;
    }

    public SignUpPage goToSignUpPage() {
        signUp.click();
        return new SignUpPage(driver);
    }

    public QuestionPage goToQuestionPage(int ind) {
        topQuestions.get(ind).click();
        return new QuestionPage(driver);
    }

    public double salary() {
        double maxzp = 0;
        double zp = 0;
        for (WebElement e : salary) {
            String str = e.getText().replace(",", "");
            String[] strPats = str.split(" - ");
            String salaryN = strPats[1];
            if (salaryN.contains("$")) {
                maxzp = Integer.valueOf(salaryN.replace("$", ""));
            }
            if (salaryN.contains("€")) {
                maxzp = 1.0528 * Integer.valueOf(salaryN.replace("€", ""));
            } //конвертим в баксы
            if (salaryN.contains("£")) {
                maxzp = 1.2257 * Integer.valueOf(salaryN.replace("£", ""));
            } //конвертим в баксы
            if (maxzp > zp)
                zp = maxzp;
        }
        return zp;
    }
public UsersPage goToUsersPage(){
        users.click();
        return new UsersPage(driver);
}
}
