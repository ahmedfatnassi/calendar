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
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="idteam")
    private Long idteam ;

    @Column(name ="idboard")
    private Long idboard ;

    @Column(name ="creation_date")
    private Instant creation_date = Instant.now() ;


    public Long getIdteam() {
        return idteam;
    }

    public void setIdteam(Long idteam) {
        this.idteam = idteam;
    }

    public Long getIdboard() {
        return idboard;
    }

    public void setIdboard(Long idboard) {
        this.idboard = idboard;
    }

    public Instant getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Instant creation_date) {
        this.creation_date = creation_date;
    }
}
