package com.example.Web_MVC_Student_App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Web_MVC_Student_App.Service.StudentService;
import com.example.Web_MVC_Student_App.binding.Student;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/")
	public String loadIndexPage(Model model) {
		Student sobj=new Student();//empty student object
		init(model, sobj);
		return "index";
	}

	private void init(Model model, Student sobj) {
		model.addAttribute("student", sobj);
		model.addAttribute("courses", service.getCourses());//this is used to send the drop down list course data to UI
		model.addAttribute("PrefTimings", service.getTimings());//this is used to send the checkbox list timings to UI
	}

	/*Note: when we send request to  http://localhost:8080/ this URL it goes request to get mapping 
	 * all drop down list, checkbox list, will be displayed as expected and process good.
	 
	 */
	
	
	//when we click on the submit button the request comes to post mapping, for every request a new model object
	//will be created(it is a fresh model object, data is not available)
	//when we form submitted we need to re-directed to the same page and all should be available(like drop down list data, checkbox data)
	//that's why we added the below methods
	//model.addAttribute("student", sobj);//this is a new empty object(form data shoud be clear)
	//model.addAttribute("courses", service.getCourses());//set courses list to display
	//model.addAttribute("PrefTimings", service.getTimings());//set timings check list to display
	//
	//
	@PostMapping("/save")
	public String handleSubmitBtn(Student s, Model model) {
		System.out.println(s);//used to print in the console
		Student sobj=new Student();
		
		boolean isSaved = service.saveStudent(s);
		if(isSaved) {
			model.addAttribute("msg", "Data Saved");
		}
		init(model, sobj);
		return "index";
	}
}
