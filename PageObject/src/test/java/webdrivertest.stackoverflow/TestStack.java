package stackoverflow;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import stackoverflowMapping.QuestionPage;
import stackoverflowMapping.SignUpPage;
import stackoverflowMapping.StartPageOver;
import stackoverflowMapping.UsersPage;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
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
        StartPageOver startPage = new StartPageOver(driver);
        int featured = startPage.featured();
        assertFalse("количество в табе Featured меньше 300: " + featured, featured < 300);
    }

    @Test
    public void testSocNetButton() {
        StartPageOver startPageOver = new StartPageOver(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        startPageOver.goToSignUpPage();
        assertTrue("Google not found :(", signUpPage.isGoogle());
        assertTrue("Facebook not found", signUpPage.isFacebook());
    }

    @Test
    public void testTopQuestionsToday() {
        StartPageOver startPageOver = new StartPageOver(driver);
        QuestionPage questionPage = new QuestionPage(driver);
        List<WebElement> listOfTop = startPageOver.topQuestions;
        int countOfTop = listOfTop.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(countOfTop);
        startPageOver.goToQuestionPage(randomIndex);
        assertTrue("Воспрос был задан не сегодня", questionPage.today());
    }

    @Test
    public void test60KSalary() {
        double zp = 0;
        StartPageOver startPageOver = new StartPageOver(driver);
        zp = startPageOver.salary();
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
        StartPageOver startPageOver = new StartPageOver(driver);
        startPageOver.goToUsersPage();
        UsersPage usersPage = new UsersPage(driver);
        for (int i = 0; i < 21; i++) {
            proc = usersPage.statJavaUsers();
            assertTrue("Пользователей с тегом JAVA меньше 30% на старнице " + i + " - " + proc, proc > 30);
            usersPage.clickNextButton();
        }
    }
}

