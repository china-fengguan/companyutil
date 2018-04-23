package com.adun.company.name;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adun.company.name.*;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
	List<Company> findByNameLike(String name);
	

}

