package amazoneMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='activeCartViewForm']/.//span[contains(@class,'a-list-item')]/a[contains(@class,'a-link-normal sc-product-link')]")
    public List<WebElement> productsInCart;

    @FindBy(xpath = ".//*[@id='activeCartViewForm']/.//p/span[contains(text(),'$')]")
    public List<WebElement> productsPriceInCart;

    @FindBy(xpath = ".//*[@id='gutterCartViewForm']/.//p/span/span[contains(text(),'$')]")
    public WebElement total;

    @FindBy(xpath = ".//*[@id='activeCartViewForm']/.//div[contains(@class,'sc-list-item-border') and @data-item-count=2]/.//input[@value='Delete']")
    public WebElement secondElementInCartDelete;

    public void secondElementInCartDelete() {
        secondElementInCartDelete.click();
    }

}
