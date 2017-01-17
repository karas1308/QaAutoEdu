package webdrivertest.amazone;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.round;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAmazone {
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
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("duck");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='nav-search']/.//input[@value='Go']")).click();
        List<WebElement> resultDuck = driver.findElements(By.xpath(".//*[@id='centerMinus']/.//h2"));
        for (WebElement element : resultDuck) {
            assertTrue("Я нашел элемент, который не содержит Duck: " + element.getText(), element.getText().contains("duck") || element.getText().contains("Duck") || element.getText().contains("DUCK"));
        }
    }

    @Test
    public void testCountDuckBaby() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("duck");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='nav-search']/.//input[@value='Go']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement count = driver.findElement(By.xpath(".//h2[@id='s-result-count']"));
        String count1 = String.valueOf(count.getText());
        String[] conut1Pats = count1.split(" ");
        int allDuck = Integer.valueOf(conut1Pats[2].replace(",", ""));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchButtonBabyProducts = driver.findElement(By.xpath(".//p[contains(text(),'Baby Products')]"));
        searchButtonBabyProducts.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement count0 = driver.findElement(By.xpath(".//h2[@id='s-result-count']"));
        String count01 = String.valueOf(count0.getText());
        String[] conut01Pats = count01.split(" ");
        int allBabyProducts = Integer.valueOf(conut01Pats[2].replace(",", ""));

        System.out.println(allBabyProducts + " " + allDuck);
        assertTrue("Количество товаров в подкатегории BabyProduct не меньше количества всех товаров", allBabyProducts < allDuck);
    }

    @Test
    public void addingToCart() {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("knife kitchen");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement go = driver.findElement(By.xpath(".//*[@id='nav-search']/.//input[@value='Go']"));
        go.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//li[contains(@id,'result')]/.//a/h2[contains(text(),'8 Inch')]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title1 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        driver.findElement(By.id("add-to-cart-button")).click();
        String price1 = String.valueOf(driver.findElement(By.xpath(".//*[@id='hlb-subcart']//span[contains(text(),'$')]")).getText().replace("$", ""));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("duck");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//li[contains(@id,'result_3')]/.//a/h2")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title2 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        String price2 = String.valueOf(driver.findElement(By.id("priceblock_ourprice")).getText().replace("$", ""));
        driver.findElement(By.id("add-to-cart-button")).click();
        System.out.println(title1);
        System.out.println(title2);
        driver.findElement(By.id("nav-cart")).click();
        List<WebElement> productsInCart = driver.findElements(By.xpath(".//*[@id='activeCartViewForm']/.//span[contains(@class,'a-list-item')]/a[contains(@class,'a-link-normal sc-product-link')]")); //товары (названия) в корзине
        for (WebElement element : productsInCart) {
            System.out.println(element.getText());
            assertTrue("Что-то не то в корзине товар: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title2));
        }
        List<WebElement> productsPriceInCart = driver.findElements(By.xpath(".//*[@id='activeCartViewForm']/.//p/span[contains(text(),'$')]")); //цена ровара в корзине
        for (WebElement element : productsPriceInCart) {
            assertTrue("Что-то не то в корзине, цены не сошлись: " + element.getText().replace("$", "") + " " + price1 + " " + price2, element.getText().replace("$", "").equals(price1) || element.getText().replace("$", "").equals(price2));
        }
        assertEquals("Количество товаров в корзине не равно 2" + productsInCart.size(), productsInCart.size(), 2);
        double total = Double.valueOf(String.valueOf(driver.findElement(By.xpath(".//*[@id='gutterCartViewForm']/.//p/span/span[contains(text(),'$')]")).getText().replace("$", "")));
        double b = Double.valueOf(price2);
        double a = Double.valueOf(price1);
        double c = Math.round((a + b) * 100.0) / 100.0;
        assertEquals("Общая сумма не сошлась с суммой цен товаров в корзине" + total, total, c, 0.1);
    }

    @Test
    public void deletingFromCart() {
        addToCart("hdmi");
        driver.navigate().back();
        String title1 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        addToCart("dvi");
        driver.navigate().back();
        String title2 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        addToCart("vga");
        driver.navigate().back();
        String title3 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        driver.findElement(By.id("nav-cart")).click();
        List<WebElement> productsInCart = driver.findElements(By.xpath(".//*[@id='activeCartViewForm']/.//span[contains(@class,'a-list-item')]/a[contains(@class,'a-link-normal sc-product-link')]")); //товары (названия) в корзине
        for (WebElement element : productsInCart) {
            assertTrue("Что-то не то в корзине товар: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title2) || element.getText().equals(title3));
        }
        driver.findElement(By.xpath(".//*[@id='activeCartViewForm']/.//div[contains(@class,'sc-list-item-border') and @data-item-count=2]/.//input[@value='Delete']")).click(); //удаляем 2-й в корзине
        driver.findElement(By.id("nav-cart")).click();
        List<WebElement> productsInCartNew = driver.findElements(By.xpath(".//*[@id='activeCartViewForm']/.//span[contains(@class,'a-list-item')]/a[contains(@class,'a-link-normal sc-product-link')]")); //товары (названия) в корзине
        for (WebElement element : productsInCartNew) {
            assertTrue("Что-то не то в корзине товар после удаления второго: " + element.getText(), element.getText().equals(title1) || element.getText().equals(title3));
        }
    }
/*Заходим на http://amazon.com. Меням страну на UK. Проверяем правильность URL. Делаем поиск по "duck". Меняем сортировку от Дорогих к Дешевым. Находим цена на первой странице, смотрим, что сортировка ОК.*/
    @Test
    public void workWithUK() {
//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='navFooter']/div[contains(@aria-label,'countries')]/ul/li/a[text()='United Kingdom']"),"sdfsdfsdf"));
        driver.findElement(By.xpath(".//*[@id='navFooter']/div[contains(@aria-label,'countries')]/ul/li/a[text()='United Kingdom']")).click();
        String curUrl = driver.getCurrentUrl();
        assertEquals("URL не UK: " + curUrl, curUrl, "https://www.amazon.co.uk/ref=footer_uk"); //проверяем правильность адреса
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys("duck");
        searchBox.submit();
        driver.findElement(By.id("sort")).click(); //открываем методы сортировки
        driver.findElement(By.xpath(".//*[@id='sort']/option[@value='price-desc-rank']")).click(); //сортируем по убывания
        List<WebElement> priceSort = driver.findElements(By.xpath("//span[contains(@class,'s-price')]")); // находим цены после сортировки
        int size = priceSort.size();
        Double arr[] = new Double[priceSort.size()];
        for (WebElement element : priceSort) {
        System.out.println(element);
        }
        for (int i = 0; i < size; i++) {
            String a = "0"+ priceSort.get(i).getText().replace(",", "");
            arr[i] = Double.valueOf(a.replace("£", ""));
            System.out.println(arr[i]);
        }
        assertTrue("Что-то с сортировкой или криво работает поиск цен", arr[1] >= arr[2] && arr[2] >= arr[3]&& arr[3] >= arr[4]);
    }

    public void addToCart(String a) {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys(a);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//li[contains(@id,'result')]/.//a/h2")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title1 = String.valueOf(driver.findElement(By.id("productTitle")).getText());
        driver.findElement(By.id("add-to-cart-button")).click();
    }
}
