package com.adun.company.name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.adun.company.CompanyApplication;
import com.adun.company.name.*;

@RestController
public class CompanyController {

    @Autowired
    ICompanyService companyService;
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @RequestMapping(value = "/api/queryCompanies", method = RequestMethod.POST)
    public Object queryCompanies(@RequestBody ReqParams reqParam) {
		int itemPerPage = reqParam.getItemsPerPage();
		int currentPage = reqParam.getCurrentPage();
		String companyName = reqParam.getCompanyName();
		logger.info("CompanyController start with " + companyName);
		if(companyName.isEmpty()) companyName = "爱";
		
    	List<Company> companies = (List<Company>) companyService.findByNameLike("%" + companyName + "%");
		PagedListHolder<Company> pagedList = new PagedListHolder<Company>(companies);
		pagedList.setPageSize(itemPerPage);
		pagedList.setPage(currentPage-1);
		List<Company> pageResult = pagedList.getPageList();
		
		Map<String,Object> companyMap = new HashMap<String, Object>();
		companyMap.put("totalCompanyName",companies.size());
		companyMap.put("pageResult", pageResult);
		logger.info("CompanyController end");
    	return companyMap;
    }
}

