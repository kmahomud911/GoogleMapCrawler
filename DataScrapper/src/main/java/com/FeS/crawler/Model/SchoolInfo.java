package com.FeS.crawler.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SchoolInfo {
	
	@Id
	private Integer ID;
	private String schoolName;
	private String address;
	private String institutionType;
	private String website;
	private String phoneNumber;
	private String googleMapLink;
	private String rating;
	private String area;
}
