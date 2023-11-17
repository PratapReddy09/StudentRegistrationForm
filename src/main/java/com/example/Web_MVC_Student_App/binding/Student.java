package com.example.Web_MVC_Student_App.binding;

import lombok.Data;

@Data
public class Student {

	private String name;
	private String email;
	private String gender;
	private String course;
	private String[] timings;
}
