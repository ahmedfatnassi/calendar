package com.ERP.authentification.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false )
@Entity
@Table 
public class Administrator extends Person {

	@Column(name="cin")
	private String cin ;
	@Column(name="address")
	private String address ;
	@Column(name="registration_Number")
	private String matricule ;

}
