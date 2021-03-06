package com.mariopmartins.reminder.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mariopmartins.reminder.domain.Reminder;
import com.mariopmartins.reminder.domain.ReminderStatus;
import com.mariopmartins.reminder.service.ReminderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/reminders")
public class ReminderRestController {

	@Autowired
	private ReminderService reminderService;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find a Reminder by id")
	public ResponseEntity<Reminder> get(
			@PathVariable Long id) {
		return ResponseEntity.ok(reminderService.findById(id));
	}
	
	@GetMapping
	@ApiOperation(value = "List Reminders by dueDate and/or status", notes = "Only present parameters will be used to filter the reminders.")
	public ResponseEntity<Iterable<Reminder>> list(
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
			@RequestParam(required = false) ReminderStatus status) {
		return ResponseEntity.ok(reminderService.findAll(dueDate, status));
	}

	@PostMapping
	@ApiOperation(value = "Add a Reminder")
	public ResponseEntity<Reminder> add(
			@RequestBody @Valid Reminder reminder) {
		return ResponseEntity.ok(reminderService.save(reminder));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update a Reminder")
	public ResponseEntity<Reminder> update(
			@PathVariable Long id, 
			@RequestBody @Valid Reminder reminder) {
		return ResponseEntity.ok(reminderService.update(id, reminder));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a Reminder")
	public void delete(
			@PathVariable Long id) {
		reminderService.deleteById(id);
	}

}