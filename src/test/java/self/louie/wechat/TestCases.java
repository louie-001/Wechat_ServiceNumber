package self.louie.wechat;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by louie on 2017-11-15.
 */
public class TestCases {

    @Test
    public void urlEncode() throws UnsupportedEncodingException {
        String url = "https://www.xonro.com";
        url = URLEncoder.encode(url,"utf8");
        System.out.println(url);
    }

}
