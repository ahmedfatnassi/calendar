package com.ERP.authentification.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false )
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name ="insuredID")
    private Long insuredID ;
    @Column(name ="patientSocialStatus")
    private String  patientSocialStatus ;
    @Column(name ="patientFirstname")
    private String  patientFirstname ;
    @Column(name ="patientLastname")
    private String  patientLastname ;
    @Column(name ="dateOfBirth")
    private Date dateOfBirth  ;

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public Long getInsuredID() {
        return insuredID;
    }

    public void setInsuredID(Long insuredID) {
        this.insuredID = insuredID;
    }

    public String getPatientSocialStatus() {
        return patientSocialStatus;
    }

    public void setPatientSocialStatus(String patientSocialStatus) {
        this.patientSocialStatus = patientSocialStatus;
    }

    public String getPatientFirstname() {
        return patientFirstname;
    }

    public void setPatientFirstname(String patientFirstname) {
        this.patientFirstname = patientFirstname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
