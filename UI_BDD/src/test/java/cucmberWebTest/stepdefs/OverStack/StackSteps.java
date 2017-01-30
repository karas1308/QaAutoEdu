package cucmberWebTest.stepdefs.OverStack;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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


public class StackSteps {
    public static WebDriver driver;
    StartPageOver startPageOver;
    int featured;
    int countOfTop;
    double zp = 0;
    double countJava = 0;
    double countNotJava = 0;
    double proc;


    @Before
    public void testBefore() {
        driver = new ChromeDriver();
        driver.get("http://stackoverflow.com/");
    }

    @Given("^Найдем количество на табе Featured$")
    public void найдемКоличествоНаТабеFeatured() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        featured = startPageOver.featured();

    }

    @Then("^Проверим, что количество Featured больше (\\d+)$")
    public void проверимЧтоКоличествоFeaturedБольше(int arg0) throws Throwable {
        assertFalse("количество в табе Featured меньше 300: " + featured, featured < arg0);
    }

    @Given("^Откроем страницу авторизации SignUp$")
    public void откроемСтраницуАвторизацииSignUp() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        startPageOver.goToSignUpPage();
    }

    @Then("^Проверим, что на странице есть кнопки Google и Facebook$")
    public void проверимЧтоНаСтраницеЕстьКнопкиGoogleИFacebook() throws Throwable {
        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue("Google not found :(", signUpPage.isGoogle());
        assertTrue("Facebook not found", signUpPage.isFacebook());
    }

    @Given("^Найдем количество вопросов на главной$")
    public void найдемКоличествоВопросовНаГлавной() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        List<WebElement> listOfTop = startPageOver.topQuestions;
        countOfTop = listOfTop.size();
    }


    @When("^Откроем случайный вопрос$")
    public void откроемСлучайныйВопрос() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        Random rand = new Random();
        int randomIndex = rand.nextInt(countOfTop);
        startPageOver.goToQuestionPage(randomIndex);
    }

    @Then("^Проверим, что он был задан сегодня$")
    public void проверимЧтоОнБылЗаданСегодня() throws Throwable {
        QuestionPage questionPage = new QuestionPage(driver);
        assertTrue("Воспрос был задан не сегодня", questionPage.today());
    }

    @Given("^Найдем зарплаты в предложениях о работе на главной$")
    public void найдемЗарплатыВПредложенияхОРаботеНаГлавной() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        zp = startPageOver.salary();
    }

    @Then("^Проверим, что зп указана и больше (\\d+) долларов$")
    public void проверимЧтоЗпУказанаИБольшеДолларов(int arg0) throws Throwable {
        if (zp > 0) {
            assertTrue("Предложений работы с зп больше 60К на главной нет, максимум " + zp, zp > arg0);
        } else {
            assertFalse("В предложениях работы  на главной зп не указана", zp == 0);
        }
    }


    @And("^rtrtr <(\\d+)> dfsd$")
    public void rtrtrDfsd(int arg0) throws Throwable {
        if (zp > 0) {
            assertTrue("Предложений работы с зп больше 60К на главной нет, максимум " + zp, zp > arg0);
        } else {
            assertFalse("В предложениях работы  на главной зп не указана", zp == 0);
        }
    }

    @Given("^Откроем страницу User$")
    public void откроемСтраницуUser() throws Throwable {
        StartPageOver startPageOver = new StartPageOver(driver);
        startPageOver.goToUsersPage();
    }

    @Then("^Проверим, что на каждой из первых (\\d+) старниц количество пользователей с тегом java > (\\d+) %$")
    public void проверимЧтоНаКаждойИзПервыхСтарницКоличествоПользователейСТегомJava(int arg0, int arg1) throws Throwable {
        UsersPage usersPage = new UsersPage(driver);
        for (int i = 0; i <= arg0; i++) {
            proc = usersPage.statJavaUsers();
            assertTrue("Пользователей с тегом JAVA меньше 30% на старнице " + i + " - " + proc, proc > arg1);
            usersPage.clickNextButton();
        }
    }

    @And("^Закроем браузер после теста$")
    public void закроемБраузерПослеТеста() throws Throwable {
        driver.quit();
    }
}
