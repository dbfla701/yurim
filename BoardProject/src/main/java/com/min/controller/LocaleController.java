package com.min.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LocaleController {
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	LocaleResolver localeResolver;
	
	@RequestMapping(value="/i18n.do", method=RequestMethod.GET)
	public String locale(Locale locale, HttpServletRequest request, Model model) {
		model.addAttribute("clientLocale", locale);
		model.addAttribute("sessionLocale", localeResolver.resolveLocale(request));
		model.addAttribute("siteCount", messageSource.getMessage("msg.first", null, locale));
		return "i18n";
		
	}

}
