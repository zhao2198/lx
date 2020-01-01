package com.lx.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import java.util.List;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public class NewsBuilder {


    public WxMpXmlOutMessage build(List<WxMpXmlOutNewsMessage.Item> list, WxMpXmlMessage wxMessage,
                                   WxMpService service) {


        WxMpXmlOutNewsMessage.Item[] items = list.toArray(new WxMpXmlOutNewsMessage.Item[0]);
        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(items)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();

        return m;
    }

}
