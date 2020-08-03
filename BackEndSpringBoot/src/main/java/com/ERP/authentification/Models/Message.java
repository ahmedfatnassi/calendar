package com.ERP.authentification.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

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
    @Column(name ="message_container_id")
    private Long message_container_id ;
    @Column(name ="idsender")
    private Long idsender ;
    @Column(name ="idreceiver")
    private Long idreceiver;
    @Column(name ="vu_date")
    private LocalDateTime vu_date ;
    @Column(name ="body")
    private String body ;
    @Column(name ="vu")
    private boolean vu ;
    @Column(name ="send_date")
    private Instant send_date =Instant.now()  ;

    public LocalDateTime getVu_date() {
        return vu_date;
    }

    public void setVu_date(LocalDateTime vu_date) {
        this.vu_date = vu_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdsender() {
        return idsender;
    }

    public void setIdsender(Long idsender) {
        this.idsender = idsender;
    }

    public Long getIdreceiver() {
        return idreceiver;
    }

    public Long getMessage_container_id() {
        return message_container_id;
    }

    public void setMessage_container_id(Long message_container_id) {
        this.message_container_id = message_container_id;
    }



    public void setIdreceiver(Long idreceiver) {
        this.idreceiver = idreceiver;
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


}
