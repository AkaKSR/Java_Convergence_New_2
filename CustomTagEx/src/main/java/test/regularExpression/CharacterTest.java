package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

public class CharacterTest {

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    @Test
    public void characterX() {
        // 한 문자
        String regex = "x";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("x").matches());
        assertFalse(p.matcher("a").matches());
    }

    @Test
    public void backslash() {
        // 백슬래쉬 문자
        String regex = "\\\\";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("\\").matches());
        assertFalse(p.matcher("a").matches());
    }

    // 아래 부분은 뭘 뜻하는 지 모르겠다.
    // 다음에 추가하자.
    // \0n The character with octal value 0n (0 <= n <= 7)
    // \0nn The character with octal value 0nn (0 <= n <= 7)
    // \0mnn The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7)
    // \xhh The character with hexadecimal value 0xhh
    // \x{h...h} The character with hexadecimal value 0xh...h
    // (Character.MIN_CODE_POINT <= 0xh...h <= Character.MAX_CODE_POINT)

    @Test
    public void hexadecimalValue0xhh() {
        // 16진수 0xhh값과 일치
        String regex = "\\x41";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("A").matches());
    }

    @Test
    public void tab() {
        String regex = "\\t";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("\t").matches());
        assertTrue(p.matcher("\u0009").matches());
    }

    @Test
    public void lineFeed() {
        String regex = "\\n";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("\n").matches());
    }

    @Test
    public void carriageReturn() {
        String regex = "\\r";
        Pattern p = Pattern.compile(regex);

        assertTrue(p.matcher("\r").matches());
    }
}