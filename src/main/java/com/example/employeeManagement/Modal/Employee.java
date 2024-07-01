package com.example.employeeManagement.Modal;


import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {




    public Employee(){

    }
    public Employee(String employeeId,String name, String department, String emailId, String jobTitle, Address address) {
        super();
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.emailId = emailId;
        this.jobTitle = jobTitle;
        this.address = address;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;
    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;
    @Column(name = "email_Id")
    private String emailId;
    @Column(name = "job_title")
    private String jobTitle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
