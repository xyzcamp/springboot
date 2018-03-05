package sample03.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "birth", "active", "id" })
public class Customer {

	public long id;
	@JsonProperty
	private String firstname;
	
	public List<Product> products = new ArrayList<Product>();

	@JsonProperty("n_lastname")
	private String lastname;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT+0:00")
	public Date birth;

	@JsonProperty("n_active")
	public boolean active;

	@JsonIgnore
	public String memo1;

	public String getName() {
		return "firstname";
	}

	@JsonIgnore
	public String getMemo2() {
		return "firstname";
	}
}
