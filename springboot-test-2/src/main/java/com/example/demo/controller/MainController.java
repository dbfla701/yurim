package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SignUpForm;
import com.example.demo.model.AccessIp;
import com.example.demo.model.Account;
import com.example.demo.service.AccessIpService;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

	private final AccountService service;
	private final AccessIpService ipService;

	@GetMapping("/login")
	public String loginPage() {
		int adminCnt = service.count();

		if (adminCnt != 0) {
			return "login";
		}

		return "redirect:/signUp";
	}

	@GetMapping("/loginAfter")
	public String loginAfter(Model model) {
		List<AccessIp> lists = new ArrayList<>();
		lists = ipService.accessIp();
		log.info("ip목록 조회");
		model.addAttribute("lists", lists);
		return "loginAfter";
	}

	@GetMapping("/signUp")
	public String signUp(Model model) {
		int adminCtn = service.count();
		log.info("admin cnt : {}", adminCtn);
		model.addAttribute(new SignUpForm());
		if (adminCtn != 0) { // id가 있으면
			return "redirect:/login";
		} else { // id가 없으면
			return "signUp";
		}
	}

	@PostMapping("/signUp")
	public String postSignUp(@Valid SignUpForm signUpForm, BindingResult result) {
		
		if(result.hasErrors()) {
			return "signUp";
		}
			service.saveAdmin(signUpForm.getUsername(), signUpForm.getPassword());
			return "redirect:/login";
		}
	

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model) {
		boolean lists = ipService.delete(id);
		model.addAttribute("lists", lists);
		log.info("ip 삭제", id);
		return "redirect:/loginAfter";
	}

	@GetMapping("/write")
	public String write(Model model) {
		log.info("write");
		model.addAttribute("data", new AccessIp());
		return "write";
	}

	@PostMapping("/write")
	public String postWrite(@ModelAttribute AccessIp data) {
		ipService.insertIp(data);
		return "redirect:/loginAfter";
	}
}
