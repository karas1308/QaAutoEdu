package amazon;


import amazoneMapping.CartPage;
import amazoneMapping.ProductPage;
import amazoneMapping.SearchPageAmazon;
import amazoneMapping.StartPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAmazon {
    public static WebDriver driver;

    @BeforeClass
    public static void testBeforeClass() {
    }

    @Before
    public void testBefore() {
        driver = new ChromeDriver();
        driver.get("http://amazon.com");
    }

    @After
    public void testAfter() {
        driver.quit();
    }

    @AfterClass
    public static void driverQuit() {
        driver.quit();
    }

    @Test
    public void testContainsDuck() {
        StartPage startPage = new StartPage(driver);
        startPage.inputText("duck");
        SearchPageAmazon searchPage = startPage.goToSearchPage();
        for (WebElement element : searchPage.listOfResult) {
            assertTrue("Я нашел элемент, который не содержит Duck: " + element.getText(), element.getText().contains("duck") || element.getText().contains("Duck") || element.getText().contains("DUCK"));
        }
    }

    @Test
    public void testCountDuckBaby() {
        StartPage startPage = new StartPage(driver);
        startPage.inputText("duck");
        SearchPageAmazon searchPage = startPage.goToSearchPage();
        int allDuck = searchPage.numberOfResults();
        searchPage.babyProductsButton.click();
        int allBabyProducts = searchPage.numberOfResults();
        System.out.println(allBabyProducts + " " + allDuck);
        assertTrue("Количество товаров в подкатегории BabyProduct не меньше количества всех товаров", allBabyProducts < allDuck);
    }

    @Test
    public void addingToCart() {
        StartPage startPage = new StartPage(driver);
        startPage.inputText("knife kitchen");
        SearchPageAmazon searchPage = startPage.goToSearchPage();
        ProductPage productPage = searchPage.goToProductKnife();
        String title1 = productPage.productTitle();
        Double price1 = productPage.productPrice();
        productPage.addToCart();
        startPage.inputText("duck");
        startPage.goToSearchPage();
        searchPage.goToFirstProduct();
        String title2 = productPage.productTitle();
        Double price2 = productPage.productPrice();
        productPage.addToCart();
        CartPage cartPage = startPage.goToCartPage();
        List<WebElement> productsInCart = cartPage.productsInCart; //товары (названия) в корзине
        for (WebElement element : productsInCart) {
            assertTrue("Что-то не то в корзине, товар: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title2));
        }
        List<WebElement> productsPriceInCart = cartPage.productsPriceInCart; //цена ровара в корзине
        for (WebElement element : productsPriceInCart) {
            assertTrue("Что-то не то в корзине, цены не сошлись: " + element.getText().replace("$", "") + " " + price1 + " " + price2, Double.valueOf(element.getText().replace("$", "")).equals(price1) || Double.valueOf(element.getText().replace("$", "")).equals(price2));
        }
        assertEquals("Количество товаров в корзине не равно 2" + productsInCart.size(), productsInCart.size(), 2);
        double total = Double.valueOf(String.valueOf(cartPage.total.getText().replace("$", "")));
        double c = Math.round((price1 + price2) * 100.0) / 100.0;
        assertEquals("Общая сумма не сошлась с суммой цен товаров в корзине" + total, total, c, 0.1);
    }

    @Test
    public void deletingFromCart() {
        SearchPageAmazon searchPage = new SearchPageAmazon(driver);
        ProductPage productPage = new ProductPage(driver);
        StartPage startPage = new StartPage(driver);
        CartPage cartPage = new CartPage(driver);
        searchPage.addToCart("hdmi");
        String title1 = productPage.productTitle();
        searchPage.addToCart("dvi");
        String title2 = productPage.productTitle();
        searchPage.addToCart("vga");
        String title3 = productPage.productTitle();
        startPage.goToCartPage();
        List<WebElement> productsInCart = cartPage.productsInCart; //товары (названия) в корзине
        for (WebElement element : productsInCart) {
            assertTrue("Что-то не то в корзине товар: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title2) || element.getText().equals(title3));
        }
        cartPage.secondElementInCartDelete(); //удаляем 2-й в корзине
        startPage.goToCartPage();
        List<WebElement> productsInCartNew = cartPage.productsInCart; //товары (названия) в корзине
        for (WebElement element : productsInCartNew) {
            assertTrue("Что-то не то в корзине товар после удаления второго: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title3));
        }
    }

    @Test
    public void workWithUK() {
        StartPage startPage = new StartPage(driver);
        SearchPageAmazon searchPage = new SearchPageAmazon(driver);
        startPage.goToUK();
        String curUrl = driver.getCurrentUrl();
        assertEquals("URL не UK: " + curUrl, curUrl, "https://www.amazon.co.uk/ref=footer_uk"); //проверяем правильность адреса
        startPage.searchTextBox.clear();
        startPage.inputText("duck");
        startPage.goToSearchPage();
        searchPage.sort();
        searchPage.sortByPriceDescRank();//сортируем по убывания
        List<WebElement> priceSort = searchPage.price; // находим цены после сортировки
        int size = priceSort.size();
        Double arr[] = new Double[size];
        for (int i = 0; i < size; i++) {
            String a = String.valueOf(priceSort.get(i).getText().replace("£", ""));
            arr[i] = Double.valueOf(a.replace(",", ""));
            System.out.println(arr[i]);
        }
        assertTrue("Что-то с сортировкой или криво работает поиск цен", arr[1] >= arr[2] && arr[2] >= arr[3] && arr[3] >= arr[4]);
    }

}

