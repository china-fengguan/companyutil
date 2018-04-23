package com.adun.company.name;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    public List<Company> findAll() {

        List<Company> companies = (List<Company>) repository.findAll();
        
        return companies;
    }
    @Override
    public List<Company> findByNameLike(String name) {
    	
    	List<Company> companies = (List<Company>) repository.findByNameLike(name);
    	
    	return companies;
    }

}

