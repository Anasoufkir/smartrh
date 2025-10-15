package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.model.JobOffer;
import ma.emsi.smartrhv1.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    public Optional<JobOffer> findById(String id) {
        return jobOfferRepository.findById(id);
    }

    public JobOffer save(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer update(String id, JobOffer jobOfferDetails) {
        return jobOfferRepository.findById(id)
                .map(jobOffer -> {
                    jobOffer.setTitle(jobOfferDetails.getTitle());
                    jobOffer.setCompany(jobOfferDetails.getCompany());
                    jobOffer.setLocation(jobOfferDetails.getLocation());
                    jobOffer.setDescription(jobOfferDetails.getDescription());
                    return jobOfferRepository.save(jobOffer);
                })
                .orElseGet(() -> {
                    jobOfferDetails.setId(id);
                    return jobOfferRepository.save(jobOfferDetails);
                });
    }

    public void delete(String id) {
        jobOfferRepository.deleteById(id);
    }

}
