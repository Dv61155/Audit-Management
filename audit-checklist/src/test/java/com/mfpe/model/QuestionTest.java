package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionTest {

	@Test
	public void testGetterAndSetter() {
		Question auditQuestion = new Question();
		auditQuestion.setQuestionId(1);
		auditQuestion.setQuestion("new Question");
		auditQuestion.setAuditType("new auditType");
		auditQuestion.setResponse("yes");
		assertEquals(1,auditQuestion.getQuestionId());
		assertEquals("new auditType",auditQuestion.getAuditType());
		assertEquals("new Question",auditQuestion.getQuestion());
		assertEquals("yes",auditQuestion.getResponse());
	}

}
