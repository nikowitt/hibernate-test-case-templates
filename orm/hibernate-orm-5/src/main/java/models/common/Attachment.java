package models.common;

import java.sql.Blob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Attachment extends Base {

	private Base parent;

	@Access(AccessType.FIELD)
	@AttributeOverrides(@AttributeOverride(name = "blob", column = @Column(name = "filedata", length = 500 * 1024 * 1024)))
	@Basic(fetch = FetchType.LAZY)
	private DataHolder data = new DataHolder();

	public DataHolder getData() {
		if (data == null) {
			data = new DataHolder();
		}
		return data;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Base getParent() {
		return parent;
	}

	public void setParent(Base parent) {
		this.parent = parent;
	}

	@Embeddable
	public static class DataHolder {

		private Blob blob;

		@Lob
		@Basic(fetch = FetchType.LAZY)
		public Blob getBlob() {
			return blob;
		}

		public void setBlob(Blob blob) {
			this.blob = blob;
		}
	}
}