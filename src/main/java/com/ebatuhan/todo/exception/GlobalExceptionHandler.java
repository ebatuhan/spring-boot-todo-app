package com.ebatuhan.todo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	static class ErrorResponse {
		private LocalDateTime timestamp;
		private String message;
		private String path;

		public ErrorResponse(String message, WebRequest request) {
			this.timestamp = LocalDateTime.now();
			this.message = message;
			this.path = request.getDescription(false).replace("uri= ", "");
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public String getMessage() {
			return message;
		}

		public String getPath() {
			return path;
		}

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleResourceNotSaved(ResourceNotSavedException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_MODIFIED);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUsernameTakenException(UsernameTakenException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleBadPasswordPatternExcpetion(BadPasswordPatternException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
