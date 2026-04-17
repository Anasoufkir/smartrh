package ma.emsi.smartrhv1.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "applications")
public class Application {

    public enum Status {
        PENDING, REVIEWING, INTERVIEW, ACCEPTED, REJECTED
    }

    @Id
    private String id;

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @NotBlank(message = "Applicant email is required")
    @Email(message = "Invalid email format")
    private String applicantEmail;

    private String cvLink;

    @NotBlank(message = "Job ID is required")
    private String jobId;

    private Status status = Status.PENDING;
}
