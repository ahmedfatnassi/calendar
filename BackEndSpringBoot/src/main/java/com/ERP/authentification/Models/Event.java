package com.ERP.authentification.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@EntityListeners(AuditingEntityListener.class)
public class Event  {
	
@Id 
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id ;	

@Column(name ="idSender")
private Long idSender ; 

@CreatedDate
@Column(name="create_date" )
private Instant create_date = Instant.now() ;

@LastModifiedDate 
@Column(name="last_modified_date" )
private Instant last_modified_date = Instant.now() ;

@Column(name ="idReceiver")
private Long idReceiver ; 

@Column(name ="title")
private String title ; 

@Column(name ="Status")
private String Status ; 

@Column(name ="startEvent")
private LocalDateTime  startEvent ; 

@Column(name ="endEvent")
private LocalDateTime  endEvent ; 

@Column(name ="color")
private String color ;

@Column(name ="receiverId")
private Long receiverId ;
@Column(name ="receiver_type")
private String receiver_type ;

@Column(name ="resourceId")
private String resourceId ; 


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}

	public Long getreceiverId() {
		return receiverId;
	}

	public void setreceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getreceiver_type() {
		return receiver_type;
	}

	public void setreceiver_type(String receiver_type) {
		this.receiver_type = receiver_type;
	}

	public LocalDateTime getStartEvent() {
	return startEvent;
}


public void setStartEvent(LocalDateTime startEvent) {
	this.startEvent = startEvent;
}


public LocalDateTime getEndEvent() {
	return endEvent;
}


public void setEndEvent(LocalDateTime endEvent) {
	this.endEvent = endEvent;
}


public String getColor() {
	return color;
}


public void setColor(String color) {
	this.color = color;
}


public String getResourceId() {
	return resourceId;
}


public void setResourceId(String resourceId) {
	this.resourceId = resourceId;
}


@Column(name ="archived" )
private boolean  archived = false  ;


public Long getIdSender() {
	return idSender;
}


public void setIdSender(Long idSender) {
	this.idSender = idSender;
}



public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Instant getCreate_date() {
	return create_date;
}


public void setCreate_date(Instant create_date) {
	this.create_date = create_date;
}


public boolean isArchived() {
	return archived;
}


public void setArchived(boolean archived) {
	this.archived = archived;
}




public Instant getLast_modified_date() {
	return last_modified_date;
}


public void setLast_modified_date(Instant last_modified_date) {
	this.last_modified_date = last_modified_date;
}


public Long getIdReceiver() {
	return idReceiver;
}


public void setIdReceiver(Long idReceiver) {
	this.idReceiver = idReceiver;
}



public String getStatus() {
	return Status;
}


public void setStatus(String status) {
	Status = status;
}







}
