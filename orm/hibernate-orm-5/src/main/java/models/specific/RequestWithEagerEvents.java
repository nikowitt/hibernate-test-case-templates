package models.specific;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestWithEagerEvents extends RelatedToEvents {

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public Set<Event> getEvents() {
		return events;
	}

}
