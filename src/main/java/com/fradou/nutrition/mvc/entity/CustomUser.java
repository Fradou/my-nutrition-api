package com.fradou.nutrition.mvc.entity;

import java.util.Collection;
import java.util.HashSet;
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
import javax.persistence.Index;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user",
		indexes = {@Index(name= "ix_username", columnList="username", unique = true),
				@Index(name= "ix_email", columnList="email", unique = true)
		})
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
	private boolean enabled;
	
	@Column(nullable=false, unique=true)
	@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message="Pas un format mail ça", flags=Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	
	@Column
	@Min(value=1, message="C'est mieux d'être né, non ?")
	@Max(value=150, message="Tu as battu tous les record de longévité, bravo !")
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
    private Set<Role> roles = new HashSet<Role>();
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}
	
	public String toString() {
		return "CustomerUser : [id=" + id + ", username=" + username + ", height="+height+", age=" + age;
	}
}
