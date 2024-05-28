package org.events;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert extends Event {

    // ATTRIBUTES
    private LocalTime time;
    private BigDecimal price;

    // CONSTRUCTORS
    public Concert(String title, LocalDate date, int totalSeats, LocalTime time, BigDecimal price) {
        super(title, date, totalSeats);
        this.time = validateTime(time);
        this.price = validatePrice(price);
    }

    // METHODS
    // Validations
    public LocalTime validateTime(LocalTime time) throws IllegalArgumentException {
        if(time.isBefore(LocalTime.now())){
            throw new IllegalArgumentException("Time must be in the future. You entered: " + time);
        }
        return time;
    }

    public BigDecimal validatePrice(BigDecimal price) throws IllegalArgumentException {
        if(price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price cannot be negative. You entered: " + price);
        }
        return price;
    }

    // Getters & Setters
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = validateTime(time);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = validatePrice(price);
    }

    public String getFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(super.getDate(), this.time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withLocale(Locale.ITALY);
        return dateTime.format(formatter);
    }

    public String getFormattedPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        return formatter.format(this.price);
    }

    // Other methods

    @Override
    public String toString() {
        return getFormattedDateTime() +
                " - " + getTitle() +
                " - " + getFormattedPrice();
    }
}
