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
public class MessageContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="name")
    private String name ;
    @Column(name ="sender_Type")
    private String  sender_Type ;
    @Column(name ="idsender")
    private Long idsender ;
    @Column(name ="idreceiver")
    private Long idreceiver;
    @Column(name ="last_message_Date")
    private Instant last_message_Date ;
    @Column(name ="last_message")
    private String last_message ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSender_Type() {
        return sender_Type;
    }

    public void setSender_Type(String sender_Type) {
        this.sender_Type = sender_Type;
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

    public void setIdreceiver(Long idreceiver) {
        this.idreceiver = idreceiver;
    }

    public Instant getLast_message_Date() {
        return last_message_Date;
    }

    public void setLast_message_Date(Instant last_message_Date) {
        this.last_message_Date = last_message_Date;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }
}
