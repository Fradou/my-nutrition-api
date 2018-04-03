package com.fradou.nutrition.mvc.entity.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fradou.nutrition.mvc.entity.generic.GenericEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Roles / Authorities for Spring Security and user control.
 * @author AF
 *
 */
@Entity
@Setter @Getter
public class Role extends GenericEntity implements GrantedAuthority {

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
	
	public void addUser(CustomUser user) {
		this.users.add(user);
		user.getRoles().remove(this);
	}
	
	public void removeUser(CustomUser user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}

	@Override
	public boolean isUserRelated() {
		return true;
	}

	@Override
	protected String initializeEntityPath() {
		return "/api/role";
	}
}
