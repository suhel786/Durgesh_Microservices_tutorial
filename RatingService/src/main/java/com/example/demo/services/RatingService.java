package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Rating;

@Service
public interface RatingService {

	// create
	Rating create(Rating rating);

	// get all ratings
	List<Rating> getAll();

	// get all by UserId
	List<Rating> getRatingsByUserId(String userId);

	// get all by hotel
	List<Rating> getRatingsByhotelId(String hotelId);
}
