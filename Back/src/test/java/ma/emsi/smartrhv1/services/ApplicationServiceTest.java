package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.exception.ResourceNotFoundException;
import ma.emsi.smartrhv1.model.Application;
import ma.emsi.smartrhv1.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    private Application buildApplication(String id) {
        Application a = new Application();
        a.setId(id);
        a.setApplicantName("Alice");
        a.setApplicantEmail("alice@example.com");
        a.setJobId("job-1");
        return a;
    }

    @Test
    void findAll_returnsList() {
        when(applicationRepository.findAll()).thenReturn(List.of(buildApplication("1")));
        assertThat(applicationService.findAll()).hasSize(1);
    }

    @Test
    void findById_existing_returnsApplication() {
        Application app = buildApplication("1");
        when(applicationRepository.findById("1")).thenReturn(Optional.of(app));
        assertThat(applicationService.findById("1")).contains(app);
    }

    @Test
    void save_persistsAndReturns() {
        Application app = buildApplication(null);
        when(applicationRepository.save(app)).thenReturn(app);
        assertThat(applicationService.save(app)).isSameAs(app);
    }

    @Test
    void update_existing_updatesFields() {
        Application existing = buildApplication("1");
        Application details = new Application();
        details.setApplicantName("Bob");
        details.setApplicantEmail("bob@example.com");
        details.setCvLink("https://cv.example.com/bob.pdf");
        details.setJobId("job-2");

        when(applicationRepository.findById("1")).thenReturn(Optional.of(existing));
        when(applicationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Application result = applicationService.update("1", details);

        assertThat(result.getApplicantName()).isEqualTo("Bob");
        assertThat(result.getApplicantEmail()).isEqualTo("bob@example.com");
        assertThat(result.getJobId()).isEqualTo("job-2");
    }

    @Test
    void update_missing_throwsException() {
        when(applicationRepository.findById("x")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> applicationService.update("x", new Application()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_existing_callsRepository() {
        when(applicationRepository.existsById("1")).thenReturn(true);
        applicationService.delete("1");
        verify(applicationRepository).deleteById("1");
    }

    @Test
    void delete_missing_throwsException() {
        when(applicationRepository.existsById("x")).thenReturn(false);
        assertThatThrownBy(() -> applicationService.delete("x"))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
