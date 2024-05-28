package org.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event {

    // ATTRIBUTES
    protected String title;
    protected LocalDate date;
    protected int totalSeats;
    protected int bookedSeats;

    // CONSTRUCTORS
    public Event(String title, LocalDate date, int totalSeats) {
        this.title = validateTitle(title);
        this.date = validateDate(date);
        this.totalSeats = validateTotalSeats(totalSeats);
        this.bookedSeats = 0;
    }

    // METHODS

    // Validations
    public String validateTitle(String title) throws IllegalArgumentException {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return title;
    }

    public LocalDate validateDate(LocalDate date) throws IllegalArgumentException {
        if(date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Date must be in the future. You entered: " + date);
        }
        return date;
    }

    public int validateTotalSeats(int seats) throws IllegalArgumentException {
        if(seats <= 0){
            throw new IllegalArgumentException("Number of seats must be positive. You entered: " + seats);
        }
        return seats;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = validateTitle(title);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = validateDate(date);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    // Other methods
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd", Locale.ENGLISH);

        return date.format(formatter) + " - " + title;
    }

    public void bookSeats(int seats) throws IllegalArgumentException {
        if (this.date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("You cannot book seats for a past event");
        }

        if (seats <=0){
            throw new IllegalArgumentException("Number of seats must be positive. You entered: " + seats);
        }

        if (bookedSeats + seats > totalSeats){
            throw new IllegalArgumentException("Not enough seats. Available seats: " + getAvailableSeats());
        }
        bookedSeats += seats;
    }

    public void cancelBooking(int seats) throws IllegalArgumentException {
        if (this.date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("You cannot cancel a booking for a past event");
        }

        if (seats <=0){
            throw new IllegalArgumentException("Number of seats must be positive. You entered: " + seats);
        }

        if (bookedSeats - seats < 0){
            throw new IllegalArgumentException("Not enough booked seats");
        }
        bookedSeats -= seats;
    }
}
