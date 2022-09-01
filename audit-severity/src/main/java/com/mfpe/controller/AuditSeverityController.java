package com.mfpe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.EmptyInputException;
import com.mfpe.exception.FeignClientException;
import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.model.AuditType;
import com.mfpe.model.AuditQuestion;
import com.mfpe.service.AuditResponseService;
import com.mfpe.service.AuthorizationService;

@RestController
@RequestMapping("/severity")
@CrossOrigin(origins = "*")
public class AuditSeverityController {

	@Autowired
	private AuditBenchmarkFeign auditBenchmarkFeign;

	@Autowired
	private AuditResponseService auditResponseService;

	@Autowired
	private AuthorizationService authorizationService;

	Logger logger = LoggerFactory.getLogger("Severity-Controller-Logger");

	// It is to check the severity of the audit and it returns the execution status
	// of the particular project
	@PostMapping("/projectexecutionstatus")
	public ResponseEntity<?> auditSeverity(@RequestHeader("Authorization") String jwt,
			@RequestBody AuditRequest auditRequest) {

		AuditResponse auditResponse = new AuditResponse();

		ResponseEntity<AuditResponse> response = null;

		// checking here if the jwt is valid or not
		// here creating auditResponse according to auditRequest
		
		
		
		try {
			if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
				// logger.info("Successfully validated the JWT :: " + jwt);

				// here getting benchmark list from Benchmark micro-services
				List<AuditBenchmark> benchmarkList = auditBenchmarkFeign.getAuditBenchmark(jwt);

				AuditType auditType = new AuditType();

				//checking if inputs is not empty
				if(auditRequest ==null || auditRequest.getProjectName() == null
						               || auditRequest.getManagerName() == null
						               || auditRequest.getAuditDetail() ==null
						               || auditRequest.getAuditDetail().getAuditType() == null
						               || auditRequest.getAuditDetail().getAuditQuestions()==null) {
					throw new EmptyInputException("Give Inputs in proper-format");
				}
				
				
				auditType.setAuditType(auditRequest.getAuditDetail().getAuditType());

				// here we getting responses back from RequestBody
				List<AuditQuestion> questionResponses = auditRequest.getAuditDetail().getAuditQuestions();

				// here we create Audit-response
				auditResponse = auditResponseService.getAuditResponse(benchmarkList, auditType.getAuditType(),
						questionResponses);

				// her saving response in DB
				auditResponse = auditResponseService.saveAuditResponse(auditResponse, auditRequest);

				logger.info("AuditResponse successfully created!!");

				response = new ResponseEntity<AuditResponse>(auditResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Failed to validate the JWT :: " + jwt);
			System.out.println("--------" + e.getMessage());
			String[] arrOfStr = e.getMessage().split(":");
			throw new FeignClientException(arrOfStr[arrOfStr.length - 1]);

		}
		return response;

	}

	@GetMapping("health-check")
	public String healthCheck() {
		return "Audit Benchmark Microservice is working fine !";
	}
}
