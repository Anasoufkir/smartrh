package ma.emsi.smartrhv1.services;

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
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setApplicantName(applicationDetails.getApplicantName());
                    application.setApplicantEmail(applicationDetails.getApplicantEmail());
                    application.setCvLink(applicationDetails.getCvLink());
                    application.setJobId(applicationDetails.getJobId());
                    return applicationRepository.save(application);
                })
                .orElseGet(() -> {
                    applicationDetails.setId(id);
                    return applicationRepository.save(applicationDetails);
                });
    }

    public void delete(String id) {
        applicationRepository.deleteById(id);
    }

}
