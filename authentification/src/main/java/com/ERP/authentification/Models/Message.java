package com.ERP.authentification.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false )
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="idsender")
    private Long idsender ;
    @Column(name ="idreceiver")
    private Long idreceiver;
    @Column(name ="id_indiv_chat")
    private Long id_indiv_chat ;
    @Column(name ="body")
    private String body ;
    @Column(name ="vu")
    private boolean vu ;
    @Column(name ="send_date")
    private Instant send_date =Instant.now()  ;
    @Column(name ="vu_date")
    private Instant vu_date ;

    public Long getidsender() {
        return idsender;
    }

    public void setidsender(Long idsender) {
        this.idsender = idsender;
    }

    public Long getidreceiver() {
        return idreceiver;
    }

    public void setidreceiver(Long idreceiver) {
        this.idreceiver = idreceiver;
    }

  

    public Long getId_indiv_chat() {
        return id_indiv_chat;
    }

    public void setId_indiv_chat(Long id_indiv_chat) {
        this.id_indiv_chat = id_indiv_chat;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isVu() {
        return vu;
    }

    public void setVu(boolean vu) {
        this.vu = vu;
    }

    public Instant getSend_date() {
        return send_date;
    }

    public void setSend_date(Instant send_date) {
        this.send_date = send_date;
    }

    public Instant getVu_date() {
        return vu_date;
    }

    public void setVu_date(Instant vu_date) {
        this.vu_date = vu_date;
    }
}
