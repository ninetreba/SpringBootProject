package com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.Status;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "USER", schema = "PUBLIC")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Status status;


}
