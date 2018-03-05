package sample01.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//ref: http://jinnianshilongnian.iteye.com/blog/1698916
//ref: http://jinnianshilongnian.iteye.com/blog/1705701
@Controller
public class RequestController {

	// @RequestMapping
	@RequestMapping("/web1")
	@ResponseBody
	public String web1() {
		return "w1";
	}

	// @RequestMapping
	// @RequestMapping ref: http://blog.csdn.net/a67474506/article/details/46361195
	@RequestMapping(value = "/web2", method = { RequestMethod.POST, RequestMethod.GET }, produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public String web2() {
		return "w2";
	}

	// @PathVariable
	@RequestMapping(value = "/web3/{id}/{name}")
	@ResponseBody
	public String web3(@PathVariable("id") int no, @PathVariable String name) {
		return "w3:" + no + ":" + name;
	}

	// @ParamVariable
	// 接收參數時, 不處理XSS, ref: http://javatech.wang/index.php/archives/49/
	@RequestMapping(value = "/web4")
	@ResponseBody
	public String web4(@RequestParam("id") int no, @RequestParam(required = false) String name,
			@RequestParam String[] acts) {
		return "w4:" + no + ":" + name + ":" + acts.length;
	}

	// @ParamVariable
	@RequestMapping(value = "/web5")
	@ResponseBody
	public String web5(@RequestParam MultipartFile file1, @RequestParam MultipartFile[] file2)
			throws IllegalStateException, IOException {

		File f = new File("W:/" + file1.getOriginalFilename());
		file1.transferTo(f);

		for (MultipartFile f2 : file2) {
			File fx = new File("W:/" + f2.getOriginalFilename());
			f2.transferTo(fx);
		}

		return "w5:" + f.getAbsolutePath() + ":" + file1.getOriginalFilename();
	}

	// Method Arguments
	// ref
	// https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments
	@RequestMapping(value = "/web6")
	@ResponseBody
	public String web6(HttpServletRequest httpServletRequest) {
		return "w6:" + httpServletRequest.getRemoteHost();
	}
}
