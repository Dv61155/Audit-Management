package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectManagerTest {

	@Test
	public void testGetterAndSetter()
	{
		ProjectManager obj=new ProjectManager();
		obj.setId(1);
		obj.setName("DHeeraj");
		obj.setUsername("Dheeraj");
		obj.setPassword("dhee123");
		obj.setProjectName("audit-authenticate");
		
		assertEquals(1,obj.getId());
		assertEquals("DHeeraj",obj.getName());
		assertEquals("Dheeraj",obj.getUsername());
		assertEquals("dhee123",obj.getPassword());
		assertEquals("audit-authenticate",obj.getProjectName());
	}
	
	@Test
	public void testconstructorAndTostring()
	{
		ProjectManager obj=new ProjectManager(1,"Dheeraj","Dheeraj","dhee123","audit-authenticate");
		assertEquals("ProjectManager(id=1, name=Dheeraj, username=Dheeraj, password=dhee123, projectName=audit-authenticate)",obj.toString());
	}

}
