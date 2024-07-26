package com.project.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.demo.model.Report;
import com.project.demo.repository.DataRepository;
import com.project.demo.repository.ReportRepository;

@Service
public class ReportMongoServiceImpl implements IReportMongoService {

	Logger logger = LoggerFactory.getLogger(ReportMongoServiceImpl.class);

	@Autowired
	private DataRepository dataRepository;

	@Autowired
	private ReportRepository reportRepository;

	private static final String CRON = "59 59 23 * * * ";

	@Override
	@Scheduled(cron = CRON)
	@Async
	public void generateReport() {

		if (reportRepository.count() == 0) {
			List<Report> listData = dataRepository.generateReportHistory();

			logger.info("generateReportHistory" + new Gson().toJson(listData));

			reportRepository.saveAll(listData);
		} else {

			List<Report> listData = dataRepository.generateReportDay(dateNow());

			logger.info("generateReportDay" + new Gson().toJson(listData));

			reportRepository.saveAll(listData);
		}

	}

	private String dateNow() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);

		return strDate;
	}

}
