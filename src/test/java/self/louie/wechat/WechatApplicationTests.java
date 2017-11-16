package self.louie.wechat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import self.louie.wechat.beans.QRCodeTicket;
import self.louie.wechat.service.MessageService;
import self.louie.wechat.service.QRCodeService;
import self.louie.wechat.service.SignatureService;
import self.louie.wechat.service.impl.MessageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	QRCodeService service;
	@Test
	public void testQRCodeTicket(){
		QRCodeTicket ticket = service.getQRCodeTicket("louie");
		System.out.println(ticket.getUrl());
	}

	@Test
	public void testXmlToBean(){
		String xmlString = "<xml><ToUserName><![CDATA[gh_0cd8e392ba18]]></ToUserName>\n" +
				"<FromUserName><![CDATA[oBNDQjm2KEkIvJIWzeNLainWvgCM]]></FromUserName>\n" +
				"<CreateTime>1510708490</CreateTime>\n" +
				"<MsgType><![CDATA[event]]></MsgType>\n" +
				"<Event><![CDATA[SCAN]]></Event>\n" +
				"<EventKey><![CDATA[louie]]></EventKey>\n" +
				"<Ticket><![CDATA[gQFV8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAySzNKUE1ONGdiZ2UxM0h4UDFxY0cAAgTrlAtaAwQAjScA]]></Ticket>\n" +
				"</xml>";
		MessageService messageService = new MessageServiceImpl();
		String message = messageService.parseMessage(xmlString);

	}



}
