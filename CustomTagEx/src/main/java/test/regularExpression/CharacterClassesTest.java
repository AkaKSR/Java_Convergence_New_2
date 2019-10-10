package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class CharacterClassesTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void simpleClass() {
        String regex = "[abc]";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("b").matches());
        assertTrue(p.matcher("c").matches());
        assertFalse(p.matcher("d").matches());
    }

    @Test
    public void negation() {
        String regex = "[^abc]";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("d").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("b").matches());
        assertFalse(p.matcher("c").matches());
    }

    @Test
    public void range() {
        String regex = "[a-c]";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("b").matches());
        assertTrue(p.matcher("c").matches());
        assertFalse(p.matcher("d").matches());
    }

    @Test
    public void union() {
        String regex = "[a-d[m-p]]"; // [a-dm-p]와 동일
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("b").matches());
        assertTrue(p.matcher("d").matches());
        assertTrue(p.matcher("m").matches());
        assertTrue(p.matcher("o").matches());
        assertTrue(p.matcher("p").matches());
        assertFalse(p.matcher("e").matches());
        assertFalse(p.matcher("q").matches());
    }

    @Test
    public void intersection() {
        String regex = "[a-z&&[def]]";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("d").matches());
        assertTrue(p.matcher("e").matches());
        assertTrue(p.matcher("f").matches());
        assertFalse(p.matcher("a").matches());
        assertFalse(p.matcher("z").matches());
    }

    @Test
    public void subtraction() {
        String regex = "[a-z&&[^bc]]"; // [ad-z]와 동일
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("z").matches());
        assertTrue(p.matcher("d").matches());
        assertFalse(p.matcher("b").matches());
        assertFalse(p.matcher("c").matches());
    }

    @Test
    public void subtraction2() {
        String regex = "[a-z&&[^m-p]]"; // [a-lq-z]와 동일
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("a").matches());
        assertTrue(p.matcher("l").matches());
        assertTrue(p.matcher("q").matches());
        assertTrue(p.matcher("z").matches());
        assertFalse(p.matcher("m").matches());
        assertFalse(p.matcher("p").matches());
    }
}