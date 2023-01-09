package com.FeS.crawler.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FeS.crawler.Model.SchoolInfo;

public interface SchoolRepo extends JpaRepository <SchoolInfo, Long>{
	SchoolInfo findByArea (String area);
}
