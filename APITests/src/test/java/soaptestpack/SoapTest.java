package soaptestpack;

import net.yandex.speller.services.spellservice.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jws.WebParam;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SoapTest {
    private static SpellService service;
    private static SpellServiceSoap port;
    private CheckTextRequest request;

    @BeforeClass
    public static void createService() {
        service = new SpellService();
        port = service.getSpellServiceSoap();
    }

    @Before
    public void prepareCleanReques() {
        request = new CheckTextRequest();
      //  request.setLang("EN");
    }
@After
public void after(){
        System.out.println("Реклама зубной пасты. Как же без рекламы-то ?? ))");
}
    @Test
    public void checkError() {
        request.setText("todey goood nigth");
        CheckTextResponse checkTextResponse = port.checkText(request);
        assertTrue("Respons don't contains 3 errors",
                checkTextResponse.getSpellResult().getError().size() == 3);
    }
    @Test
    public void checkErrorPos0Row0() {
        request.setText("todey");
        CheckTextResponse checkTextResponse = port.checkText(request);
        List<SpellError> errorsList = checkTextResponse.getSpellResult().getError();
        for (SpellError error : errorsList) {
            assertTrue("pos и row не равны 0 " ,  error.getRow()==0 & error.getPos()==0);
        }
    }
        @Test
    public void checkErrorRow1() {
        request.setText("today \n todey");
        CheckTextResponse checkTextResponse = port.checkText(request);
        List<SpellError> errorsList = checkTextResponse.getSpellResult().getError();
        for (SpellError error : errorsList) {
            assertTrue("pos и row не равны 0 " + error.getRow(),  error.getRow()==1);
        }
    }
    @Test
    public void checkErrorRuPlayed() {
        request.setLang("RU");
        request.setText("Пьеса была сиграна");
        CheckTextResponse checkTextResponse = port.checkText(request);
        List<SpellError> errorsList = checkTextResponse.getSpellResult().getError();
        for (SpellError error : errorsList) {
            assertTrue("Слово 'сиграна' не попало в ошибки",  error.getWord().equals("сиграна"));
            assertTrue("В замене предложено не слово 'сыграна' ",  error.getS().contains("сыграна"));
        }
    }
    @Test
    public void checkErrorRuNumbSuggest() {
        request.setText("сиграна");
        CheckTextResponse checkTextResponse = port.checkText(request);
        List<SpellError> errorsList = checkTextResponse.getSpellResult().getError();
        for (SpellError error : errorsList) {
            assertTrue("Слово 'сиграна' не попало в ошибки",  error.getWord().equals("сиграна"));
            assertTrue("Количество вариантов замены не равно 5, а равно " +error.getS().size(),  error.getS().size()==5);
        }
    }
}
