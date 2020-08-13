package com.ERP.authentification.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
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
    private Long  boardid ;
    @Column(name ="send_date")
    private Instant send_date =Instant.now()  ;

    @Column(name ="isArchived")
    private Boolean isArchived  ;
    @Column(name ="isExecuted")
    private Boolean isExecuted  ;

    @Column(name ="activitiProcessId")
    private String activitiProcessId  ;

    public String getActivitiProcessId() {
        return activitiProcessId;
    }

    public void setActivitiProcessId(String activitiProcessId) {
        this.activitiProcessId = activitiProcessId;
    }

    public Long getBoardid() {
        return boardid;
    }

    public Boolean getIsExecuted() {
        return isExecuted;
    }

    public void setIsExecuted(Boolean executed) {
        isExecuted = executed;
    }

    public void setBoardid(Long boardid) {
        this.boardid = boardid;
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getSend_date() {
        return send_date;
    }

    public void setSend_date(Instant send_date) {
        this.send_date = send_date;
    }

    public Boolean getExecuted() {
        return isExecuted;
    }

    public void setExecuted(Boolean executed) {
        isExecuted = executed;
    }
}
