package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Hotel;

public interface HotelService {

	// create
	Hotel create(Hotel hotel);

	// get all
	List<Hotel> getAll();

	// get single
	Hotel get(String id);

	// delete
	void delete(String id);

	// update
	Hotel update(String id, Hotel updatedHotel);
}
