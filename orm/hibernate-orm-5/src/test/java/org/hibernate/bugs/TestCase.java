package org.hibernate.bugs;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Test;

import models.common.Attachment;
import models.specific.Event;
import models.specific.RequestWithEvents;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(RequestWithEvents.class, Event.class, Attachment.class);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE)
				.setProperty(AvailableSettings.SHOW_SQL, FALSE));
	}

	@Test
	public void hhhXXX() {
		RequestWithEvents e2 = new RequestWithEvents();
		doInOpenTransaction((s, tx) -> {

			s.save(e2);
			e2.getEvents().add(saveAndFlush(s, new Event().setParent(e2)));
			s.flush();

		});
		doInOpenTransaction((s, tx) -> {
			RequestWithEvents ev = s.load(RequestWithEvents.class, e2.getId());
			Set<Event> newC = new LinkedHashSet<>();
			newC.add(saveAndFlush(s, new Event().setParent(ev)));
			Set<Event> oldC = ev.getEvents();
			oldC.clear();
			oldC.addAll(newC);
			s.flush();

		});
	}

}
