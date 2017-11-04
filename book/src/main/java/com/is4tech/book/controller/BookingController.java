package com.is4tech.book.controller;

import com.is4tech.book.component.BookingComponent;
import com.is4tech.book.domain.BookingException;
import com.is4tech.book.domain.BookingRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

	private final BookingComponent bookingComponent;
	
	@Autowired
    BookingController(BookingComponent bookingComponent){
		this.bookingComponent = bookingComponent;
	}

	@PostMapping
	Long book(@RequestBody BookingRequestDTO dto) throws BookingException {
		return bookingComponent.book(dto);
	}
	
}
