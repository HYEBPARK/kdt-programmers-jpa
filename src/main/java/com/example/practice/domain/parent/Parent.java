package com.example.practice.domain.parent;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Parent {

	@EmbeddedId
	public ParentId id;

	public ParentId getId() {
		return id;
	}

	public void setId(ParentId id) {
		this.id = id;
	}
}
