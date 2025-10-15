package ma.emsi.smartrhv1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document
public class JobOffer {
    @Id
    private String id;
    private String title;
    private String company;
    private String location;
    private String description;
}
