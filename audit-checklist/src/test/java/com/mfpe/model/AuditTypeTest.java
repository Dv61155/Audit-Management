package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditTypeTest {

	@Test
	public void testGetterAndSetter() {
		AuditType auditType = new AuditType();
		auditType.setAuditType("new auditType");
		assertEquals("new auditType",auditType.getAuditType());
	}
	
	@Test
	public void testConstructorAndToString() {
		AuditType auditType = new AuditType("Internal");
		assertEquals("AuditType(auditType=Internal)",auditType.toString());
	}

}
