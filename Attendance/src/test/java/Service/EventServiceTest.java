package Service;

import Entity.Event;
import Repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import service.EventService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private EventService eventService;

    private Event event;
    private List<Event> eventList;

    @BeforeEach
    void setUp() {
        event = new Event();
        event.setId(1L);
        event.setEmployeeId("E001");
        event.setTimestamp(LocalDateTime.now());
        event.setType("IN");

        eventList = Arrays.asList(event);
    }

    @Test
    void testSaveEvent() {
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.saveEvent(event);

        assertEquals(event, result);
        verify(kafkaTemplate, times(1)).send("swipe-events", event.toString());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testGetEventsByEmployeeId() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        when(eventRepository.findByEmployeeIdAndTimestampBetween(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(eventList);

        List<Event> result = eventService.getEventsByEmployeeId("E001", start, end);

        assertEquals(eventList, result);
        verify(eventRepository, times(1))
                .findByEmployeeIdAndTimestampBetween("E001", start, end);
    }
}
