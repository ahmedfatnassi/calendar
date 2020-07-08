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
public class IndividualChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="idsender")
    private Long id_Sender ;
    @Column(name ="username_receiver")
    private String username_receiver ;
    @Column(name ="last_sent_msg_date")
    private Instant last_sent_msg_date ;
    @Column(name ="idreceiver")
    private Long idreceiver ;

    public IndividualChatHistory(Long id_Sender ,String username_receiver ,Instant last_sent_msg_date, Long idreceiver) {
    this.id_Sender = id_Sender ;
    this.username_receiver = username_receiver ;
    this.last_sent_msg_date = last_sent_msg_date ;
    this.idreceiver = idreceiver;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(Long idreceiver) {
        this.idreceiver = idreceiver;
    }

    public Long getId_Sender() {
        return id_Sender;
    }

    public void setId_Sender(Long id_Sender) {
        this.id_Sender = id_Sender;
    }

    public String getUsername_receiver() {
        return username_receiver;
    }

    public void setUsername_receiver(String username_receiver) {
        this.username_receiver = username_receiver;
    }

    public Instant getLast_sent_msg_date() {
        return last_sent_msg_date;
    }

    public void setLast_sent_msg_date(Instant last_sent_msg_date) {
        this.last_sent_msg_date = last_sent_msg_date;
    }

    public Long getidreceiver() {
        return idreceiver;
    }

    public void setidreceiver(Long idreceiver) {
        this.idreceiver = idreceiver;
    }
}
