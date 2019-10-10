package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class PredefinedCharacterClassesTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void anyCharacter() {
        // Any character (may or may not match line terminators)
        String regex = ".";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("1").matches());
        assertTrue(p.matcher("$").matches());

        // Line Terminators
        assertFalse(p.matcher("\n").matches());
        assertFalse(p.matcher("\r").matches());
        assertFalse(p.matcher("\u0085").matches()); // A next-line character
        assertFalse(p.matcher("\u2028").matches()); // A line-separator character 
        assertFalse(p.matcher("\u2029").matches()); // A paragraph-separator character 
    }

    @Test
    public void digit() {
        // A digit
        String regex = "\\d"; // [0-9]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("1").matches());
        assertFalse(p.matcher("a").matches());
    }

    @Test
    public void nonDigit() {
        // A non-digit
        String regex = "\\D"; // [^0-9]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void whiteSpace() {
        // A whitespace character
        String regex = "\\s"; // [ \t\n\x0B\f\r]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher(" ").matches());
        assertTrue(p.matcher("\t").matches());
        assertTrue(p.matcher("\n").matches());
        assertTrue(p.matcher("\r").matches());
        assertTrue(p.matcher("\f").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void nonWhiteSpace() {
        // A whitespace character
        String regex = "\\S"; // [^\\s]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("1").matches());
        assertTrue(p.matcher("$").matches());
        assertTrue(p.matcher("a").matches());

        assertFalse(p.matcher(" ").matches());
        assertFalse(p.matcher("\t").matches());
        assertFalse(p.matcher("\n").matches());
        assertFalse(p.matcher("\r").matches());
        assertFalse(p.matcher("\f").matches());
    }

    @Test
    public void word() {
        // A word character
        String regex = "\\w"; // [a-zA-Z_0-9]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("A").matches());
        assertTrue(p.matcher("1").matches());
        assertFalse(p.matcher(" ").matches());
        assertFalse(p.matcher("%").matches());
    }

    @Test
    public void nonWord() {
        // A non-word character
        String regex = "\\W"; // [^\\w]
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher(" ").matches());
        assertTrue(p.matcher("%").matches());
        assertFalse(p.matcher("1").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("A").matches());
    }
}

