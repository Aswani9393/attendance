package service;


import Repository.EventRepository;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Event saveEvent(Event event) {
        kafkaTemplate.send("swipe-events", event.toString());
        return eventRepository.save(event);
    }

    public List<Entity.Event> getEventsByEmployeeId(String employeeId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByEmployeeIdAndTimestampBetween(employeeId, start, end);
    }
}
