package sample04.controller;

import java.text.ParseException;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

	@RequestMapping("/web1")
	@ResponseBody
	public String web1() throws ParseException {
		System.out.println("web1");
		return "hello, web1";
	}
}
