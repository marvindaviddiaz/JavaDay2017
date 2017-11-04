package com.is4tech.book.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BookingRecord {

	public enum Status { BOOKING_CONFIRMED, BOOKING_CANCELED }

	private Long id;
    private String dpi;
    private String name;
    private String email;
	private String event;
	private String category;
    private Integer quantity;
    private Date bookingDate;
    private Double price;
    private Status status;

	public BookingRecord() {
	}

	public BookingRecord(String dpi, String name, String email, String event, String category, Integer quantity, Date bookingDate, Double price, Status status) {
		this.dpi = dpi;
		this.name = name;
		this.email = email;
		this.event = event;
		this.category = category;
		this.quantity = quantity;
		this.bookingDate = bookingDate;
		this.price = price;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
