package Repository;




import Entity.Event;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEmployeeIdAndTimestampBetween(String employeeId, LocalDateTime start, LocalDateTime end);

    jdk.jfr.Event save(jdk.jfr.Event event);
}
