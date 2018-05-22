package models.specific;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import models.common.Base;

@MappedSuperclass
public abstract class RelatedToEvents extends Base {
	protected Set<Event> events = new LinkedHashSet<>();

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@Transient
	// do this to be able to modify the mapping table
	public abstract Set<Event> getEvents();
}
