package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class LogicalOperatorsTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void XFollowedByY() {
        // X followed by Y
        String regex = "XY";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("XY").matches());
        assertFalse(p.matcher("XX").matches());
    }

    @Test
    public void eitherXOrY() {
        // Either X or Y
        String regex = "X|Y";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("X").matches());
        assertTrue(p.matcher("Y").matches());
        assertFalse(p.matcher("Z").matches());
    }

    @Test
    public void asACapturingGroup() {
        // X, as a capturing group
        String regex = "(X)";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("X").matches());
        assertFalse(p.matcher("Y").matches());
    }

    @Test
    public void asACapturingGroup2() {
        String regex = "(a(b)?)+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("aba").matches());
        assertTrue(p.matcher("ab").matches());
        assertTrue(p.matcher("abab").matches());
        assertTrue(p.matcher("abaaba").matches());
    }
}