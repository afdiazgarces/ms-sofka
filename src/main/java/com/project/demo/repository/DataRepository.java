package com.project.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Report;
import com.project.demo.model.Transaction;

public interface DataRepository extends MongoRepository<Transaction, String> {
	
	@Aggregation("    {\r\n"
			+ "      $group: {\r\n"
			+ "        _id: {\r\n"
			+ "          $dateToString: {\r\n"
			+ "            format: '%Y-%m-%d',\r\n"
			+ "            date: '$timestamp'\r\n"
			+ "          }\r\n"
			+ "        },\r\n"
			+ "        totalTransactions: { $sum: 1 },\r\n"
			+ "        totalAmount: { $sum: '$amount' }\r\n"
			+ "      }\r\n"
			+ "    }")
	List<Report> generateReportHistory();
	

	
	@Aggregation(pipeline = {
			"    {\r\n"
			+ "      $project: {\r\n"
			+ "        _id: {\r\n"
			+ "          $dateToString: {\r\n"
			+ "            format: '%Y-%m-%d',\r\n"
			+ "            date: '$timestamp'\r\n"
			+ "          }\r\n"
			+ "        },\r\n"
			+ "        timestamp: { $getField: 'timestamp' },\r\n"
			+ "        amount: { $getField: 'amount' }\r\n"
			+ "      }\r\n"
			+ "    }",
			"{ $match: { _id: ?0 } }",
			"{\r\n"
			+ "      $group: {\r\n"
			+ "        _id: {\r\n"
			+ "          $dateToString: {\r\n"
			+ "            format: '%Y-%m-%d',\r\n"
			+ "            date: '$timestamp'\r\n"
			+ "          }\r\n"
			+ "        },\r\n"
			+ "        totalTransactions: { $sum: 1 },\r\n"
			+ "        totalAmount: { $sum: '$amount' }\r\n"
			+ "      }\r\n"
			+ "    }"
	})
	List<Report> generateReportDay(String date);

}
