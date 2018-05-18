package org.hibernate.bugs;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Assert;
import org.junit.Test;

import models.common.Attachment;
import models.specific.Event;
import models.specific.RequestWithEagerEvents;
import models.specific.RequestWithLazyEvents;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(RequestWithLazyEvents.class, RequestWithEagerEvents.class, Event.class, Attachment.class);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE)
				.setProperty(AvailableSettings.SHOW_SQL, FALSE));
	}

	@Test
	public void hhh12601() {
		doInOpenTransaction((s, tx) -> {
			RequestWithLazyEvents e1 = new RequestWithLazyEvents();
			e1.getEvents().add(save(s, new Event()));
			s.save(e1);

			RequestWithEagerEvents e2 = new RequestWithEagerEvents();
			e2.getEvents().add(save(s, new Event()));
			s.save(e2);
			s.flush();

		});

		doInOpenTransaction((s, tx) -> {
			RequestWithEagerEvents eager = (RequestWithEagerEvents) s.createCriteria(RequestWithEagerEvents.class).uniqueResult();
			Assert.assertTrue("Failed with eager collection!", eager.getEvents().size() == 1);
			RequestWithLazyEvents lazy = (RequestWithLazyEvents) s.createCriteria(RequestWithLazyEvents.class).uniqueResult();
			Assert.assertTrue("Failed with lazy collection!", lazy.getEvents().size() == 1);
		});

	}

}
