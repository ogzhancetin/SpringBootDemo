package com.example.demo.API;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StudentService;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		
		this.studentService = studentService;
		
	}
	
	@GetMapping("info")
	public List<Student> getStudents(){
		
		return studentService.getStudents();
		
	}

	@PostMapping("add")
	public void registerStudent(@RequestBody Student student){
		
		studentService.addNewStudent(student);	
	}
	
	@DeleteMapping("delete/{studentId}")
	public void deleteStudent(
			@PathVariable("studentId") Long id) {
		
		studentService.deleteStudent(id);
	}
	
	@PutMapping("update/{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
	}
			
	
	
	
}





