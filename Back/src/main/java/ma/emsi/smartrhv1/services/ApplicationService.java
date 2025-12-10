package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.exception.ResourceNotFoundException;
import ma.emsi.smartrhv1.model.Application;
import ma.emsi.smartrhv1.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Optional<Application> findById(String id) {
        return applicationRepository.findById(id);
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public Application update(String id, Application applicationDetails) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found: " + id));
        existing.setApplicantName(applicationDetails.getApplicantName());
        existing.setApplicantEmail(applicationDetails.getApplicantEmail());
        existing.setCvLink(applicationDetails.getCvLink());
        existing.setJobId(applicationDetails.getJobId());
        return applicationRepository.save(existing);
    }

    public void delete(String id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application not found: " + id);
        }
        applicationRepository.deleteById(id);
    }
}
