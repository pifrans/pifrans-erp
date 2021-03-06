package com.pifrans.global.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pifrans.global.enums.Profile;
import com.pifrans.modules.ecommerce.models.Order;
import com.pifrans.modules.place.models.Address;

/* Anotação para mostrar para o JPA que esta é uma superclasse e não precisa criar tabela a partir dela e sim das classes filhas */
@Entity
@Table(name = "glb_user")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickname;

	@NotEmpty(message = "Campo obrigatório!")
	@Email(message = "E-mail inválido!")
	@Column(unique = true)
	private String email;

	@JsonIgnore
	@Column(nullable = false, length = 255)
	private String password;

	@Column(name = "current_access")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date currentAccess;

	@Column(name = "last_access")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date lastAccess;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "glb_user_profile")
	private Set<Integer> profiles = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "glb_user_phone")
	private Set<String> phones = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "glb_user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private List<Address> addresses;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();

	public User() {
		addProfile(Profile.USER);
	}

	public User(Long id, String nickname, String email, String password, Date currentAccess, Date lastAccess) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.currentAccess = currentAccess;
		this.lastAccess = lastAccess;
		addProfile(Profile.USER);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCurrentAccess() {
		return currentAccess;
	}

	public void setCurrentAccess(Date currentAccess) {
		this.currentAccess = currentAccess;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		profiles.add(profile.getId());
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public void addPhone(String phone) {
		phones.add(phone);
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
