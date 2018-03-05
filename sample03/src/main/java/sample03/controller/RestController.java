package sample03.controller;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample03.model.Customer;
import sample03.model.Product;

@Controller
public class RestController {

	@RequestMapping("/rest1")
	@ResponseBody
	public String rest1() throws ParseException {
		return "hello world";
	}

	@RequestMapping("/rest2")
	@ResponseBody
	public Customer rest2() throws ParseException {
		Customer customer = new Customer();
		customer.active = true;
		customer.birth = DateUtils.parseDateStrictly("2018-01-02 13:4:15", "yyyy-MM-dd HH:mm:ss");
		return customer;
	}

	@RequestMapping("/rest3")
	@ResponseBody
	public ResponseEntity<?> rest3(Model model) {
		Customer customer = new Customer();
		customer.active = true;

		for (int n = 1; n <= 3; n++) {
			Product p = new Product();
			p.id = n;
			customer.products.add(p);
		}

		// Solution 1
		model.addAttribute("code", "ok001");
		model.addAttribute("content", customer);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);

		// Solution 2
		// Map<String, Object> map = new LinkedHashMap<String, Object>();
		// map.put("code", "ok002");
		// map.put("content", customer);
		// return ResponseEntity.ok().body(map);
	}

}
