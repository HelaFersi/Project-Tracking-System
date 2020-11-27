package com.pfa.pack.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity(name = "employees")
@Table(name = "employees", indexes = {@Index(name = "employees_email_IX", columnList = "email", unique = true)})
public final class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", unique = true, nullable = false, precision = 10)
	private Integer employeeId;
	
	@NotBlank(message = "** Must give a firstname **")
	@Column(name = "first_name", length = 200)
	private String firstName;
	
	@NotBlank(message = "** Must give a lastname **")
	@Column(name = "last_name", length = 200)
	private String lastName;
	
	@Email(message = "*Must be in an email form")
	@NotBlank
	@Column(name = "email", unique = true, length = 200)
	private String email;
	
	@NotBlank(message = "** Must give a phone number **")
	@Column(name = "phone", length = 20)
	private String phone;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "hiredate")
	private LocalDate hiredate;
	
	@NotBlank(message = "** Must give a job **")
	@Column(name = "job", length = 200)
	private String job;
	
	@NotBlank(message = "** Must give a salary **")
	@Column(name = "salary", precision = 7, scale = 2)
	private Double salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Employee manager;
	
	@JsonIgnore
	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	private Set<Employee> employees;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private Set<Assignment> assignments;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
	private UserCredential userCredential;
	
	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + ", hiredate=" + hiredate + ", job=" + job + ", salary=" + salary
				+ ", manager=" + manager + ", department=" + department + ", userCredential=" + userCredential + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public LocalDate getHiredate() {
		return hiredate;
	}

	public void setHiredate(final LocalDate hiredate) {
		this.hiredate = hiredate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(final String job) {
		this.job = job;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(final Double salary) {
		this.salary = salary;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(final Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getEmployees() {
		return Collections.unmodifiableSet(this.employees);
	}

	public void setEmployees(final Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Assignment> getAssignments() {
		return Collections.unmodifiableSet(this.assignments);
	}

	public void setAssignments(final Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(final UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}
	
	
	
}













