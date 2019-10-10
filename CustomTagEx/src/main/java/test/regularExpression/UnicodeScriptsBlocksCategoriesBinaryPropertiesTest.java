package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class UnicodeScriptsBlocksCategoriesBinaryPropertiesTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void isLatin() {
        // java 7부터 지원
        // 라틴어 문자
        String regex = "\\p{IsLatin}+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("Latīna").matches());
        assertTrue(p.matcher("English").matches());
        assertFalse(p.matcher("한글").matches());
    }

    @Test
    public void isHangul() {
        // java 7부터 지원
        // 한국어 문자
        String regex = "\\p{IsHangul}+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("한글").matches());
        assertFalse(p.matcher("English").matches());
    }

    @Test
    public void inGreek() {
        // 그리스어 문자
        String regex = "\\p{InGreek}+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("Ελληνικά").matches());
        assertFalse(p.matcher("English").matches());
        assertFalse(p.matcher("한글").matches());
    }

    @Test
    public void upperCaseAlphabet() {
        // 알파벳 대문자 한 문자
        String regex = "\\p{Lu}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("A").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void alphabet() {
        // java 7부터 지원
        // 알파벳 대소문자 한 문자
        String regex = "\\p{IsAlphabetic}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("A").matches());
        assertFalse(p.matcher("aa").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void currencySymbol() {
        // 통화 문자
        String regex = "\\p{Sc}+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("$€£").matches());
    }

    @Test
    public void anyLetter() {
        // 통화 문자
        String regex = "\\p{L}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("A").matches());
        assertTrue(p.matcher("한").matches());
        assertFalse(p.matcher("1").matches());
        assertFalse(p.matcher("$").matches());
    }

    @Test
    public void anyLetterExceptUpperCase() {
        // 통화 문자
        String regex = "[\\p{L}&&[^\\p{Lu}]]";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("한").matches());
        assertFalse(p.matcher("A").matches());
        assertFalse(p.matcher("1").matches());
        assertFalse(p.matcher("$").matches());
    }
}