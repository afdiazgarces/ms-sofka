package com.project.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Transaction;

public interface DataRepository extends MongoRepository<Transaction, String> {

}
