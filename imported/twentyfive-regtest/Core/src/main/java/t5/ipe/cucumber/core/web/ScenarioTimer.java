package t5.ipe.cucumber.core.web;
import java.time.Instant;

public class ScenarioTimer {

    private Instant startTime;
    private Instant endTime;

    // call in @Before
    public void start() {
        startTime = Instant.now();
    }

    // call in @After
    public void end() {
        endTime = Instant.now();
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public long getDurationMillis() {
        if (startTime != null && endTime != null) {
            return endTime.toEpochMilli() - startTime.toEpochMilli();
        }
        return 0;
    }
}
