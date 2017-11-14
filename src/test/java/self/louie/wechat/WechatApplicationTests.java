package self.louie.wechat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import self.louie.wechat.service.WechatService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

	@Autowired
	WechatService service;

	@Test
	public void contextLoads() {
	}



}
