package com.mfpe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mfpe.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
	
	// Retrieves the data from the database by searching using the auditType
	List<Question> getQuestionsByAuditType(String auditType);
	
}
