package org.hibernate.bugs;

import org.junit.Test;

import models.specific.Mail;

public class TestCase extends AbstractTestCase {
	public TestCase() {
		super(Mail.class);
	}

	@Test
	public void hhh12421() {
		System.out.println("Just compile with bytecode enhancement, it will crash. Set models.specific.AbstractMail.to PUBLIC, then it will work.");
	}
}
