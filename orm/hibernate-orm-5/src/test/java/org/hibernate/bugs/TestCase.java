package org.hibernate.bugs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.junit.Test;

import models.common.CommonEntity;
import models.common.security.JafSid;
import models.common.security.User;

public class TestCase extends AbstractTestCase {

	public TestCase() {
		super(CommonEntity.class, SECURITY_ENTITIES);
		configure(c -> c.setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, TRUE)
				.setProperty(AvailableSettings.SHOW_SQL, TRUE));
	}

	private static class IdDTO {
		private final int id;

		private IdDTO(int id) {
			this.id = id;
		}
	}

	private static class IdResultTransformer implements ResultTransformer {

		@Override
		public Object transformTuple(Object[] tuple, String[] aliases) {
			return new IdDTO((int) tuple[0]);
		}

		@Override
		public List transformList(List collection) {
			return collection;
		}
	}

	private static class UserResultTransformer implements ResultTransformer {

		@Override
		public Object transformTuple(Object[] tuple, String[] aliases) {
			return new UserDTO((int) tuple[0], (String) tuple[1]);
		}

		@Override
		public List transformList(List collection) {
			return collection;
		}
	}

	@Test
	public void hhh12706() {
		doInOpenTransaction((s, tx) -> {
			save(s, new User().setName("user").setSid(save(s, new JafSid().setSid("sid"))));
		});

		//works as expected
		doInOpenTransaction((s, tx) -> {
			CriteriaQuery<User> q = s.getCriteriaBuilder().createQuery(User.class);
			Root<User> root = q.from(User.class);
			q.select(root.get("id"));
			List<IdDTO> ids = s.createQuery(q).unwrap(Query.class)
					.setResultTransformer(new IdResultTransformer()).getResultList();
			assertThat(ids.get(0).id, is(2));
		});

		//works, but UserDTO must not be an inner class and needs to have appropriate constructor
		doInOpenTransaction((s, tx) -> {
			CriteriaQuery<UserDTO> q = s.getCriteriaBuilder().createQuery(UserDTO.class);
			Root<User> root = q.from(User.class);
			q.multiselect(root.get("id"), root.get("name"));
			List<UserDTO> ids = s.createQuery(q).getResultList();
			assertThat(ids.get(0).id, is(2));
			assertThat(ids.get(0).name, is("user"));
		});

		//result transformer is ignored.
		doInOpenTransaction((s, tx) -> {
			CriteriaQuery<User> q = s.getCriteriaBuilder().createQuery(User.class);
			Root<User> root = q.from(User.class);
			q.multiselect(root.get("id"), root.get("name"));
			List<UserDTO> ids = s.createQuery(q).unwrap(Query.class)
					.setResultTransformer(new UserResultTransformer()).getResultList();
			assertThat(ids.get(0).id, is(2));
			assertThat(ids.get(0).name, is("user"));
		});
	}

}
