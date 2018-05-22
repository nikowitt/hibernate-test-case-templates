package org.hibernate.bugs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Test;

import models.common.Attachment;
import models.specific.TestEntity;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(TestEntity.class, Attachment.class);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE));
	}

	@Test
	public void hhhXXX() {
		TestEntity u1 = new TestEntity();
		doInOpenTransaction((s, tx) -> {
			save(s, u1);
			assertThat(u1.getId(), is(notNullValue()));
		});

		doInOpenTransaction((s, tx) -> {
			assertThat(u1.getId(), is(notNullValue()));
			TestEntity cloned = (TestEntity) s.get(TestEntity.class, u1.getId()).clone();
			assertThat(s.contains(cloned), is(true));
			s.evict(cloned);
			assertThat(s.contains(cloned), is(false));
			assertThat(cloned.getId(), is(nullValue()));
			cloned = save(s, cloned);
			assertThat(s.contains(cloned), is(true));
			assertThat(cloned.getId(), is(notNullValue()));
			assertThat(cloned.getId(), not(is(u1.getId())));
		});
	}

}
