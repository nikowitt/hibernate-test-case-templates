package models.common;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import business.common.BOBase;

@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, include = "non-lazy")
public abstract class Base<BO extends BOBase<?>> extends DatabaseEntity<BO> {

	private Set<Attachment> attachments = new LinkedHashSet<>();

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
}
