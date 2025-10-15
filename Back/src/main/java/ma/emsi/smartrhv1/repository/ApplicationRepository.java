package ma.emsi.smartrhv1.repository;

import ma.emsi.smartrhv1.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
    List<Application> findByApplicantEmail(String email);
    List<Application> findByJobId(String jobId);
}
