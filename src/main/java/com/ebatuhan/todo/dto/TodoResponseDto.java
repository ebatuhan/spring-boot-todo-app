package com.ebatuhan.todo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TodoResponseDto {

	private Long id;

	private String todoName;

	private String todoContent;

	private Date deadline;

	private boolean isDone;

	private Long todoUserId;
}
