package com.project.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Integer totalTransactions;
	private Float totalAmount;

}
