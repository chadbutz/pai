package com.chadbutz.pai.rest.v1;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//@IntegrationTest("server.port:0")
public class PaiResourceTest {

	private static final String API_PATH = "/api";
	private static final String MSG_TEMPLATE = "User %s. Version %s - passed in %s";

	@Value("${local.server.port}")
	private int port;

	@Before
	public void setup() {
		RestAssured.port = this.port;
	}

	@Test
	public void shouldRetrieveNameVersion1InURL() {
		String name = "world";
		RestAssured.
			given().
				accept(ContentType.JSON).
			when().
				get(String.format("%s/v1/hello/{name}", API_PATH), name).
			then().
				statusCode(HttpStatus.SC_OK).
				contentType(ContentType.JSON).
				body("msg", Matchers.equalTo(String.format(MSG_TEMPLATE, name, 1, "URL")));
	}

	@Test
	public void shouldRetrieveNameVersion1InAcceptHeader() {
		String name = "world";
		RestAssured.
			given().
				accept("application/vnd.chadbutz-v1+json").
			when().
				get(String.format("%s/hello/{name}", API_PATH), name).
			then().
				statusCode(HttpStatus.SC_OK).
				contentType("application/vnd.chadbutz-v1+json").
				body("msg", Matchers.equalTo(String.format(MSG_TEMPLATE, name, 1, "Accept Header")));
	}

	@Test
	public void retrieveShouldResultIn404Version1InURL() {
		String name = "404";
		RestAssured.
			when().
				get(String.format("%s/v1/hello/{name}", API_PATH), name).
			then().
				statusCode(HttpStatus.SC_NOT_FOUND).
				contentType(ContentType.JSON);
	}

	@Test
	public void retrieveShouldResultIn404Version1InAcceptHeader() {
		String name = "404";
		RestAssured.
			given().
				accept("application/vnd.chadbutz-v1+json").
			when().
				get(String.format("%s/hello/{name}", API_PATH), name).
			then().
				statusCode(HttpStatus.SC_NOT_FOUND).
				contentType(ContentType.JSON);
	}

	@Test
	public void shouldCreateNewResourceVersion1InUrl() {
		String name = "world";
		RestAssured.
			given().
				contentType("application/json").
				accept("application/json").
				body("{ \"msg\": \"world\" }").
			when().
				post(String.format("%s/v1/hello", API_PATH)).
			then().
				statusCode(HttpStatus.SC_CREATED).
				header("Location", Matchers.equalTo(String.format("http://localhost:%s%s/v1/hello/%s", this.port, API_PATH, name)));
	}

	@Test
	public void shouldCreateNewResourceVersion1InAcceptHeader() {
		String name = "world";
		RestAssured.
			given().
				contentType("application/vnd.chadbutz-v1+json").
				body("{ \"msg\": \"world\" }").
			when().
				post(String.format("%s/hello", API_PATH)).
			then().
				statusCode(HttpStatus.SC_CREATED).
				header("Location", Matchers.equalTo(String.format("http://localhost:%s%s/hello/%s", this.port, API_PATH, name)));
	}
}