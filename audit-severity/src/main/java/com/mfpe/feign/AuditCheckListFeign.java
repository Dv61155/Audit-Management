package com.mfpe.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.model.AuditType;
import com.mfpe.model.AuditQuestion;

@FeignClient(url = "${ms.checklist}", name="auditChecklist")
public interface AuditCheckListFeign {
	
	@PostMapping(value = "/checklistquestions" )
	public List<AuditQuestion> auditCheckListQuestions(@RequestHeader("Authorization") String jwt, @RequestBody AuditType auditType);
	
}
