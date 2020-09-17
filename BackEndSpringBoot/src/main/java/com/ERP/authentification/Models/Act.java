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
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name ="type")
    private RequestFieldType type ;
    @Column(name ="requestId")
    private Long   requestId;
    @Column(name ="actDate")
    private Instant actDate ;
    @Column(name ="quotation")
    private String  quotation ;
    @Column(name ="honorary")
    private String honorary ;
    @Column(name ="conventional_code")
    private String  conventional_code ;
    @Column(name ="TAX__ID")
    private String  TAX_ID ;
    @Column(name ="archived")
    private Boolean archived  = Boolean.TRUE ;
    ///
    @Column(name ="result_description")
    private String result_description ;

    @Column(name ="result")
    private String result ;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult_description() {
        return result_description;
    }

    public void setResult_description(String result_description) {
        this.result_description = result_description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestFieldType getType() {
        return type;
    }

    public void setType(RequestFieldType type) {
        this.type = type;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Instant getActDate() {
        return actDate;
    }

    public void setActDate(Instant actDate) {
        this.actDate = actDate;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getHonorary() {
        return honorary;
    }

    public void setHonorary(String honorary) {
        this.honorary = honorary;
    }

    public String getConventional_code() {
        return conventional_code;
    }

    public void setConventional_code(String conventional_code) {
        this.conventional_code = conventional_code;
    }

    public String getTAX_ID() {
        return TAX_ID;
    }

    public void setTAX_ID(String TAX_ID) {
        this.TAX_ID = TAX_ID;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
