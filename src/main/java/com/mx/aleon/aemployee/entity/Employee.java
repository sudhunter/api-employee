package com.mx.aleon.aemployee.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents an Employee entity in the system. 
 * This entity is mapped to a database table and contains personal details,
 * job information, and other attributes related to employees.
 */
@Entity
public class Employee {

    /**
     * Unique identifier for the employee, generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the employee.
     */
    private String firstName;

    /**
     * Last name of the employee.
     */
    private String lastName;


    /**
     * Gender of the employee.
     * Example values: 'M' for Male, 'F' for Female.
     */
    private Character gender;

    /**
     * Birth date of the employee.
     * This field is stored as a DATE type in the database.
     */
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * Job position associated with the employee.
     * This field establishes a many-to-one relationship with the Job entity.
     */
    @ManyToOne
    @JoinColumn(name = "job_id") // Foreign key column in the database
    private Job job;
    

    public Employee() {
		super();
	}

	public Employee(Long id, String firstName, String lastName, Character gender, Date birthDate,
			Job job) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.job = job;
	}

	/**
     * Retrieves the employee's unique identifier.
     *
     * @return the ID of the employee
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the employee's unique identifier.
     *
     * @param id the ID of the employee
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the first name of the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name of the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the gender of the employee.
     *
     * @return the gender of the employee
     */
    public Character getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender the gender of the employee
     */
    public void setGender(Character gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the birth date of the employee.
     *
     * @return the birth date of the employee
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the employee.
     *
     * @param birthDate the birth date of the employee
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the job associated with the employee.
     *
     * @return the job associated with the employee
     */
    public Job getJob() {
        return job;
    }

    /**
     * Sets the job associated with the employee.
     *
     * @param job the job to associate with the employee
     */
    public void setJob(Job job) {
        this.job = job;
    }

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", job=" + job + "]";
	}
    
    
}
