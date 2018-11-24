package com.example.demo.Repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;
import com.example.demo.model.Currency;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveAccountRepository extends ReactiveCrudRepository<Account, String> {

    Flux<Account> findByCurrency(Currency currency);
}