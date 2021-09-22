package com.finance.tasklet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finance.model.Post;

@Service
public class PeopleSoftApi {

	private static final Logger log = LoggerFactory.getLogger(PeopleSoftApi.class);
	
	private final RestTemplate restTemplate;

    public PeopleSoftApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

	public Post createPost() {
	    String url = "https://jsonplaceholder.typicode.com/posts";

	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	    // create a post object
	    Post post = new Post(1, "Introduction to Spring Boot",
	            "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");

	    // build the request
	    HttpEntity<Post> entity = new HttpEntity<>(post, headers);

	    // send POST request
	    ResponseEntity<Post> response = this.restTemplate.exchange(url, HttpMethod.POST, entity, Post.class);

	    // check response status code
	    if (response.getStatusCode() == HttpStatus.CREATED) {
	        return response.getBody();
	    } else {
	        return null;
	    }
	}
	
}
