package com.example.Web_MVC_Student_App.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Web_MVC_Student_App.Entity.StudentEntity;
import com.example.Web_MVC_Student_App.Repository.StudentRepository;
import com.example.Web_MVC_Student_App.binding.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	/*
	 * this method data available in the student binding object only, but save method takes only entity
	 * objects that's why we converted student binding object to entity object by using BeanUtils
	 */
	public boolean saveStudent(Student student) {
		
		StudentEntity entity=new StudentEntity();//student entity object
		BeanUtils.copyProperties(student, entity);//copy from student binding object to entity object
		//student binding object timings is a string array , But, student entity having it is only  string
		//so, data types are different that's why we are doing manual conversion. if data types are same 
		//it will automatically conversion happens
		entity.setTimings(Arrays.toString(student.getTimings()));//convert array to string
		repo.save(entity);
		return true;
		
	}
	
	public List<String> getCourses(){
		return Arrays.asList("Java", "Python","AWS","Devops");
		
	}
	
	public List<String> getTimings(){
		return Arrays.asList("Morning", "AfetNoon","Evening");
		
	}

}
