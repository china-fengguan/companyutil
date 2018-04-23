package com.adun.company.name;

import java.util.List;

public interface ICompanyService {
	public List<Company> findAll();
	public List<Company> findByNameLike(String name);
}
