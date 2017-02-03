package soap;

import net.yandex.speller.services.spellservice.*;

import java.util.List;

public class Soap {

    public static void main(String[] args) {
        CheckTextRequest request = new CheckTextRequest();
        SpellService service = new SpellService();
        SpellServiceSoap port = service.getSpellServiceSoap();
        request = new CheckTextRequest();
        request.setLang("EN");
        request.setText("todey goood nigth");
        CheckTextResponse checkTextResponse = port.checkText(request);
        System.out.println(checkTextResponse);
        List<SpellError> errorsList = checkTextResponse.getSpellResult().getError();
        System.out.println(errorsList.size());
        for (SpellError error : errorsList){
            System.out.println(error.getWord());
          System.out.println(error.getS());
            System.out.println(error.getPos());
            System.out.println(error.getRow());
        }
    }
}
