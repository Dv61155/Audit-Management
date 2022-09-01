package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.AuditType;
import com.mfpe.model.Question;
import com.mfpe.repository.QuestionRepo;

@SpringBootTest
class QuestionServiceTest {

	@Mock
	QuestionRepo repo;
	
	@Autowired
    QuestionServiceImpl impl;
	
	@Test
	public void contextLoads() throws Exception {
		assertNotNull(impl);
	}
	
	@Test
	public  void getQuestionsByAuditType(){
	//	AuditType type=new AuditType("Internal");
		List<Question> questions = new ArrayList<>();		
		questions.add(new Question(1,"Have all Change requests followed SDLC before PROD move?","Internal","No"));
		questions.add(new Question(2,"Have all Change requests been approved by the application owner?","Internal","No"));
		questions.add(new Question(3,"Are all artifacts like CR document, Unit test cases available?","Internal","No"));
		questions.add(new Question(4,"Is the SIT and UAT sign-off available?","Internal","No"));
		questions.add(new Question(5,"Is data deletion from the system done with application owner approval?","Internal","No"));
	
		when(repo.findAll()).thenReturn(questions);
		assertEquals(questions,impl.getQuestionsByAuditType(new AuditType("Internal")));
	}

}
