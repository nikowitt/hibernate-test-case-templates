package org.hibernate.bugs;

import org.junit.Test;

import models.common.Attachment;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(Attachment.class);
		//	configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE));
	}

	@Test
	public void hhhXXX() {
		doInOpenTransaction((s, tx) -> {
		
		});
	}

}
