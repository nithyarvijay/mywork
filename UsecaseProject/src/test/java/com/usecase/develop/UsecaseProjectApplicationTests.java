package com.usecase.develop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase.develop.entities.Book;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class UsecaseProjectApplicationTests {
    
	/**
	 * A Test method to verify the Rest API service to get all the Books 
	 * @throws JSONException 
	 * 
	 */
	@Test
	public void testGetAllBooks() throws JSONException {
		//The base URI to be used
		RestAssured.baseURI = "http://localhost:9085/Books/";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.GET);

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
				
		//Check the response values
		JSONArray jsArry = new JSONArray(response.body().asString());
				
		Assert.assertEquals(((JSONObject) jsArry.get(0)).get("id"), 1);
		Assert.assertEquals(((JSONObject) jsArry.get(0)).get("bookName"), "Alice in Wonderland");
		Assert.assertEquals(((JSONObject) jsArry.get(0)).get("authorName"), "Dangote");
	}
	
	/**
	 * A Test method to verify the Rest API service to get the Book with ID
	 * 
	 * @throws JSONException
	 */
	@Test
	void testGetBookById() throws JSONException {
		//The base URI to be used
		RestAssured.baseURI = "http://localhost:9085/Books/";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.GET, "2");

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		//Check the response values
		JSONObject jsObj = new JSONObject(response.body().asString());
		System.out.println("Response Body : "+ jsObj);
		
		Assert.assertEquals(jsObj.get("id"), 2);
		Assert.assertEquals(jsObj.get("bookName"), "Harry Whats this");
		Assert.assertEquals(jsObj.get("authorName"), "Gates");
	}


	
	/**
	 * A Test method to verify the Rest API service to Add or Update a Book based the entity details passed 
	 * @throws JSONException 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testCreateOrUpdateBook() throws JSONException, JsonProcessingException {
        RestAssured.baseURI = "http://localhost:9085/Books/add"; 
        RequestSpecification httpRequest = RestAssured.given();
 
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book();
        book.setId((long)3);
        book.setBookName("Folrunsho");
        book.setAuthorName("Alakija");
        book.setPublisher("Connecting Books Company");
                
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(book);
 
        httpRequest.header("Content-Type", "application/json"); 
        httpRequest.body(jsonString);
		
        Response response = httpRequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
 
        //Check the response values
      	JSONObject jsObj = new JSONObject(response.body().asString());
      	System.out.println("Response Body : "+ jsObj);
      		
      	Assert.assertEquals(jsObj.get("id"), 3);
      	Assert.assertEquals(jsObj.get("bookName"), "Folrunsho");
      	Assert.assertEquals(jsObj.get("authorName"), "Alakija");
      	Assert.assertEquals(jsObj.get("publisher"), "Connecting Books Company");
	}
	
	/**
	 * A Test method to verify the Rest API service to Add or Update a Book based the entity details passed 
	 * @throws JSONException 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testCreateBook() throws JSONException, JsonProcessingException {
        RestAssured.baseURI = "http://localhost:9085/Books/add"; 
        RequestSpecification httpRequest = RestAssured.given();
 
        //Creating the ObjectMapper object
        ObjectMapper mapper = new ObjectMapper();
        Book book = new Book();       
        book.setBookName("Test Book");
        book.setAuthorName("Test Book");
        book.setPublisher("Test Books Company");
                
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(book);
 
        httpRequest.header("Content-Type", "application/json"); 
        httpRequest.body(jsonString);
		
        Response response = httpRequest.request(Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
 
        //Check the response values
      	JSONObject jsObj = new JSONObject(response.body().asString());
      	System.out.println("Response Body : "+ jsObj);
      	
      	Assert.assertEquals(jsObj.get("bookName"), "Test Book");
      	Assert.assertEquals(jsObj.get("authorName"), "Test Book");
      	Assert.assertEquals(jsObj.get("publisher"), "Test Books Company");
      	
      	testDeleteBookById(jsObj.get("id").toString());
	}

	/**
	 * A Test method to verify the Rest API service to Delete a Book with specific ID
	 * 
	 * @throws JSONException
	 */
	@Test
	static void testDeleteBookById(String id) throws JSONException {
		//The base URI to be used
		RestAssured.baseURI = "http://localhost:9085/Books/";

		//Define the specification of request. Server is specified by baseURI above.
		RequestSpecification httpRequest = RestAssured.given();

		//Makes calls to the server using Method type.
		Response response = httpRequest.request(Method.DELETE, id);

		//Checks the Status Code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
}
