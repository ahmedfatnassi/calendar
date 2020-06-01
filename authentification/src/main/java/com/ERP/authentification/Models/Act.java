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
    @Column(name ="send_date")
    private Instant data = Instant.now() ;
    /*@Column(name ="quotation")
    private Double  quotation ;
    @Column(name ="honorary")
    private Double honorary ;*/
    @Column(name ="conventional_code")
    private String  conventional_code ;
    @Column(name ="TAX__ID")
    private String  TAX_ID ;
    ///




}
