package models.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class DatabaseEntity implements Cloneable {
	private String id;

	@Id
	@Column(length = 32, updatable = false, nullable = false)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public DatabaseEntity clone() {
		try {
			DatabaseEntity clone = (DatabaseEntity) super.clone();
			clone.setId(null);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
