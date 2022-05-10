package com.example.practice.domain.order;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "id")
	private String uuid;

	@Column(name = "memo")
	private String memo;

	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "order_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime orderDateTime;

	//fk
	@Column(name = "member_id", insertable = false, updatable = false)
	private Long memberId;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private Member member;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			member.getOrders().remove(this);
		}

		this.member = member;
		member.getOrders().add(this);
	}
}
