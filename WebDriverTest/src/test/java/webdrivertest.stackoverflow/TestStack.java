package webdrivertest.stackoverflow;

import com.google.common.base.Verify;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestStack {
    public static WebDriver driver;

    @BeforeClass
    public static void testBeforeClass() {
    }

    @Before
    public void testBefore() {
        driver = new ChromeDriver();
        driver.get("http://stackoverflow.com/");
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
    public void testNumbFeatured() {
        WebElement featured = driver.findElement(By.xpath(".//*[@id='tabs']/a/span[@class='bounty-indicator-tab']"));
        double test = Double.valueOf(featured.getText());
        assertFalse("количество в табе Featured меньше 300: " + featured.getText(), test < 300);
    }

    @Test
    public void testSocNetButton() {
        driver.findElement(By.id("tell-me-more")).click();
        Boolean isGoogle = driver.findElements(By.xpath(".//*[@id='openid-buttons']/.//span[contains(text(),'Google')]")).size() == 0;
        Boolean isFacebook = driver.findElements(By.xpath(".//*[@id='openid-buttons']/.//span[contains(text(),'Facebook')]")).size() == 0;
        assertFalse("Google not found :(", isGoogle);
        assertFalse("Facebook not found", isFacebook);
    }

    @Test
    public void testTopQuestionsToday() {
        List<WebElement> listOfTop = driver.findElements(By.xpath(".//*[@id='qlist-wrapper']//h3/a[contains(@href,'questions')]"));
        int countOfTop = listOfTop.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(countOfTop);
        //  System.out.println(countOfTop);
        //  System.out.println(randomIndex);
        listOfTop.get(randomIndex).click();
        Boolean isToday = driver.findElements(By.xpath(".//*[@id='qinfo']/.//b[contains(text(),'today')]")).size() != 0;
        assertTrue("Воспрос был задан не сегодня", isToday);
    }

    @Test
    public void test60KSalary() {
        double maxzp = 0;
        double zp = 0;
        List<WebElement> salary = driver.findElements(By.xpath("..//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'€')] | .//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'$')] | .//*[@id='hireme']/.//span[contains(@class,'salary') and contains(text(),'£')]"));
        for (WebElement e : salary) {
            String str = e.getText().replace(",", "");
            String[] strPats = str.split(" - ");
            String salaryN = strPats[1];
            System.out.println(salaryN);
            if (salaryN.contains("$")) {
                maxzp = Integer.valueOf(salaryN.replace("$", ""));
            }
            if (salaryN.contains("€")) {
                maxzp = 1.0528 * Integer.valueOf(salaryN.replace("€", ""));
            } //конвертив в баксы
            if (salaryN.contains("£")) {
                maxzp = 1.2257 * Integer.valueOf(salaryN.replace("£", ""));
            } //конвертив в баксы
            if (maxzp > zp)
                zp = maxzp;
            System.out.println(zp);
        }
        if (zp > 0) {
            assertTrue("Предложений работы с зп больше 60К на главной нет, максимум " + zp, zp > 60000);
        } else {
            assertFalse("В предложениях работы  на главной зп не указана", zp == 0);
        }
    }

    /*Переодим на страничку USERS, выдергиваем информацию о пользователях, считаем количество пользователей, у которых встречается "java", и
        тех, у кого нет "java". Смотрим это на первых 20 страницах пользователей с сортировкой о умолчанию. Тест упадет, если на странице окажется
        пользователей с джавой меньше 30%*/
    @Test
    public void userInfo() {
        double countJava = 0;
        double countNotJava = 0;
        double proc;
        driver.findElement(By.id("nav-users")).click();
        for (int i = 1; i < 21; i++) {
            countJava = 0;
            countNotJava = 0;
            proc = 0;
            List<WebElement> allUsersInfo = driver.findElements(By.xpath(".//*[@id='user-browser']/.//div[contains(@class,'user-info')]"));
            for (WebElement e : allUsersInfo) {
                if (e.getText().contains("java")) {
                    countJava++;
                } else {
                    countNotJava++;
                }
            }
            proc = Math.round(countJava / (countJava + countNotJava) * 100);
            assertTrue("Пользователей с тегом JAVA меньше 30% на старнице " + i + " - " + proc, proc > 30);
            driver.findElement(By.xpath(".//*[@id='user-browser']/div/a/span[contains(text(),'next')]")).click();
        }
    }
}