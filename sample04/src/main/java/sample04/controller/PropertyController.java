package sample04.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertyController {

	@Value("${app.emb.a1}")
	private String app_emb_a1;

	@Value("${app.extern.a1}")
	private String app_extern_a1;

	@Autowired
	private Environment env;

	@RequestMapping("/property1")
	@ResponseBody
	public String property1() throws ParseException, UnsupportedEncodingException {
		System.out.println("property1");

		String app_emb_a2 = env.getProperty("app.emb.a2");
		String app_extern_a2 = env.getProperty("app.extern.a2");

		String rtn = "hello, web2. emb.a1=" + this.app_emb_a1 + ". emb.a2=" + app_emb_a2;
		rtn += " extern.a1=" + this.app_extern_a1 + " extern.a2=" + app_extern_a2;

		return rtn;
	}

}
