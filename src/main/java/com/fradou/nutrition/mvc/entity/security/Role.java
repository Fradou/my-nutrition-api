package com.fradou.nutrition.mvc.entity.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fradou.nutrition.mvc.entity.security.CustomUser;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(name="authority", unique=true, nullable=false)
	private String authority;
	
	@ManyToMany(mappedBy="roles")
    private Set<CustomUser> users = new HashSet<CustomUser>();
	
	@Override
	public String getAuthority() {
		return authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<CustomUser> getUsers() {
		return users;
	}

	public void setUsers(Set<CustomUser> users) {
		this.users = users;
	}
	
	public void addUser(CustomUser user) {
		this.users.add(user);
		user.getRoles().remove(this);
	}
	
	public void removeUser(CustomUser user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}
}
