package com.ERP.authentification.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false )
@Entity
@Table
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="idTeam")
    private Long idTeam ;
    @Column(name ="iduser")
    private Long idUser ;
    @Column(name ="date")
    private Instant  date = Instant.now() ;
}
