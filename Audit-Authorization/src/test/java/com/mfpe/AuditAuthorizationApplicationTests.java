package com.mfpe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditAuthorizationApplicationTests {

	@Test
	 void main() {
		AuditAuthorizationApplication.main(new String[] {});
		assertTrue(true);
	}
}
