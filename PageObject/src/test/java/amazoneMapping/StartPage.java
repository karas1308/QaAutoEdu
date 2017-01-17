package amazoneMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage {
    private WebDriver driver;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchTextBox;

    @FindBy(xpath = ".//*[@id='nav-search']/.//input[@value='Go']")
    public WebElement searchButton;

    @FindBy(id ="nav-cart")
    public WebElement cartButton;

    @FindBy(xpath = ".//*[@id='navFooter']/div[contains(@aria-label,'countries')]/ul/li/a[text()='United Kingdom']")
    public WebElement linkUK;

    public void inputText(String s) {
        searchTextBox.sendKeys(s);
    }

    public SearchPageAmazon goToSearchPage() {
        searchButton.click();
        return new SearchPageAmazon(driver);
    }
    public CartPage goToCartPage() {
        cartButton.click();
        return new CartPage(driver);
    }
    public UKPage goToUK() {
        linkUK.click();
        return new UKPage(driver);
    }
}
