package com.fradou.nutrition.mvc.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user")
public class CustomUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(nullable=false, unique=true, length=50)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private boolean accountNonExpired;
	
	@Column(nullable=false)
	private boolean accountNonLocked;
	
	@Column(nullable=false)
	private boolean credentialsNonExpired;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@Column
	private Integer age;
	
	@Column
	private Double weight;
	
	@Column
	private Integer height;
	
	@Column
	private Integer bmr;
	
	@Column
	private Integer tdee;
	
    @ManyToMany
    @JoinTable(
    		name="users_roles",
    		joinColumns=@JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<Role> roles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	// Methods imposed by Spring Security
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getBmr() {
		return bmr;
	}

	public void setBmr(Integer bmr) {
		this.bmr = bmr;
	}

	public Integer getTdee() {
		return tdee;
	}

	public void setTdee(Integer tdee) {
		this.tdee = tdee;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String toString() {
		return "CustomerUser : [id=" + id + ", username=" + username + ", height="+height+", age=" + age;
	}
}
