package ma.emsi.smartrhv1.controller;

import ma.emsi.smartrhv1.model.JobOffer;
import ma.emsi.smartrhv1.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/job-offers")
@CrossOrigin(origins="*")
public class JobOfferController {

    @Autowired
    private JobOfferService jobOfferService;

    @GetMapping
    public ResponseEntity<List<JobOffer>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferService.findAll());
    }

    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOffer jobOffer) {
        return new ResponseEntity<>(jobOfferService.save(jobOffer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable String id) {
        return jobOfferService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOffer> updateJobOffer(@PathVariable String id, @RequestBody JobOffer jobOfferDetails) {
        return ResponseEntity.ok(jobOfferService.update(id, jobOfferDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable String id) {
        jobOfferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
