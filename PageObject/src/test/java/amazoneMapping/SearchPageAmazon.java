package amazoneMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPageAmazon {
    private WebDriver driver;

    public SearchPageAmazon(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='centerMinus']/.//h2")
    public List<WebElement> listOfResult;

    @FindBy(xpath = ".//p[contains(text(),'Baby Products')]")
    public WebElement babyProductsButton;

    @FindBy(xpath = ".//h2[@id='s-result-count']")
    public WebElement count;

    @FindBy(xpath = ".//li[contains(@id,'result')]/.//a/h2[contains(text(),'8 Inch')]")
    public WebElement knife;

    @FindBy(xpath = ".//li[contains(@id,'result_3')]/.//a/h2")
    public WebElement fistElement;

    @FindBy(xpath = ".//li[contains(@id,'result')]/.//a/h2")
    public WebElement product;

    @FindBy(id = "sort")
    public WebElement sort;

    @FindBy(xpath = ".//*[@id='sort']/option[@value='price-desc-rank']")
    public WebElement sortByPriceDescRank;

    @FindBy(xpath = "//span[contains(@class,'s-price')]")
    public List<WebElement> price;


    public List<WebElement> listOfResults() {
        return listOfResult;
    }

    public int numberOfResults() {
        String count1 = String.valueOf(count.getText());
        String[] conut1Pats = count1.split(" ");
        int allDuck = Integer.valueOf(conut1Pats[2].replace(",", ""));
        return allDuck;
    }

    public ProductPage goToProductKnife() {
        knife.click();
        return new ProductPage(driver);
    }

    public ProductPage goToProduct() {
        product.click();
        return new ProductPage(driver);
    }

    public ProductPage goToFirstProduct() {
        fistElement.click();
        return new ProductPage(driver);
    }

    public void sort() {
        sort.click();
    }

    public void sortByPriceDescRank() {
        sortByPriceDescRank.click();
    }

    public void addToCart(String a) {
        StartPage startPage = new StartPage(driver);
        startPage.searchTextBox.clear();
        startPage.inputText(a);
        SearchPageAmazon searchPage = startPage.goToSearchPage();
        ProductPage productPage = searchPage.goToProduct();
        productPage.addToCart();
        driver.navigate().back();
    }
}
