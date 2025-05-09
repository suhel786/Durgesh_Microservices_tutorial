package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Hotel;
import com.example.demo.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {

		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));

	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingle(@PathVariable String hotelId) {
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}

	@GetMapping
	public ResponseEntity<List<Hotel>> getAll() {
		return ResponseEntity.ok(hotelService.getAll());
	}

	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> delete(@PathVariable String hotelId) {
		hotelService.delete(hotelId);
		return ResponseEntity.ok("Hotel deleted successfully");
	}

	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> update(@PathVariable String hotelId, @RequestBody Hotel updatedHotel) {
		Hotel updatedHotelResponse = hotelService.update(hotelId, updatedHotel);
		return ResponseEntity.ok(updatedHotelResponse);

	}
}
