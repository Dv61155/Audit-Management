package com.mfpe.model;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuditType {
	
	@NotEmpty(message = "AuditType is required")
	private String auditType;	
	
}
