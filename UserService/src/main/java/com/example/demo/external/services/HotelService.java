package com.example.demo.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotels(@PathVariable("hotelId") String hotelId); // if hotelId in uri is differnt than hotel Id in
																		// attributes like String hotelId then wev used
																		// @PathVariable("hotelId")
}
