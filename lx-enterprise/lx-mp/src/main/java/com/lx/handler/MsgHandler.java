package com.lx.handler;

import com.google.common.collect.Lists;
import com.lx.builder.ImageBuilder;
import com.lx.builder.NewsBuilder;
import com.lx.builder.TextBuilder;
import com.lx.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        if (wxMessage.getContent().equals("sb")) {

            List<WxMpXmlOutNewsMessage.Item> list = Lists.newArrayList();

            WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
            item.setTitle("测试1");
            item.setPicUrl("https://avatar.csdnimg.cn/2/1/D/1_laksdbaksjfgba.jpg");
            item.setUrl("http://www.baidu.com");
            item.setDescription("测试图片1");
            list.add(item);


            WxMpXmlOutNewsMessage.Item item1 = new WxMpXmlOutNewsMessage.Item();
            item1.setTitle("测试2");
            item1.setPicUrl("https://scj-media-hz.oss-cn-hangzhou.aliyuncs.com/files/20191102/446fcaa21deb5c041747c5e69f4381c908e5cbcb.jpg");
            item1.setUrl("http://www.google.com");
            item1.setDescription("测试图片2");
            list.add(item1);


            WxMpXmlOutMessage wxMpXmlOutMessage = new NewsBuilder().build(list, wxMessage, weixinService);
            return wxMpXmlOutMessage;
        } else if (wxMessage.getContent().equals("img")) {

            return new ImageBuilder().build("gyOZ4p8HVAcSPTYl2WMA6Xi8EI21UBKoYuri3f3CvsU", wxMessage, weixinService);
        } else {
            return new TextBuilder().build(content, wxMessage, weixinService);
        }

    }

}
