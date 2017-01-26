package cucmberWebTest.stepdefs;

import amazoneMapping.CartPage;
import amazoneMapping.ProductPage;
import amazoneMapping.SearchPageAmazon;
import amazoneMapping.StartPage;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AmazonStepdefs {
    public static WebDriver driver;
    StartPage startPage;
    SearchPageAmazon searchPage;
    ProductPage productPage;
    CartPage cartPage;
    int allDuck;
    int allBabyProducts;
    String title1, title2;
    Double price1, price2;
    static ArrayList<String> arrlist = new ArrayList<String>(3);
    static ArrayList<Double> arr = new ArrayList<Double>();
    int size;


    @Before
    public void testBefore() {
        driver = new ChromeDriver();
        driver.get("http://amazon.com");
    }


    @Given("^Найдем товары, содержащие слово \"([^\"]*)\"$")
    public void найдемТоварыСодержащиеСлово(String arg0) throws Throwable {
        StartPage startPage = new StartPage(driver);
        startPage.inputText(arg0);
        searchPage = startPage.goToSearchPage();
    }

    @Then("^Проверим, что найденные товары содержат в названии \"([^\"]*)\", \"([^\"]*)\" или \"([^\"]*)\"$")
    public void проверимЧтоНайденныеТоварыСодержатВНазванииИли(String arg0, String arg1, String arg2) throws Throwable {
        for (WebElement element : searchPage.listOfResult) {
            assertTrue("Я нашел элемент, который не содержит Duck: " + element.getText(), element.getText().contains(arg0) || element.getText().contains(arg1) || element.getText().contains(arg2));
        }
    }

    @When("^Сохраним количество найденных товаров$")
    public void сохранимКоличествоНайденныхТоваров() throws Throwable {
        allDuck = searchPage.numberOfResults();

    }

    @And("^Нажмем на кнорпку BabyProduct и сохраним количество найденных товаров$")
    public void нажмемНаКнорпкуBabyProductИСохранимКоличествоНайденныхТоваров() throws Throwable {
        searchPage.babyProductsButton.click();
        allBabyProducts = searchPage.numberOfResults();
    }

    @Then("^Сравним результаты$")
    public void сравнимРезультаты() throws Throwable {
        assertTrue("Количество товаров в подкатегории BabyProduct не меньше количества всех товаров", allBabyProducts < allDuck);
    }

    @And("^Откроем стриницу товара и сохраним его название и цену$")
    public void откроемСтриницуТовараИСохранимЕгоНазваниеИЦену() throws Throwable {
        productPage = searchPage.goToProductKnife();
        title1 = productPage.productTitle();
        price1 = productPage.productPrice();
    }

    @And("^Добавляем товар в корзину$")
    public void добавляемТоварВКорзину() throws Throwable {
        productPage.addToCart();
    }

    @And("^Откроем стриницу первого товара и сохраним его название и цену$")
    public void откроемСтриницуПервогоТовараИСохранимЕгоНазваниеИЦену() throws Throwable {
        searchPage.goToFirstProduct();
        title2 = productPage.productTitle();
        price2 = productPage.productPrice();
    }

    @Then("^Откроем корзину$")
    public void откроемКорзину() throws Throwable {
        StartPage startPage = new StartPage(driver);
        startPage.goToCartPage();
    }

    @And("^Проверяем, что в корзине (\\d+) товара$")
    public void проверяемЧтоВКорзинеТовара(int arg0) throws Throwable {
        CartPage cartPage = new CartPage(driver);
        for (WebElement element : cartPage.productsInCart) {
            assertEquals("Количество товаров в корзине не равно 2" + cartPage.productsInCart.size(), cartPage.productsInCart.size(), arg0);
        }
    }


    @And("^Проверяем названия товаров в корзине$")
    public void проверяемНазванияТоваровВКорзине() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        for (WebElement element : cartPage.productsInCart) {
            assertTrue("Что-то не то в корзине, товар: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title2));
        }
    }

    @And("^Проверяем цены товаров в корзине$")
    public void проверяемЦеныТоваровВКорзине() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        for (WebElement element : cartPage.productsPriceInCart) {
            assertTrue("Что-то не то в корзине, цены не сошлись: " + element.getText().replace("$", "") + " " + price1 + " " + price2, Double.valueOf(element.getText().replace("$", "")).equals(price1) || Double.valueOf(element.getText().replace("$", "")).equals(price2));
        }
    }

    @And("^Проверяем сумму цен в корзине$")
    public void проверяемСуммуЦенВКорзине() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        double total = Double.valueOf(String.valueOf(cartPage.total.getText().replace("$", "")));
        double c = Math.round((price1 + price2) * 100.0) / 100.0;
        assertEquals("Общая сумма не сошлась с суммой цен товаров в корзине" + total, total, c, 0.1);
    }

    @Given("^Добавим в корзину товар \"([^\"]*)\"$")
    public void добавимВКорзинуТовар(String arg0) throws Throwable {
        SearchPageAmazon searchPage = new SearchPageAmazon(driver);
        ProductPage productPage = new ProductPage(driver);
        searchPage.addToCart(arg0);
        arrlist.add(productPage.productTitle());
    }

    @Then("^Проверяем содержимое корзины$")
    public void проверяемСодержимоеКорзины() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        for (WebElement element : cartPage.productsInCart) {
            assertTrue("Что-то не то в корзине товар: " + element.getText(), element.getText().equals(arrlist.get(0)) || element.getText().equals(arrlist.get(1)) || element.getText().equals(arrlist.get(2)));
        }
    }

    @And("^Удалим второй элемент из корзины$")
    public void удалимВторойЭлементИзКорзины() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        cartPage.secondElementInCartDelete();
    }

    @And("^Проверяем второго элемента нет в корзине$")
    public void проверяемВторогоЭлементаНетВКорзине() throws Throwable {
        CartPage cartPage = new CartPage(driver);
        //List<WebElement> productsInCartNew = cartPage.productsInCart; //товары (названия) в корзине
        for (WebElement element : cartPage.productsInCart) {
            assertTrue("Что-то не то в корзине товар после удаления второго: " + element.getText(), element.getText().equals(arrlist.get(0)) || element.getText().equals(arrlist.get(2)));
        }
    }

    @Given("^Перейдем на amazon UK$")
    public void перейдемНаAmazonUK() throws Throwable {
        StartPage startPage = new StartPage(driver);
        startPage.goToUK();
    }

    @And("^Проверим, что текущий url \"([^\"]*)\"$")
    public void проверимЧтоТекущийUrl(String arg0) throws Throwable {
        String curUrl = driver.getCurrentUrl();
        assertEquals("URL не UK: " + curUrl, curUrl, arg0);
    }

    @And("^Отсортируем товары по убыванию цены$")
    public void отсортируемТоварыПоУбываниюЦены() throws Throwable {
        SearchPageAmazon searchPage = new SearchPageAmazon(driver);
        searchPage.sort();
        searchPage.sortByPriceDescRank();
    }

    @And("^Найдем цены товаров на странице$")
    public void найдемЦеныТоваровНаСтранице() throws Throwable {
        SearchPageAmazon searchPage = new SearchPageAmazon(driver);
        Thread.sleep(3000);
        size = searchPage.price.size();
        for (int i = 0; i < size; i++) {
            String a = String.valueOf(searchPage.price.get(i).getText().replace("£", ""));
            arr.add(i, Double.valueOf(a.replace(",", "")));
            System.out.println(arr.get(i));
        }
    }

    @Then("^Проверим сортировку цен по убыванию$")
    public void проверимСортировкуЦенПоУбыванию() throws Throwable {
        for (int i = 0; i < size - 1; i++) {
            assertTrue("Что-то с сортировкой или криво работает поиск цен", arr.get(i) >= arr.get(i + 1));
        }
    }

    @And("^Закроем браузер$")
    public void закроемБраузер() throws Throwable {
        driver.quit();
    }
}
