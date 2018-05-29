package models.common;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import business.common.BODatabaseEntity;

@MappedSuperclass
public abstract class DatabaseEntity<BO extends BODatabaseEntity<?>> {
	private int id;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Transient
	@Basic(fetch = FetchType.LAZY)
	public BO getBusinessObject() {
		return null;

	}

	@Transient
	@Basic(fetch = FetchType.LAZY)
	public final <T extends BO> T getBusinessObject(Class<T> clazz) {
		return null;
	}
}
