package com.project.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Report;

public interface ReportRepository extends MongoRepository<Report, String> {

}
