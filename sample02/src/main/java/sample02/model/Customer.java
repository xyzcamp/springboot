package sample02.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Customer {

	public long id;
	private String firstname;
	public List<Product> products = new ArrayList<Product>();
	private String lastname;
	public Date birth;
	public boolean active;
	public String memo1;

	public String getName() {
		return "firstname";
	}

	private String getMemo2() {
		return "firstname";
	}

	public boolean getActive() {
		return this.active;
	}

	public List<Product> getProducts() {
		return this.products;
	}
}
