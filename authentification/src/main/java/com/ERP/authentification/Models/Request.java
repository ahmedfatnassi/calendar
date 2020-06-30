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
    @Column(name ="defaultColumn")
    private Long defaultColumn ;
    @Column(name ="patientSocialStatus")
    private String  patientSocialStatus ;
    @Column(name ="patientFirstname")
    private String  patientFirstname ;
    @Column(name ="patientLastname")
    private String  patientLastname ;
    @Column(name ="boardId")
    private String  boardId ;
    @Column(name ="dateOfBirth")
    private Date dateOfBirth  ;

    @Column(name ="isArchived")
    private Boolean isArchived  ;
    @Column(name ="activitiProcessId")
    private String activitiProcessId  ;

    public String getActivitiProcessId() {
        return activitiProcessId;
    }

    public void setActivitiProcessId(String activitiProcessId) {
        this.activitiProcessId = activitiProcessId;
    }


    public Long getDefaultColumn() {
        return defaultColumn;
    }

    public void setDefaultColumn(Long defaultColumn) {
        this.defaultColumn = defaultColumn;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getBoardId() {
        return boardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

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
