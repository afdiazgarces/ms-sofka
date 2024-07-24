package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.TransactionRequestDto;

public interface IDataService {

	Object sendTransaction(TransactionRequestDto request);

	Object sendTransactionMasive(List<TransactionRequestDto> request);

}
