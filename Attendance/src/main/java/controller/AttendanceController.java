package controller;

import Entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendance(@PathVariable String employeeId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployeeId(employeeId));
    }
}