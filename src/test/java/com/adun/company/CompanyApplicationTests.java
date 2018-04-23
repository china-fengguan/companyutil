package com.adun.company;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.adun.company.name.*;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CompanyApplication.class)
public class CompanyApplicationTests {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setup() {
//		companyRepository.deleteAll();
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				//.apply(springSecurity()) 
				.build();
	}

	@Test
	public void testCompany() throws Exception {
		String companyURI = "http://localhost:9000/api/showCompanies";
		String companyQueryURI = "http://localhost:9000/api/queryCompanies";
		String json = "{\"companyName\":\"a%\",\"itemsPerPage\":\"20\",\"currentPage\":\"1\"}";
		CompanyController controller = new CompanyController();
		MvcResult result = mockMvc.perform(post(companyQueryURI, "json")
								   .characterEncoding("UTF-8")
								   .contentType(MediaType.APPLICATION_JSON)
								   .content(json.getBytes()))
								   .andExpect(status().isOk())
								   .andDo(print())         
								   .andReturn();
		
//		MvcResult result = mockMvc.perform(get(companyURI))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$[0].name").exists())
//            .andExpect(jsonPath("$[0].name",is("关锋")))
//			.andDo(print())         //打印出请求和相应的内容
//	        .andReturn();
//	        .getResponse().getContentAsString();
	}
//	@Test
	public void testCompanyItem() throws Exception {
		byte[] jsonData = Files.readAllBytes(Paths.get("1.txt"));
		String jsonString = new String(jsonData);
		Long count = 0L;
		String[] sa = jsonString.trim().split("");
		System.out.println(jsonString.length());
		for (String b  : sa) {
			if (b.hashCode() != 65279 && b.trim().length() > 0) {
				for(String c : sa) {
					if (c.hashCode() != 65279  && c.trim().length()>0  && !c.equals(b) ) {
					Company name = new Company();
					count++;
					name.setName(b + c);
					//name.setId(count);
					companyRepository.save(name);
					}
				}
			}
		}
	}
}



