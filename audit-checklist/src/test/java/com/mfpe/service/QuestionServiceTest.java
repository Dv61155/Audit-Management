package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.AuditType;
import com.mfpe.model.Question;
import com.mfpe.repository.QuestionRepo;

@SpringBootTest
class QuestionServiceTest {

	@Mock
	QuestionRepo repo;
	
	@InjectMocks
    QuestionServiceImpl impl;
	
	@Test
	public void contextLoads() throws Exception {
		assertNotNull(impl);
	}
	
	@Test
	public  void getQuestionsByAuditType(){
		List<Question> auditQuestion = new ArrayList<>();
		auditQuestion.add(new Question(1,"Question","AuditType","response"));
		when(repo.findAll()).thenReturn(auditQuestion);
		assertEquals(auditQuestion, impl.getQuestionsByAuditType(new AuditType("AuditType")));
	}

}
