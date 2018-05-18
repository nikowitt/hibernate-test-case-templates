package models.specific;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.MappedSuperclass;

import models.common.Base;

@MappedSuperclass
public abstract class RelatedToEvents extends Base {
	protected Set<Event> events = new LinkedHashSet<>();

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
}
