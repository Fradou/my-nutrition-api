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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fradou.nutrition.mvc.entity.generic.GenericEntity;
import com.fradou.nutrition.mvc.entity.work.Intake;
import com.fradou.nutrition.mvc.entity.work.PantryItem;
import com.fradou.nutrition.mvc.utils.deserializer.EntityIdResolver;
import com.fradou.nutrition.mvc.utils.serializer.CustomUserSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * Custom user entity, include all Spring security requirement and custom
 * attributes.
 * 
 * @author AF
 *
 */
@Entity
@Table(name="user",
		indexes = {@Index(name= "ix_username", columnList="username", unique = true),
				@Index(name= "ix_email", columnList="email", unique = true)
		}
)
@Setter @Getter
@JsonSerialize(using = CustomUserSerializer.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope=CustomUser.class
)
public class CustomUser extends GenericEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, unique=true, length=50)
	@NotNull
	private String username;
	
	@Column(nullable=false)
	@JsonIgnore
	private String password;
	
	@Column(nullable=false)
	@JsonIgnore
	private boolean enabled;
	
	@Column(nullable=false, unique=true)
	@Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message="Pas un format mail ça", flags=Pattern.Flag.CASE_INSENSITIVE)
	@NotNull
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
	@JsonIgnore
	private List<Intake> intakes;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<PantryItem> pantryItems;
	
    @ManyToMany
    @JoinTable(
    		name="users_roles",
    		joinColumns=@JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="role_id")
    )
    
    @JsonIgnore
    private Set<Role> roles = new HashSet<Role>();
	
	// Methods imposed by Spring Security
	
	@Override
	@JsonIgnore
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
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

	@Override
	protected String initializeEntityPath() {
		return "/user";
	}
	
	@Override
	public boolean isUserRelated() {
		return true;
	}
	
	public String toString() {
		return "CustomerUser : [id=" + id + ", username=" + username + ", height="+height+", age=" + age;
	}
}
