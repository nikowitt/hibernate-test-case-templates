package models.specific;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import models.common.Base;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event extends Base {

	private Base parent;

	@ManyToOne(fetch = FetchType.LAZY)
	public Base getParent() {
		return parent;
	}

	public Event setParent(Base parent) {
		this.parent = parent;
		return this;
	}
}
