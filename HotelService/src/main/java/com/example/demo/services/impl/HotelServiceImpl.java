package com.example.demo.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Hotel;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.HotelRepository;
import com.example.demo.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String uid = UUID.randomUUID().toString();
		hotel.setId(uid);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
	}

	@Override
	public void delete(String id) {
		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));

		hotelRepository.delete(hotel);
	}

	@Override
	public Hotel update(String id, Hotel updatedHotel) {
		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
		hotel.setName(updatedHotel.getName());
		hotel.setLocation(updatedHotel.getLocation());
		hotel.setAbout(updatedHotel.getAbout());
		return hotelRepository.save(hotel);
	}

}
