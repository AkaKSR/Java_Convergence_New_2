package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class GreedyQuantifiersTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void onceOrNotAll() {
        // X, once or not at all
        String regex = "X?";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("").matches());
        assertTrue(p.matcher("X").matches());
        assertFalse(p.matcher("XX").matches());
    }

    @Test
    public void zeroOrMoreTimes() {
        // X, zero or more times
        String regex = "X*";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("").matches());
        assertTrue(p.matcher("X").matches());
        assertTrue(p.matcher("XX").matches());
    }

    @Test
    public void oneOrMoreTimes() {
        // X, one or more times
        String regex = "X+";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("X").matches());
        assertTrue(p.matcher("XX").matches());
        assertFalse(p.matcher("").matches());
    }

    @Test
    public void exactlyNTimes() {
        // X, exactly n times
        String regex = "X{2}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("XX").matches());
        assertFalse(p.matcher("").matches());
        assertFalse(p.matcher("X").matches());
        assertFalse(p.matcher("XXX").matches());
    }

    @Test
    public void atLeastNTimes() {
        // X, at least n times : n개 이상
        String regex = "X{2,}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("XX").matches());
        assertTrue(p.matcher("XXX").matches());
        assertFalse(p.matcher("").matches());
        assertFalse(p.matcher("X").matches());
    }

    @Test
    public void atLeastNButNotMoreThanMTimes() {
        // X, at least n but not more than m times
        String regex = "X{2,4}";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("XX").matches());
        assertTrue(p.matcher("XXX").matches());
        assertTrue(p.matcher("XXXX").matches());
        assertFalse(p.matcher("").matches());
        assertFalse(p.matcher("X").matches());
        assertFalse(p.matcher("XXXXX").matches());
    }
}