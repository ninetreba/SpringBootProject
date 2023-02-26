package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.transport;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.parking.Parking;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSPORT", schema = "PUBLIC")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY
    @Column(name = "ID_TRANSPORT")
    protected Long id;

    protected String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSPORT_TYPE")
    protected Type type;


    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSPORT_CONDITION")
    protected Condition condition;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name ="TRANSPORT_STATUS")
    private TransportStatus transportStatus;

    //  Сначала указывается широта, затем долгота.
    private Double longitude;

    private Double latitude;


    @ManyToOne
    @JoinColumn(name = "CURRENT_PARKING")
    protected Parking currentParking;


}