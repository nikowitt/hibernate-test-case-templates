package models.common;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.common.security.User;

@Entity
public class CommonEntity extends Base {

	private String name;
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = LAZY)
	public User getUser() {
		return user;
	}

	public CommonEntity setUser(User user) {
		this.user = user;
		return this;
	}
}