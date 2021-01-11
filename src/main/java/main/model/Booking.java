package main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private LocalDate date_start;
    @Column
    private LocalDate date_end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private HotelRoom room;

    public Booking(HotelRoom room, LocalDate date_start, LocalDate date_end) {
        this.room = room;
        this.date_start = date_start;
        this.date_end = date_end;
    }
}
