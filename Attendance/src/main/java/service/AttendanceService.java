package service;

import Entity.Attendance;
import Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @KafkaListener(topics = "swipe-events", groupId = "attendance-group")
    public void listenToEvents(String eventString) {
        // Parse eventString to Event object
        // Calculate attendance based on event data
        // Save attendance data to database
    }

    public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}

