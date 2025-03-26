package com.mx.aleon.aemployee.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the response object for employee-related API operations.
 * This class extends {@link GenericResponse} to include additional details
 * specific to an employee, such as their ID, name, age, gender, birth date, 
 * and job title.
 */
@Schema(description = "Employee Value Object representing employee details")
public class EmployeeVO{

	/**
     * Unique identifier for the employee.
     */
    private Long id;

    /**
     * First name of the employee.
     */
    @Schema(description = "First name of the employee", example = "Juan")
    private String firstName;

    /**
     * Last name of the employee.
     */
    @Schema(description = "Last name of the employee", example = "Silva")
    private String lastName;

    /**
     * Age of the employee in years.
     */
    @Schema(hidden = true)
    private Integer age;

    /**
     * Gender of the employee.
     * Example values: 'M' for Male, 'F' for Female.
     */
    @JsonProperty("gender")
    @Schema(description = "Gender of the employee", example = "M")
    private String gender;

    /**
     * Birth date of the employee, formatted as dd-MM-yyyy.
     */
    @JsonProperty("birthDate")
    @Schema(description = "Birth date of the employee in dd-MM-yyyy format", example = "01-03-2010")
    private String birthDate;

    /**
     * Job title of the employee (e.g., "Software Engineer", "Manager").
     */
    @JsonProperty("jobTitle")
    @Schema(description = " Job title of the employee", example = "Manager")
    private String jobTitle;

    public EmployeeVO() {
		super();
	}

	public EmployeeVO(String firstName, String lastName, String gender, String birthDate, String jobTitle) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
	}

	public EmployeeVO(Long id, String firstName, String lastName, String gender, String birthDate, String jobTitle) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
	}

	public EmployeeVO(Long id, String firstName, String lastName, Integer age, String gender, String birthDate,
			String jobTitle) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
	}

	/**
     * Retrieves the unique identifier of the employee.
     *
     * @return the ID of the employee
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the employee.
     *
     * @param id the ID to assign to the employee
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
     * @param firstName the first name to assign to the employee
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
     * @param lastName the last name to assign to the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the age of the employee in years.
     *
     * @return the age of the employee
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age of the employee in years.
     *
     * @param age the age to assign to the employee
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Retrieves the gender of the employee.
     *
     * @return the gender of the employee
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender the gender to assign to the employee
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the birth date of the employee.
     *
     * @return the birth date of the employee
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the employee.
     *
     * @param birthDate the birth date to assign to the employee
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the job title of the employee.
     *
     * @return the job title of the employee
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets the job title of the employee.
     *
     * @param jobTitle the job title to assign to the employee
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", birthDate=" + birthDate + ", jobTitle=" + jobTitle + "]";
	}
    
    
}