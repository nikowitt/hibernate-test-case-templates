package models.specific;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import models.common.Base;

@Entity
public class TestEntity extends Base {

	private TestEntity parent;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public TestEntity getParent() {
		return parent;
	}

	public void setParent(TestEntity parent) {
		this.parent = parent;
	}
}
