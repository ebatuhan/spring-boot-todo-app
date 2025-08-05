package com.ebatuhan.todo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String todoName;

	@Column(nullable=false)
	private String todoContent;

	@Column(nullable=false)
	private Date deadline;

	@Column(nullable=false)
	private Date expireDate;

	private boolean isDone = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private TodoUser todoUser;

	@PrePersist
	@PreUpdate
	public void calculateExpireDate() {
		if (this.deadline != null) {
			this.expireDate = Date.valueOf(this.deadline.toLocalDate().plusDays(30));
		}

	}
}
