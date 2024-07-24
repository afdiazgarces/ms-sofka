package com.project.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.project.demo.dto.TransactionRequestDto;
import com.project.demo.model.Transaction;
import com.project.demo.repository.DataRepository;

@Service
public class DataServiceImpl implements IDataService {

	Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

	@Autowired
	private DataRepository dataRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${kafka.nameTopic}")
	private String nameTopic;

	private final Gson gson = new Gson();

	@Override
	@Transactional
	public Object sendTransaction(TransactionRequestDto request) {

		Transaction transaction = modelMapper.map(request, Transaction.class);

		kafkaTemplate.send(nameTopic, gson.toJson(request));

		return dataRepository.save(transaction);
	}

	@Override
	@Transactional
	public Object sendTransactionMasive(List<TransactionRequestDto> request) {

		List<Transaction> ListTransaction = request.stream().map(entity -> modelMapper.map(entity, Transaction.class))
				.collect(Collectors.toList());

		kafkaTemplate.send(nameTopic, gson.toJson(request));

		return dataRepository.saveAll(ListTransaction);

	}

}
