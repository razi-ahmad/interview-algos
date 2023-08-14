package payconiq;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
class HealthcheckController {

    private final IHealthcheckService service;

    public HealthcheckController(IHealthcheckService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        Optional<HealthStatus> healthStatus = new HealthcheckService().getStatus("full");
        healthStatus.ifPresent(System.out::println);

    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<HealthStatus> healthcheck(@RequestParam("format") String format) {
        Optional<HealthStatus> healthStatus = this.service.getStatus(format);
        return healthStatus.map(status -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(status)).orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @PutMapping(value = "/healthcheck")
    public ResponseEntity<Object> healthcheckPut() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @PostMapping(value = "/healthcheck")
    public ResponseEntity<Object> healthcheckPost() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }


    @DeleteMapping(value = "/healthcheck")
    public ResponseEntity<Object> healthcheckDelete() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

}

interface IHealthcheckService {
    Optional<HealthStatus> getStatus(String format);
}

@Service
class HealthcheckService implements IHealthcheckService {
    private static final String SHORT = "short";
    private static final String FULL = "full";

    public Optional<HealthStatus> getStatus(String format) {
        if (SHORT.equals(format)) {
            return Optional.of(new HealthStatus(Status.OK.name()));
        } else if (FULL.equals(format)) {
            return Optional.of(new HealthStatus(
                    Status.OK.name(),
                    ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            ));
        } else {
            return Optional.empty();
        }
    }
}

class HealthStatus {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currentTime;

    public HealthStatus(String status) {
        this.status = status;
    }

    public HealthStatus(String status, String currentTime) {
        this.status = status;
        this.currentTime = currentTime;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        return "HealthStatus{" +
                "status='" + status + '\'' +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}

enum Status {
    OK();
}
