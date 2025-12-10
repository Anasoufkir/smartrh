package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.exception.ResourceNotFoundException;
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
        JobOffer existing = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found: " + id));
        existing.setTitle(jobOfferDetails.getTitle());
        existing.setCompany(jobOfferDetails.getCompany());
        existing.setLocation(jobOfferDetails.getLocation());
        existing.setDescription(jobOfferDetails.getDescription());
        return jobOfferRepository.save(existing);
    }

    public void delete(String id) {
        if (!jobOfferRepository.existsById(id)) {
            throw new ResourceNotFoundException("JobOffer not found: " + id);
        }
        jobOfferRepository.deleteById(id);
    }
}
