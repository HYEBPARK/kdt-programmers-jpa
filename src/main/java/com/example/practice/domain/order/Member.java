package com.example.practice.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30, unique = true)
	private String nickName;

	private int age;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "description", nullable = true)
	private String description;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();

	public void addOrder(Order order) {
		order.setMember(this);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Order> getOrders() {
		return orders;
	}

}
