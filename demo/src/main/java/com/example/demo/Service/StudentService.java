package com.example.demo.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.API.Student;
import com.example.demo.DAL.IStudentRepository;



@Service
public class StudentService {

	private final IStudentRepository iStudentRepository;
	
	@Autowired
	public StudentService(IStudentRepository iStudentRepository) {
	
		this.iStudentRepository = iStudentRepository;
	
	}

	public List<Student> getStudents(){
			
		return iStudentRepository.findAll();
			
	}

	public void addNewStudent(Student student) {
		
		Optional<Student> studentCheck = iStudentRepository
				.findStudentByEmail(student.getEmail());
		
		if(studentCheck.isPresent()) {		
			throw new IllegalStateException("email taken");			
		}
		
		iStudentRepository.save(student);
		
	}

	public void deleteStudent(Long id) {
		boolean exists = iStudentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException(
					"student with id " + id + " does not exists"
					);
		}
		iStudentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long studentId,
							  String name,
							  String email) {
		Student student = iStudentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException(
						"student with id " + studentId + " does not exists") );
		if(name != null  &&
				name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
			
		if(email != null  &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
			
			Optional<Student> studentCheck = iStudentRepository
					.findStudentByEmail(email);
			
			if(studentCheck.isPresent()) {		
				throw new IllegalStateException("email taken");			
			}
			student.setEmail(email);
		}
	}
	
}
