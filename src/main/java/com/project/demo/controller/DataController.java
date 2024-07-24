package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.TransactionRequestDto;
import com.project.demo.exception.GeneralException;
import com.project.demo.service.IDataService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class DataController {

	final static String INFO = "Se genero un error interno en el sistema";

	@Autowired
	private IDataService dataService;

	@ApiOperation(value = "Send trnasaction", notes = "Send trnasaction")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema) })

	})
	@PostMapping(value = "/send-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sendTransaction(@RequestBody @Validated TransactionRequestDto request) {

		try {
			Object response = dataService.sendTransaction(request);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			throw new GeneralException(INFO);

		}

	}

	@ApiOperation(value = "Send trnasaction", notes = "Send trnasaction")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(schema = @Schema) })

	})
	@PostMapping(value = "/send-transaction-masive", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sendTransactionMasive(@RequestBody @Validated List<TransactionRequestDto> request) {

		try {
			Object response = dataService.sendTransactionMasive(request);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			throw new GeneralException(INFO);

		}

	}

}
