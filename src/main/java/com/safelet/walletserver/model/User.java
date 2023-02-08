package com.safelet.walletserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surnames;

	@Column(name = "registry_date", nullable = false)
	private LocalDateTime registyDate;

	@OneToMany(mappedBy = "source")
	private Set<Transaction> received = new LinkedHashSet<>();

	@OneToMany(mappedBy = "destiny")
	private Set<Transaction> sent = new LinkedHashSet<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "contact",
		joinColumns = @JoinColumn(name = "self"),
		inverseJoinColumns = @JoinColumn(name = "other"))
	private Set<User> contacts = new LinkedHashSet<>();

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private Set<Wallet> wallets = new LinkedHashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		User user = (User) o;
		return id != null && Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
