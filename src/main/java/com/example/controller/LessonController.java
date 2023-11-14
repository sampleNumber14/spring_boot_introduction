package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.SampleForm;

@Controller
@RequestMapping("/lesson")
//http://localhost:8080/lesson
public class LessonController {

	// http://localhost:8080/lesson/ にリクエストするとindex()が実行される
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Hello World!";
	}

	// http://localhost:8080/lesson/test にリクエストするとtest()が実行される
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Good Evening!";
	}

	// http://localhost:8080/lesson/math/14 にリクエストするとmath()が実行される
	@GetMapping("/math/{num}")
	@ResponseBody
	public Integer math(@PathVariable(name="num") Integer number) {
		return number;
	}

	// http://localhost:8080/lesson/sample にリクエストするとsample()が実行される
	@GetMapping("/sample")
	public String sample(Model model) {
		String text="Hello Spring Boot!!";
		model.addAttribute("message",text);
		return "index";
	}


	// http://localhost:8080/lesson/request_test?name=太郎&bloodType=AB
	@GetMapping("/request_test")
	// 返り値をレスポンスとして扱います
	@ResponseBody
	// name ... 名前、 bloodType ... 血液型
	// パラメータ名と仮引数名が同じな場合、@RequestParam String nameのようにパラメータ名の指定を省略できます
	public String getTest(@RequestParam("name") String name
			, @RequestParam("bloodType") String bloodType) {
		return "名前: " + name + "<br>血液型: " + bloodType;
	}


	// http://localhost:8080/lesson/request_test
	@PostMapping("/request_test")
	@ResponseBody
	public String postTest(SampleForm sampleForm) {
		return "名前: " + sampleForm.getName() + "<br>血液型: " + sampleForm.getBloodType();
	}

	// http://localhost:8080/lesson/form_test
	@GetMapping("/form_test")
	// SampleFormをModel属性にセットするだけであればModelクラスは不要になります
	public String formTest(@ModelAttribute SampleForm sampleForm) {
		return "lesson/form_test";
	}

}


