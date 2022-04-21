package events.repository;

import events.data.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query(value = "SELECT  * from Events where title= :title", nativeQuery = true)
    List<Event> findAllByTitle(String title);
}
