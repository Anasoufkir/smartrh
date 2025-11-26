package ma.emsi.smartrhv1.controller;

import jakarta.validation.Valid;
import ma.emsi.smartrhv1.model.Application;
import ma.emsi.smartrhv1.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Application> submitApplication(@Valid @RequestBody Application application) {
        return new ResponseEntity<>(applicationService.save(application), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable String id) {
        return applicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable String id,
                                                          @Valid @RequestBody Application applicationDetails) {
        return ResponseEntity.ok(applicationService.update(id, applicationDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
