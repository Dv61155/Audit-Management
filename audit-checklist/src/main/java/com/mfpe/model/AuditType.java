package com.mfpe.model;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditType {
	
	@NotEmpty(message = "AuditType is required")
	private String auditType;	
	
}
