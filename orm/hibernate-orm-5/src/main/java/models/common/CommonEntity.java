package models.common;

import javax.persistence.Entity;

@Entity
public class CommonEntity extends Base {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}