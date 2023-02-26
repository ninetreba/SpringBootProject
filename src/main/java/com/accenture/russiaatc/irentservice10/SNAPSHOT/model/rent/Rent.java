package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.rent;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport.Transport;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "RENT", schema = "PUBLIC")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE_RENT")
    @Column(name = "ID_RENT")
    private Long id;

    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "USER_REF")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TRANSPORT_REF")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "START_PARKING")
    private Parking startParking;

    @ManyToOne
    @JoinColumn(name = "END_PARKING")
    private Parking endParking;


    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private StatusRent statusRent;


    @Column(name = "START_RENT")
    private LocalDateTime startRent;

    @Column(name = "END_RENT")
    private LocalDateTime endRent;


    public Rent() {}


}
