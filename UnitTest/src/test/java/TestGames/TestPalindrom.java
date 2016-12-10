package TestGames;

import games.Palindrom;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPalindrom {
    @Test
    public void testСheckWordTrue() {
    String s1="q1qq1q";
    boolean pal = Palindrom.checkWord(s1);
    assertTrue("Ваще-то это палиндром: " + s1, pal);

    }
    @Test
    public void testСheckWordFalse() {
        String s1="Q1qqQ";
        boolean pal = Palindrom.checkWord(s1);
        assertFalse("Ваще-то это не палиндром: " + s1, pal);

    }
    @Test
    public void testcheckPhraseTrue() {
        String s1="Qwert asd d sa trew q";
        boolean pal = Palindrom.checkPhrase(s1);
        assertTrue("Ваще-то это палиндром: " + s1, pal);

    }
    @Test
    public void testcheckPhraseFalse() {
        String s1="Qwert asd d sa trew q1";
        boolean pal = Palindrom.checkPhrase(s1);
        assertFalse("Ваще-то это не палиндром: " + s1, pal);

    }
}
