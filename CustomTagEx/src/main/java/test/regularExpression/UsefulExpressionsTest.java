package test.regularExpression;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Test;

public class UsefulExpressionsTest {

    private Pattern pattern;

    /*
     * 정규표현식 참고
     * http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     */

    /*
     * 예제 참고
     * 1.
     * http://blog.naver.com/PostView.nhn?blogId=fightingdk&logNo=60124162230&
     * redirect=Dlog&widgetTypeCall=true
     * 
     * 2. http://goodidea.tistory.com/86
     * 3. http://blog.outsider.ne.kr/360
     */

    @After
    public void after() {
        System.out.println(pattern.pattern());
    }

    // @Test
    // public void test() {
    // String regex = "/";
    // pattern = Pattern.compile(regex);
    //
    // assertTrue(pattern.matcher("/").matches());
    // }

    @Test
    public void ip4Address() {
        // ((([0-9])|([1-9]\d{1})|(1\d{2})|(2[0-4]\d)|(25[0-5]))\.){3}(([0-9])|([1-9]\d{1})|(1\d{2})|(2[0-4]\d)|(25[0-5]))

        /*
         * ([0-9])는 한 자리 숫자
         * ([1-9]\\d{1})는 2 자리 숫자, 즉 10~99
         * (1\d{2})는 1로 시작하는 세 자리 숫자, 즉 100에서 199 사이의 숫자와 일치한다.
         * (2[0-4]\d)는 200에서 249 사이의 숫자와 일치한다.
         * (25[0-5])는 250에서 255 사이의 숫자와 일치한다.
         */

        String regex = "((([0-9])|([1-9]\\d{1})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}(([0-9])|([1-9]\\d{1})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("0.0.0.0").matches());
        assertTrue(pattern.matcher("0.10.100.255").matches());
        assertFalse(pattern.matcher("00.10.100.255").matches());
        assertFalse(pattern.matcher("000.10.100.255").matches());
        assertFalse(pattern.matcher("-.0.0.0").matches());
        assertFalse(pattern.matcher("a.0.0.0").matches());
        assertFalse(pattern.matcher("256.0.0.0").matches());
    }

    @Test
    public void url() {
        // ^(https?):\/\/([^:\/\s]+)(:([^\/]*))?((\/[^\s/\/]+)*)?\/?([^#\s\?]*)(\?([^#\s]*))?(#(\w*))?$

        String regex = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
        pattern = Pattern.compile(regex);

        assertTrue(pattern
                .matcher(
                        "https://goodidea.tistory.com:8888/qr/aaa/ddd.html?abc=def&ddd=fgf#sharp")
                .matches());
        assertTrue(pattern.matcher("http://dextto.tistory.com").matches());
        assertTrue(pattern.matcher("http://blog.daum.net/dexter").matches());
        assertTrue(pattern.matcher("http://www.daum.net:80/index.cfm").matches());
        assertTrue(pattern.matcher("http://xxx:password@www.daum.net").matches());
        assertTrue(pattern.matcher("http://localhost/index.php?ab=1&c=2").matches());
        assertTrue(pattern.matcher("http://localhost:8080").matches());
        assertTrue(pattern
                .matcher(
                        "http://dextto.tistory.com/admin/entry/post/?id=150&returnURL=/entry/JAVA-Regular-Expression-%EC%A0%95%EA%B7%9C-%ED%91%9C%ED%98%84%EC%8B%9D-%EC%98%88%EC%A0%9C")
                .matches());
    }

    @Test
    public void emailAdress() {
        // (\w+\.)*\w+@(\w+\.)+[A-Za-z]+

        String regex = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("abc@def.com").matches());
        assertTrue(pattern.matcher("abc@def.comcomcom").matches());
        assertTrue(pattern.matcher("abc@daum.co.kr").matches());
        assertTrue(pattern.matcher("abc@daum.co.kr.kr").matches());
        assertFalse(pattern.matcher("abc@daum.co.kr.kr.").matches());
        assertFalse(pattern.matcher("@def.com").matches());
        assertFalse(pattern.matcher("a$def.com").matches());
        assertFalse(pattern.matcher("abc@.com").matches());
        assertFalse(pattern.matcher("abc@def").matches());
        assertFalse(pattern.matcher("abc@def.").matches());
    }

    @Test
    public void htmlComment() {
        // <!-{2}(.|\n)*?-{2}>
        String regex = "<!-{2}(.|\\n)*?-{2}>";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("<!-- something -->").matches());
        assertTrue(pattern.matcher("<!-- line1 \n line2 -->").matches());
        assertFalse(pattern.matcher("<~!-- something -->").matches());
        assertFalse(pattern.matcher("<~!-- something -- >").matches());
    }

    @Test
    public void htmlTag() {
        // ^<([a-z]+)([^<]+)*(?:>(.*)<\/\1>|\s+\/>)$
        String regex = "^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s*\\/>)$";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("<tag></tag>").matches());
        assertTrue(pattern.matcher("<tag/>").matches());
        assertTrue(pattern.matcher("<img src=\"img.jpg\"/>").matches());
        assertTrue(pattern.matcher("<a href=\"http://net.tutsplus.com/\">Nettuts+</a>").matches());
        assertFalse(pattern.matcher("<tag>").matches());
        assertFalse(pattern.matcher("<tag><tag>").matches());
        assertFalse(pattern.matcher("<tag></tag >").matches());
    }

    @Test
    public void socialNumber() {
        // 주민등록 번호
        // 주민번호 체계 공식에 대입하기 전에 간단히 자리수 체크만 할 때 쓴다.
        // \d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])-[1-4]\d{6}
        String regex = "\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])-[1-4]\\d{6}";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("760123-1108316").matches());
        assertTrue(pattern.matcher("991231-4444444").matches());
        assertFalse(pattern.matcher("991231-9999999").matches());
    }

    @Test
    public void hangul() {
        // 한글
        String regex = "[가-힣]+";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("가나다").matches());
        assertTrue(pattern.matcher("홍길동").matches());
    }

    @Test
    public void userName() {
        // 3자~16자의 사용자 id (영문/숫자/_/- 만 가능)
        String regex = "^[a-z0-9_-]{3,16}$";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("dextto").matches());
        assertTrue(pattern.matcher("dextto76").matches());
        assertTrue(pattern.matcher("_dextto-").matches());
        assertTrue(pattern.matcher("1234567890123456").matches());
        assertFalse(pattern.matcher("12345678901234567").matches());
        assertFalse(pattern.matcher("dextt@").matches());
    }

    @Test
    public void hexaValue() {
        String regex = "^#?([a-f0-9]{6}|[a-f0-9]{3})$";
        pattern = Pattern.compile(regex);

        assertTrue(pattern.matcher("#a3c113").matches());
        assertTrue(pattern.matcher("#a3c").matches());
    }
}