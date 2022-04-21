package events.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Table(name = "SPEAKERS")
@Entity
@Data
public class Speaker {

    private static Log log = LogFactory.getLog(Speaker.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToMany(mappedBy = "speaker")
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    @Transient
    private String fullName;


    public void addEvent(Event event) {
        getEvents().add(event);
        log.info("Event:" + event.getTitle() + " added to Speaker:" + this.getFullName() + ".");
    }

    @PostLoad
    private void fullNameLoad() {
        this.fullName = this.getFirstName() + " " + this.getLastName();
        log.info("data.data.Speaker loaded from database: id=" + this.id + ", fullName=" + this.fullName + ".");
    }


}
