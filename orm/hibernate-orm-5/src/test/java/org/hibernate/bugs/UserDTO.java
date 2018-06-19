package org.hibernate.bugs;

public class UserDTO {
	public final int id;
	public final String name;

	public UserDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
