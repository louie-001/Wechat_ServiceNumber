package self.louie.wechat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import self.louie.wechat.service.MessageService;
import self.louie.wechat.service.QRCodeService;
import self.louie.wechat.service.SignatureService;

/**
 * Created by louie on 2017-11-13.
 */
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr){
        return signatureService.checkSignature(signature,timestamp,nonce,echostr);
    }

    @RequestMapping(value = "/access",method = RequestMethod.POST)
    public String accessEvent(@RequestBody String message){
        messageService.parseMessage(message);
        return "";
    }

    @RequestMapping(value = "/qrcode/{userId}")
    public String getUserQRCodeUrl(@PathVariable String userId){
        return qrCodeService.getUserQRCodeUrl(userId);
    }

}
