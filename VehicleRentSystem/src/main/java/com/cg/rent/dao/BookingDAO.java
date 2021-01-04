package com.cg.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.rent.model.Booking;
/**
 * BookingDAO interface
 * This class handles all the booking related database operations.
 * @author 15BW089AX
 *
 */
public interface BookingDAO extends JpaRepository<Booking, Integer>{
	
}
