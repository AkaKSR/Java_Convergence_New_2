package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class PosixTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#usc
     */

    @Test
    public void lowerCaseAlphabet() {
        // 알파벳 소문자
        String regex = "\\p{Lower}"; // [a-z]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertFalse(p.matcher("A").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void upperCaseAlphabet() {
        // 알파벳 대문자
        String regex = "\\p{Upper}"; // [A-Z]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("A").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void ascii() {
        String regex = "\\p{ASCII}"; // [\\x00-\\x7F]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertFalse(p.matcher("\u9999").matches());
    }

    @Test
    public void alphabet() {
        String regex = "\\p{Alpha}"; // [\\p{Lower}\\p{Upper}]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("A").matches());
        assertFalse(p.matcher("aa").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void alphanumeric() {
        // An alphanumeric character
        String regex = "\\p{Alnum}"; // [\\p{Alpha}\\p{Digit}]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("1").matches());
        assertFalse(p.matcher("ab").matches());
        assertFalse(p.matcher("12").matches());
    }

    @Test
    public void digit() {
        String regex = "\\p{Digit}"; // [0-9]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("0").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("11").matches());
    }

    @Test
    public void punctuationMark() {
        // !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 중 하나
        String regex = "\\p{Punct}+";
        Pattern p = Pattern.compile(regex);
        assertTrue(p.matcher("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~").matches());
    }

    @Test
    public void graph() {
        // A visible character
        String regex = "\\p{Graph}"; // [\\p{Alnum}\\p{Punct}]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("1").matches());
        assertTrue(p.matcher("!").matches());
        assertFalse(p.matcher("\t").matches());
        assertFalse(p.matcher(" ").matches());
    }

    @Test
    public void print() {
        // A printable character
        String regex = "\\p{Print}"; // [\\p{Graph}\\x20]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("1").matches());
        assertTrue(p.matcher("!").matches());
        assertTrue(p.matcher(" ").matches());
        assertFalse(p.matcher("\t").matches());
    }

    @Test
    public void blank() {
        // A space or a tab
        String regex = "\\p{Blank}"; // [ \t]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher(" ").matches());
        assertTrue(p.matcher("\t").matches());
        assertFalse(p.matcher("1").matches());
        assertFalse(p.matcher(" 1").matches());
    }

    @Test
    public void control() {
        String regex = "\\p{Cntrl}"; // [\\x00-\\x1F\\x7F]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("\t").matches());
        assertTrue(p.matcher("\n").matches());
        assertTrue(p.matcher("\r").matches());
        assertTrue(p.matcher("\f").matches());
        assertTrue(p.matcher("\u0007").matches());
        assertTrue(p.matcher("\u001B").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void xdigit() {
        // A hexadecimal digit
        String regex = "\\p{XDigit}"; // [0-9a-fA-F]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("0").matches());
        assertTrue(p.matcher("F").matches());
        assertTrue(p.matcher("f").matches());
    }

    @Test
    public void space() {
        // A whitespace characte
        String regex = "\\p{Space}+"; // [ \t\n\x0B\f\r]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher(" \t\n\r\f").matches());
        assertFalse(p.matcher("1").matches());
    }
}