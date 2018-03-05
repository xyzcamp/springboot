package sample02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sample02.helper.URLHelper;
import sample02.model.Customer;
import sample02.model.Product;

@Controller
public class ViewController {

	@Autowired
	private URLHelper urlHelper;

	@ModelAttribute("urls")
	public URLHelper urls() {
		return this.urlHelper;
	}

	// jsp
	@RequestMapping(value = "/view1/{type}")
	public String view1(Model model, @PathVariable String type) {
		Customer customer = new Customer();
		customer.id = 5;
		customer.active = false;

		for (int n = 1; n <= 3; n++) {
			Product p = new Product();
			p.id = n;
			p.title = "標" + n;
			p.descr = "描述" + n;
			customer.products.add(p);
		}

		model.addAttribute("content", customer);
		model.addAttribute("memo1", "中文堃");

		switch (type) {
		case "jsp":
			return "theme01/jsp1";
		case "freemarker":
			return "theme01/freemarker1";
		case "thymeleaf":
			return "theme01/thymeleaf1";
		}

		return "theme01/jsp1";
	}

}
