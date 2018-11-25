package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.ReactiveAccountRepository;
import com.example.demo.model.Account;
import com.example.demo.model.Currency;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    
    private ReactiveAccountRepository reactiveAccountRepository;
    
    public AccountController (ReactiveAccountRepository reactiveAccountRepository)
    {
    	this.reactiveAccountRepository =  reactiveAccountRepository;
    }

    @RequestMapping(value = "/currency/{currency}", method = RequestMethod.GET)
    Flux<Account> findByCurrency(@PathVariable String currency) {
        return reactiveAccountRepository.findByCurrency(Currency.fromValue(currency));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Mono<Account> findById(@PathVariable String id) {
        return reactiveAccountRepository.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Mono<Account> save(@RequestBody Account account) {
        return reactiveAccountRepository.save(account);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    Flux<Account> saveAll(@RequestBody Flux<Account> accounts) {
        return reactiveAccountRepository.saveAll(accounts);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Flux<Account> findAll() {
        return reactiveAccountRepository.findAll();
    }
}