package com.accenture.russiaatc.irentservice10.SNAPSHOT.telegram;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TG", schema = "PUBLIC")
public class UserTelegram {
    @Id
    @Column(name = "CHAT_ID_TG")
    private Long id;

    private Double longitude;
    private Double latitude;

}
