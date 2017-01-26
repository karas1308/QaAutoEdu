package amazoneMapping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "productTitle")
    public WebElement productTitle;

    @FindBy(id = "add-to-cart-button")
    public WebElement addToCart;

    @FindBy(xpath = ".//span[contains(@id,'priceblock')]")
    public WebElement price;

    public String productTitle() {
        String title = String.valueOf(productTitle.getText());
        return title;
    }
    public Double productPrice() {
        String a = String.valueOf(price.getText().replace("$", ""));
        double price = Double.valueOf(a);
        return price;
    }
    public void addToCart() {
        addToCart.click();
       }
}
