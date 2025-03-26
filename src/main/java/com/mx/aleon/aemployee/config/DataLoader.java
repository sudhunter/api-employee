package com.mx.aleon.aemployee.config;

import java.text.SimpleDateFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mx.aleon.aemployee.entity.Job;
import com.mx.aleon.aemployee.entity.Employee;
import com.mx.aleon.aemployee.repository.JobRepository;
import com.mx.aleon.aemployee.repository.EmployeeRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner loadData(JobRepository jobRepository, EmployeeRepository employeeRepository) {
	    return args -> {
	        // Insertar trabajos
	        Job developer = jobRepository.save(new Job(null, "Developer", "Builds and maintains software", 60000.0));
	        Job designer = jobRepository.save(new Job(null, "Designer", "Creates visual assets and UI/UX designs", 50000.0));
	        Job projectManager = jobRepository.save(new Job(null, "Project Manager", "Manages project timelines and deliverables", 70000.0));
	        Job tester = jobRepository.save(new Job(null, "Tester", "Tests and ensures quality of software", 45000.0));
	        Job dataAnalyst = jobRepository.save(new Job(null, "Data Analyst", "Analyzes data to provide insights", 65000.0));

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        employeeRepository.save(new Employee(null, "John", "Doe", 'M', sdf.parse("1993-01-15"), developer));
	        employeeRepository.save(new Employee(null, "Jane", "Smith", 'F', sdf.parse("1995-07-22"), designer));
	        employeeRepository.save(new Employee(null, "Michael", "Johnson", 'M', sdf.parse("1988-03-10"), projectManager));
	        employeeRepository.save(new Employee(null, "Emily", "Davis", 'F', sdf.parse("1998-11-02"), tester));
	        employeeRepository.save(new Employee(null, "Chris", "Brown", 'M', sdf.parse("1994-05-15"), dataAnalyst));
	        employeeRepository.save(new Employee(null, "Sarah", "Wilson", 'F', sdf.parse("1991-12-25"), developer));
	        employeeRepository.save(new Employee(null, "David", "Moore", 'M', sdf.parse("1983-08-08"), projectManager));
	        employeeRepository.save(new Employee(null, "Sophia", "Taylor", 'F', sdf.parse("2000-04-18"), designer));
	        employeeRepository.save(new Employee(null, "Daniel", "Anderson", 'M', sdf.parse("1996-09-12"), tester));
	        employeeRepository.save(new Employee(null, "Olivia", "Thomas", 'F', sdf.parse("1997-03-28"), dataAnalyst));
	        employeeRepository.save(new Employee(null, "James", "Jackson", 'M', sdf.parse("1992-06-05"), developer));
	        employeeRepository.save(new Employee(null, "Mia", "White", 'F', sdf.parse("1999-01-30"), projectManager));
	        employeeRepository.save(new Employee(null, "Andrew", "Harris", 'M', sdf.parse("1987-07-17"), designer));
	        employeeRepository.save(new Employee(null, "Isabella", "Martin", 'F', sdf.parse("1994-10-10"), tester));
	        employeeRepository.save(new Employee(null, "Luke", "Thompson", 'M', sdf.parse("1989-02-14"), dataAnalyst));
	        employeeRepository.save(new Employee(null, "Charlotte", "Garcia", 'F', sdf.parse("1990-11-25"), developer));
	        employeeRepository.save(new Employee(null, "Liam", "Martinez", 'M', sdf.parse("1996-08-19"), designer));
	        employeeRepository.save(new Employee(null, "Amelia", "Robinson", 'F', sdf.parse("1993-05-07"), projectManager));
	        employeeRepository.save(new Employee(null, "Ethan", "Clark", 'M', sdf.parse("1998-03-03"), tester));
	        employeeRepository.save(new Employee(null, "Ava", "Lewis", 'F', sdf.parse("1995-12-12"), dataAnalyst));
	    };
	}
}
