package controller;




import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EventService;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        Event Event = null;
        return ok(eventService.saveEvent(Event));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable String employeeId, @RequestParam String start, @RequestParam String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return (ResponseEntity<List<Event>>) ok();
    }
}
