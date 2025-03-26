package com.mx.aleon.aemployee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * Represents a Job entity in the system. This entity stores information about a
 * job position, including its title, description, and base salary, and it is
 * mapped to a database table.
 */
@Entity
public class Job {

	/**
	 * Unique identifier for the job position, generated automatically.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Title of the job position (e.g., "Software Engineer", "Manager").
	 */
	@Column(unique = true)
	private String title;

	/**
	 * Detailed description of the job position, outlining responsibilities and
	 * role.
	 */
	private String description;

	/**
	 * Base salary associated with the job position. This value represents the
	 * starting salary for the role.
	 */
	private double baseSalary;

	/**
	 * Formats the title of the job by converting it to uppercase. This method is
	 * triggered automatically before the entity is persisted to the database or
	 * updated, ensuring the title is stored in a consistent format.
	 *
	 * Example: - Input: "software engineer" - Output: "SOFTWARE ENGINEER"
	 *
	 * Note: - If the title is null, no transformation is applied.
	 */
	@PrePersist
	@PreUpdate
	private void formatTitle() {
		if (title != null) {
			title = title.toUpperCase();
		}
	}

	public Job() {
		super();
	}

	public Job(Long id, String title, String description, double baseSalary) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.baseSalary = baseSalary;
	}

	/**
	 * Retrieves the unique identifier for the job.
	 *
	 * @return the ID of the job
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the job.
	 *
	 * @param id the ID to assign to the job
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the title of the job.
	 *
	 * @return the title of the job
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the job.
	 *
	 * @param title the title to assign to the job
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retrieves the description of the job.
	 *
	 * @return the description of the job
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the job.
	 *
	 * @param description the description to assign to the job
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the base salary of the job.
	 *
	 * @return the base salary of the job
	 */
	public double getBaseSalary() {
		return baseSalary;
	}

	/**
	 * Sets the base salary of the job.
	 *
	 * @param baseSalary the base salary to assign to the job
	 */
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", description=" + description + ", baseSalary=" + baseSalary
				+ "]";
	}

}
