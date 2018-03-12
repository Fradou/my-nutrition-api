package com.fradou.nutrition.mvc.entity.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.entity.work.PantryItem;

@Entity
@Table(name="user",
		indexes = {@Index(name= "ix_username", columnList="username", unique = true),
				@Index(name= "ix_email", columnList="email", unique = true)
		})
public class CustomUser extends GenericEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, unique=true, length=50)
	private String username;
	
	@Column(nullable=false)
	@JsonIgnore
	private String password;
	
	@Column(nullable=false)
	@JsonIgnore
	private boolean enabled;
	
	@Column(nullable=false, unique=true)
	@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message="Pas un format mail ça", flags=Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@Min(value=1, message="C'est mieux d'être né, non ?")
	@Max(value=150, message="Tu as battu tous les record de longévité, bravo !")
	private Integer age;
	
	@Min(value=2, message="Are you just born ?")
	private Double weight;
	
	@Min(value=0)
	@Max(value=250)
	private Integer height;
	
	private Integer bmr;
	
	private Integer tdee;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Intake> intakes;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<PantryItem> pantryItems;
	
    @ManyToMany
    @JoinTable(
    		name="users_roles",
    		joinColumns=@JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

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
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}
	
	public String toString() {
		return "CustomerUser : [id=" + id + ", username=" + username + ", height="+height+", age=" + age;
	}

	@Override
	protected String initializeEntityPath() {
		return "/user";
	}

	public List<Intake> getIntakes() {
		return intakes;
	}

	public void setIntakes(List<Intake> intakes) {
		this.intakes = intakes;
	}

	public List<PantryItem> getPantryItems() {
		return pantryItems;
	}

	public void setPantryItems(List<PantryItem> pantryItems) {
		this.pantryItems = pantryItems;
	}
}
