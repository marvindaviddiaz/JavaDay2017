package com.is4tech.book.component;

import com.is4tech.book.repository.BookingRepository;
import com.is4tech.book.domain.BookingException;
import com.is4tech.book.domain.BookingRecord;
import com.is4tech.book.domain.BookingRequestDTO;
import com.is4tech.book.domain.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
@Component
public class BookingComponent {
	private static final Logger logger = LoggerFactory.getLogger(BookingComponent.class);
   
	private final BookingRepository bookingRepository;
	private final Sender sender;
    private final RestTemplate restTemplate;

    @Value("${app.booking.maxPerUser}")
    private Integer maxPerUser;

	@Autowired
	public BookingComponent(BookingRepository bookingRepository, Sender sender, RestTemplate restTemplate) {
		this.bookingRepository = bookingRepository;
        this.sender = sender;
        this.restTemplate = restTemplate;
    }

    public BookingRecord getBooking(Long id) {
        return bookingRepository.findOne(id);
    }

	public Long book(BookingRequestDTO req) throws BookingException {
		logger.info("calling inventory to get price");
        EventDTO[] result = restTemplate.getForObject("http://inventory/inventory?event={event}",
                EventDTO[].class, new Object[]{ req.getEvent() });

        EventDTO e = Arrays.asList(result).stream()
                .filter(f -> f.getCategory().equals(req.getCategory()))
                .findFirst()
                .orElseThrow(() -> new BookingException("Invalid information"));

        // validations
        if(e.getAvailable().compareTo(req.getQuantity()) == -1) {
            throw new BookingException("Remaining tickets: " + e.getAvailable());
        } else if (req.getQuantity().compareTo(maxPerUser) == 1) {
            throw new BookingException("You have exceeded the max of tickets: " + maxPerUser);
        }

        // create
        BookingRecord record = new BookingRecord(req.getDpi(), req.getName(), req.getEmail(), e.getEvent(),
                e.getCategory(), req.getQuantity(), new Date(), e.getPrice(), BookingRecord.Status.BOOKING_CONFIRMED);
        this.bookingRepository.save(record);

        // notification to Search
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("EVENT", record.getEvent());
        bookingDetails.put("CATEGORY", record.getCategory());
        bookingDetails.put("QUANTITY", record.getQuantity());
        sender.send(bookingDetails);

		return record.getId();
	}

}

