package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Rating;
import com.example.demo.services.RatingService;
import com.example.demo.services.impl.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingSerive;

	// create rating
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingSerive.create(rating));

	}

	// get all
	@GetMapping
	public ResponseEntity<List<Rating>> getAll() {
		return ResponseEntity.ok(ratingSerive.getAll());
	}

	// get all by userId
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getAllByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(ratingSerive.getRatingsByUserId(userId));
	}

	// get all by hotelId
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getAllByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(ratingSerive.getRatingsByhotelId(hotelId));
	}

}
