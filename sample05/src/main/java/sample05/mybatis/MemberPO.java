package sample05.mybatis;

import java.util.Date;

public class MemberPO {

	private Long m_id;
	private String name;
	private Date birth;
	private Integer sex;
	private Boolean active;

	public Long getM_id() {
		return m_id;
	}

	public void setM_id(Long m_id) {
		this.m_id = m_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
