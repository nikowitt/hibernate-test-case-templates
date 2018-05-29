package org.hibernate.bugs;

import org.hibernate.cfg.AvailableSettings;
import org.junit.Test;

import models.common.CommonEntity;

public class TestCase extends AbstractTestCase {

	public TestCase() {
		super(CommonEntity.class, SECURITY_ENTITIES);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE));
	}

	@Test
	public void hhXXX() {
		
	}

}
