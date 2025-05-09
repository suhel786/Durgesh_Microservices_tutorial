package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Rating;
import com.example.demo.repositories.RatingRepository;
import com.example.demo.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {

		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAll() {

		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {

		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByhotelId(String hotelId) {

		return ratingRepository.findByHotelId(hotelId);
	}

}
