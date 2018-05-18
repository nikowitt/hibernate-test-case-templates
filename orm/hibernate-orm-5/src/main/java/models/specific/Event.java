package models.specific;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import models.common.Base;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event extends Base {

}
