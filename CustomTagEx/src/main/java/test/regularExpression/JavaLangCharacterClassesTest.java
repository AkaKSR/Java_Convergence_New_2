package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class JavaLangCharacterClassesTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void javaLowerCase() {
        // java.lang.Character.isLowerCase()와 동일
        String regex = "\\p{javaLowerCase}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertFalse(p.matcher("A").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void javaUpperCase() {
        // java.lang.Character.isUpperCase()와 동일
        String regex = "\\p{javaUpperCase}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("A").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void whiteSpace() {
        // java.lang.Character.isWhitespace()와 동일
        String regex = "\\p{javaWhitespace}+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher(" \t\n\r\f").matches());
        assertFalse(p.matcher("\\x0B").matches()); // VT(Vertical Tab)은 미지원
        assertFalse(p.matcher("1").matches());
    }

    @Test
    public void javaMirrored() {
        // java.lang.Character.isWhitespace()와 동일
        String regex = "\\p{javaMirrored}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("(").matches());
        assertTrue(p.matcher(")").matches());
        assertTrue(p.matcher("[").matches());
        assertTrue(p.matcher("]").matches());
        assertTrue(p.matcher("{").matches());
        assertTrue(p.matcher("}").matches());
        assertTrue(p.matcher("<").matches());
        assertTrue(p.matcher(">").matches());
        assertFalse(p.matcher("a").matches());
    }

}