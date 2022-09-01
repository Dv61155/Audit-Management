package com.mfpe.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.FeignClientException;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.service.AuditBenchmarkService;
import com.mfpe.service.AuthorizationService;

@RestController
@RequestMapping("/benchmark")
@CrossOrigin(origins = "*")
public class AuditBenchmarkController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private AuditBenchmarkService auditBenchmarkService;

	Logger logger = LoggerFactory.getLogger("Benchmark-Controller-Logger");

	// Endpoint to retrieve the Audit Benchmark details
	@GetMapping("/auditbenchmark")
	public List<AuditBenchmark> getAuditBenchmark(@RequestHeader("Authorization") String jwt) {
		List<AuditBenchmark> auditBenchmarks = new ArrayList<>();

		// checking if the jwt is valid or not
		logger.info("from header JWT :: " + jwt);
		try {
			if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
				auditBenchmarks = auditBenchmarkService.getAuditBenchmarkList();
			} else {
				logger.error("Failed to validate the JWT :: " + jwt);
			}
		} catch (Exception e) {
			String[] arrOfStr = e.getMessage().split(":");
			throw new FeignClientException(arrOfStr[arrOfStr.length - 1]);
		}

		return auditBenchmarks;
	}

	@GetMapping("health-check")
	public String healthCheck() {
		return "Audit Benchmark Microservice is Active";
	}
}
