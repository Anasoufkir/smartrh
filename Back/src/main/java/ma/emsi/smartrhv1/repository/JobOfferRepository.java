package ma.emsi.smartrhv1.repository;

import ma.emsi.smartrhv1.model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String> {
    List<JobOffer> findByCompanyIgnoreCase(String company);
    List<JobOffer> findByLocation(String location);
    List<JobOffer> findByTitleContainingIgnoreCase(String title);
}
