package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private LocalDate date;
    private String status; // Absent, Half day, Present
    private int totalHours;
}