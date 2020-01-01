package com.lx.controller;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@AllArgsConstructor
@RestController
@RequestMapping("/wx/pic/{appid}")
public class WxPicController {
    private final WxMpService wxService;


    @GetMapping("/pics")
    public WxMpMaterialNewsBatchGetResult getSelfMenuInfo(@PathVariable String appid) throws WxErrorException {
        return this.wxService.switchoverTo(appid).getMaterialService().materialNewsBatchGet(0, 10);
    }
}
