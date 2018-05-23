package org.hibernate.bugs;

import javax.persistence.SharedCacheMode;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Test;

import models.common.Attachment;
import models.common.CommonEntity;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(CommonEntity.class, Attachment.class);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE)
				.setProperty(AvailableSettings.JPA_SHARED_CACHE_MODE, String.valueOf(SharedCacheMode.ENABLE_SELECTIVE)));
	}

	@Test
	public void hhh12613() {
		doInOpenTransaction((s, tx) -> {
			CommonEntity u = save(s, new CommonEntity());
			Attachment m = save(s, new Attachment());
			m.getData().setBlob(s.getLobHelper().createBlob("TEST".getBytes()));
			u.getAttachments().add(m);
			s.flush();

		});

	}

}
