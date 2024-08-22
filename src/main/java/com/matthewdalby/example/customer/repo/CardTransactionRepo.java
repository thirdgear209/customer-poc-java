package com.matthewdalby.example.customer.repo;

import org.springframework.data.repository.CrudRepository;

import com.matthewdalby.example.customer.entity.CardTransaction;

public interface CardTransactionRepo extends CrudRepository<CardTransaction, Long>{

}
