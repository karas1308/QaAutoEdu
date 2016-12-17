package cucetestpackage.stepdefs;

        import cucumber.api.java.en.Given;
        import cucumber.api.java.en.Then;
        import cucumber.api.java.en.When;
        import games.Palindrom;

        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

public class StepTestPalindrom {
    private String str;
    private boolean pal;

    @Given("^Введем текст \"([^\"]*)\"$")
    public void введемТекст(String arg0) throws Throwable {
        str = arg0;
    }

    @When("^Узнаем палиндром ли это$")
    public void узнаемПалиндромЛиЭто() throws Throwable {
        pal = Palindrom.checkWord(str);
    }

    @Then("^Что-то напишем, если вдруг ошибка$")
    public void чтоТоНапишемЕслиВдругОшибка() throws Throwable {
        assertTrue("Ваще-то это палиндром: " + str, pal);
    }

    @Then("^Сообщим об ошибке$")
    public void сообщимОбОшибке() throws Throwable {
        assertFalse("Ваще-то это не палиндром: " + str, pal);
    }

    @Given("^Введем фразу-палиндром \"([^\"]*)\"$")
    public void введемФразуПалиндром(String arg0) throws Throwable {
        str = arg0;
    }

    @When("^Узнаем палиндром ли эта фраза$")
    public void узнаемПалиндромЛиЭтаФраза() throws Throwable {
        pal = Palindrom.checkPhrase(str);
    }
}
