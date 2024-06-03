package Lab.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "job_title")
    private String jobTitle;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private double salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
