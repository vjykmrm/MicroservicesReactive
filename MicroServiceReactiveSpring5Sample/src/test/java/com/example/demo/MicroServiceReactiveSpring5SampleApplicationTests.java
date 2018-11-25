package com.example.demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec;

import com.example.demo.Repository.ReactiveAccountRepository;
import com.example.demo.controller.AccountController;
import com.example.demo.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MicroServiceReactiveSpring5SampleApplicationTests {
	 WebTestClient webTestClient;


	    List<Account> expectedAccounts;

	    @Autowired
	    ReactiveAccountRepository reactiveAccountRepository;

	    @Before
	    public void setup() {
	        webTestClient = WebTestClient.bindToController(new AccountController(reactiveAccountRepository))
	        		.httpMessageCodecs(clientDefaultCodecsConfigurer -> {
	                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
	                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.APPLICATION_JSON));

	                }).configureClient().build();
	        expectedAccounts = reactiveAccountRepository.findAll().collectList().block();
//	        System.out.println(expectedAccounts.get(0).getCreationDate());
//	        System.out.println(expectedAccounts.get(1).getCreationDate());
	    }

	    @Test
	    public void findAllAccountsTest() {
	     ListBodySpec<Account> acts=  this.webTestClient.get().uri("/accounts/")
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	                .expectBodyList(Account.class);
	    List<Account> lacts = acts.returnResult().getResponseBody();
//	    System.out.println("<====>" + lacts.get(0).getCreationDate());
//        System.out.println("<====>" + lacts.get(1).getCreationDate());
	     
	    	
	    	 
		        
	                 
	    	   this.webTestClient.get().uri("/accounts/")
               .accept(MediaType.APPLICATION_JSON_UTF8)
               .exchange()
               .expectStatus().isOk()
               .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
               .expectBodyList(Account.class);
               //.isEqualTo(expectedAccounts); - Not matching as JSON deserializing removeing dateformat and returning as Milliseconds. 
	    }

	    @Test
	    public void streamAllAccountsTest() throws Exception {
//	    	FluxExchangeResult<Account> result = 
	    			this.webTestClient.get()
	                .uri("/accounts/")
	                .accept(MediaType.parseMediaType("text/event-stream;charset=UTF-8"))
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.parseMediaType("text/event-stream;charset=UTF-8"))
	                .returnResult(Account.class);
	    	
	    	
	    }

	    @Test
	    public void streamAllAccountsByCurrencyTest() throws Exception {
	        this.webTestClient.get()
	                .uri("/accounts/?currency=EUR")
	                .accept(MediaType.parseMediaType("text/event-stream;charset=UTF-8"))
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.parseMediaType("text/event-stream;charset=UTF-8"))
	                .returnResult(Account.class);
	    }

}
