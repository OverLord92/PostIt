package com.postit.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.postit.constraints.ValidDateFormat;

@Component
@Entity
public class Postit {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	@NotBlank
	@Size(min=10, max=30)
	private String subject;
	
	@NotBlank
	@Size(min=10, max=250)
	private String text;
	
	@Transient
	@NotBlank
	@ValidDateFormat // custom hibernate constraint
	private String dateString;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean active;
	
	public Postit() {
	}
	
	public Postit(String subject, String text, String dateString, Date date, boolean active) {
		this.subject = subject;
		this.text = text;
		
		this.dateString = dateString;
		this.date = date;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((dateString == null) ? 0 : dateString.hashCode());
		result = prime * result + id;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postit other = (Postit) obj;
	
		if (id != other.id)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Postit [id=" + id + ", subject=" + subject + ", text=" + text + ", dateString=" + dateString + ", date="
				+ date + ", active=" + active + "]";
	}

	
	
}
