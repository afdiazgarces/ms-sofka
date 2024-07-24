package com.project.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String transactionId;
	private Timestamp timestamp;
	private Integer deviceNumber;
	private Integer userId;
	private String geoPosition;
	private Float amount;

}
