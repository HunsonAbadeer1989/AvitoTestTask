package main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="room")
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String description;
    @Column
    private Double price;

    @Column(name = "date_of_creation")
    private LocalDate date;

    @OneToMany(mappedBy = "room")
    private List<Booking> reservations;

    public HotelRoom(String description, Double price) {
        this.description = description;
        this.price = price;
        this.date = LocalDate.now();
    }
}
