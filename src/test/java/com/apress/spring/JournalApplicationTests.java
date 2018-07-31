package com.apress.spring;

import static io.restassured.RestAssured.given;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JournalApplicationTests {

	@Autowired
	JournalRepository repo;

	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setup() throws Exception {
    repo.save(new Journal("Get to know Spring Boot","Today I will learn Spring Boot","01/01/2016"));
    repo.save(new Journal("Simple Spring Boot Project","I will do my first Spring Boot Project","01/02/2016"));
    repo.save(new Journal("Spring Boot Reading","Read more about Spring Boot","02/01/2016"));
    repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud Foundry","03/01/2016"));
	}

	@After
	public void cleaup() throws Exception {
		repo.deleteAll();
	}	

	@Test
	public void testFindAll() {
		// given().
		// expect().
		// statusCode(200).
		// when().
		// get("/journals");
	}

	@Test
	public void testFindById() {
		// given().
		// expect().
		// statusCode(200).
		// when().
		// get("/journals/1");
	}


}
