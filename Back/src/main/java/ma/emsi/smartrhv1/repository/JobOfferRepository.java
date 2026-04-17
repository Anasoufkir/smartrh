package ma.emsi.smartrhv1.repository;

import ma.emsi.smartrhv1.model.JobOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String> {
    List<JobOffer> findByCompanyIgnoreCase(String company);
    List<JobOffer> findByLocation(String location);
    List<JobOffer> findByTitleContainingIgnoreCase(String title);

    @Query("{ $or: [ { title: { $regex: ?0, $options: 'i' } }, { description: { $regex: ?0, $options: 'i' } } ] }")
    List<JobOffer> searchByKeyword(String keyword);

    @Query("{ $and: [ { $or: [ { title: { $regex: ?0, $options: 'i' } }, { description: { $regex: ?0, $options: 'i' } } ] }, { location: { $regex: ?1, $options: 'i' } } ] }")
    List<JobOffer> searchByKeywordAndLocation(String keyword, String location);
}
