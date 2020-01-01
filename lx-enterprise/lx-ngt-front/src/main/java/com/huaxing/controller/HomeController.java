package com.huaxing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 注册
 */

@RequestMapping("/")
@Controller
public class HomeController {
	@Value("${lx.api.url.sso}")
	private String ssoUrl;
	@Value("${lx.api.url.ngt}")
	private String ngtUrl;

	@RequestMapping("/")
	public String home(Model model) {

		addAttribute(model);
		return "home";
	}

	@RequestMapping("/index")
	public String index() {

		return "index";
	}

	private void addAttribute(Model model) {
		model.addAttribute("ssoUrl", ssoUrl);
		model.addAttribute("ngtUrl", ngtUrl);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("curDay", format.format(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		model.addAttribute("firstDay", format.format(cal.getTime()));
	}

	@RequestMapping("/content")
	public String content() {

		return "content";
	}

	@RequestMapping("/login")
	public String login(Model model) {

		addAttribute(model);
		return "login";
	}

	@RequestMapping("/loginout")
	public String loginout() {

		return "login";
	}

	@RequestMapping("/unauthorization")
	public String auth() {

		return "403";
	}

}
