package example.l5d.helloservice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class DefaultResponse {
    private String serviceName;
    private String nodeName;
    private String podIp;
    private String httpProxy;
    private int latency;
    private DefaultResponse downstreamResponse;
    private String message;
}
