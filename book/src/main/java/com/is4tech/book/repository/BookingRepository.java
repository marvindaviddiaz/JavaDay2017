package com.is4tech.book.repository;


import com.is4tech.book.domain.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {

}
