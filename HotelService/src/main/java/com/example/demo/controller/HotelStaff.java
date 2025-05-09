package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class HotelStaff {

	@GetMapping
	public ResponseEntity<List<String>> getAllHotelStaffs() {
		String[] staffs = { "Akshay", "Vishal", "Furqan", "khadir" };
		List<String> hotelStaff = Arrays.asList(staffs);
		return ResponseEntity.status(HttpStatus.OK).body(hotelStaff);

	}
}
