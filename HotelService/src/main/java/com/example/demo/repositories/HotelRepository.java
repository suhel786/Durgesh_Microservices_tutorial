package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
