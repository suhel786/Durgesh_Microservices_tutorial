package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Hotel;
import com.example.demo.entities.Rating;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.external.services.HotelService;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.netflix.discovery.converters.Auto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// generate unique userid
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	/*
	 * @Override public List<User> getAllusers() { return userRepository.findAll();
	 * }
	 */
	@Override
	public List<User> getAllusers() {

		List<User> users = userRepository.findAll();

		for (User user : users) {
			// Fetch ratings for each user
			/*
			 * Rating[] ratings =
			 * restTemplate.getForObject("http://localhost:9083/ratings/users/" +
			 * user.getUserId(), Rating[].class);
			 */

			// replace localhost:9083 with service instace names in Eureka
			Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
					Rating[].class);

			// Optionally, fetch hotel info for each rating
			for (Rating rating : ratings) {
				/*
				 * Hotel hotel = restTemplate.getForObject("http://localhost:9082/hotels/" +
				 * rating.getHotelId(), Hotel.class);
				 */
				// replace localhost:9082 with service instance names in Eureka

				// 1.we used RestTemplate call another service
				/*
				 * Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" +
				 * rating.getHotelId(), Hotel.class);
				 */
				// 2. we used Feign Client instead of RestTemplate
				Hotel hotel = hotelService.getHotels(rating.getHotelId());
				rating.setHotel(hotel);
			}

			user.setRatings(Arrays.asList(ratings));
		}

		return users;
	}

	/*
	 * @Override public User getUser(String userId) {
	 * 
	 * User user = userRepository.findById(userId).orElseThrow( () -> new
	 * ResourceNotFoundException("User with given id is not found on server !!" +
	 * userId));
	 * 
	 * // Fetching rating of the above user from RATING SERVICE //
	 * http://localhost:9083/ratings/users/8a743d59-5cbb-4bb0-b775-bff86b2e4f56
	 * ArrayList<Rating> ratingsOfUser = restTemplate
	 * .getForObject("http://localhost:9083/ratings/users/" + user.getUserId(),
	 * ArrayList.class); logger.info("{} ", ratingsOfUser);
	 * user.setRatings(ratingsOfUser); return user; }
	 */
	@Override
	public User getUser(String userId) {

		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !! " + userId));

		// Step 1: Fetch ratings of the user
		/*
		 * Rating[] ratingsArray =
		 * restTemplate.getForObject("http://localhost:9083/ratings/users/" +
		 * user.getUserId(), Rating[].class);
		 */
		Rating[] ratingsArray = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);
		List<Rating> ratingsList = new ArrayList<>();

		// Step 2: For each rating, fetch hotel info and set it
		if (ratingsArray != null) {
			for (Rating rating : ratingsArray) {
				/*
				 * Hotel hotel = restTemplate.getForObject("http://localhost:9082/hotels/" +
				 * rating.getHotelId(), Hotel.class);
				 */
				Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
						Hotel.class);

				rating.setHotel(hotel);
				ratingsList.add(rating);
			}
		}

		user.setRatings(ratingsList);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));
		userRepository.delete(user);
	}

	@Override
	public User updateuser(String userId, User updatedUser) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		existingUser.setName(updatedUser.getName());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setAbout(updatedUser.getAbout());
		return userRepository.save(existingUser);
	}

}
