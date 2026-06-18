package t5.ipe.cucumber.core.web;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
//import com.influxdb.client.write.WriteApiBlocking;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ScenarioMetrics {

    private long startTime;
    private String jobName;
    private String scenarioName;
    private String testName;
    private String browser;
    private String os;

    // Store initial tags and start time
    public ScenarioMetrics(String jobName, String scenarioName, String testName, String browser, String os) {
        this.jobName = jobName;
        this.scenarioName = scenarioName;
        this.testName = testName;
        this.browser = browser;
        this.os = os;
        this.startTime = System.currentTimeMillis();
    }

    // Getters
    public String getJobName() { return jobName; }
    public String getScenarioName() { return scenarioName; }
    public String getTestName() { return testName; }
    public String getBrowser() { return browser; }
    public String getOs() { return os; }
    // Build a Point object for InfluxDB
    public Point buildPoint(long endTime, String status) {
        long duration = endTime - startTime;

        Map<String, String> tags = new HashMap<>();
        tags.put("job_name", jobName);
        tags.put("scenario", scenarioName);
        tags.put("test_name", testName);
        tags.put("browser", browser);
        tags.put("os", os);

        return Point.measurement("test_execution")
                .addTags(tags)
                .addField("start_time", startTime)
                .addField("end_time", endTime)
                .addField("duration_ms", duration)
                .addField("status", status)
                .time(Instant.now(), WritePrecision.MS);
    }

    public long getStartTime() {
        return startTime;
    }
}
