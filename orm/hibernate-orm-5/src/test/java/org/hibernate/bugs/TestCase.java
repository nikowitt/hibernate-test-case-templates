package org.hibernate.bugs;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Test;

import models.common.CommonEntity;
import models.common.security.JafSid;
import models.common.security.User;

public class TestCase extends AbstractTestCase {

	public TestCase() {
		super(CommonEntity.class, SECURITY_ENTITIES);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE)
				.setProperty(AvailableSettings.SHOW_SQL, TRUE).setProperty(AvailableSettings.FORMAT_SQL, FALSE));
	}

	@Test
	public void hhh12642() {
		doInOpenTransaction((s, tx) -> {
			User u = new User();
			s.save(u.setSid(save(s, new JafSid())));

			CommonEntity c = save(s, new CommonEntity().setUser(u));

		});

		doInOpenTransaction((s, tx) -> {
			s.createCriteria(CommonEntity.class).list();
		});
	}

}
