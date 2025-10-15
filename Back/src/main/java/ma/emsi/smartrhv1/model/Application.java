package ma.emsi.smartrhv1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document
public class Application {
    @Id
    private String id;
    private String applicantName;
    private String applicantEmail;
    private String cvLink;
    private String jobId;
}
