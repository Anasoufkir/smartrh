package ma.emsi.smartrhv1.services;

import ma.emsi.smartrhv1.exception.ResourceNotFoundException;
import ma.emsi.smartrhv1.model.JobOffer;
import ma.emsi.smartrhv1.repository.JobOfferRepository;
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
class JobOfferServiceTest {

    @Mock
    private JobOfferRepository jobOfferRepository;

    @InjectMocks
    private JobOfferService jobOfferService;

    private JobOffer buildOffer(String id, String title) {
        JobOffer o = new JobOffer();
        o.setId(id);
        o.setTitle(title);
        o.setCompany("Acme");
        o.setLocation("Paris");
        return o;
    }

    @Test
    void findAll_returnsList() {
        when(jobOfferRepository.findAll()).thenReturn(List.of(buildOffer("1", "Dev Java")));
        assertThat(jobOfferService.findAll()).hasSize(1);
    }

    @Test
    void findById_existing_returnsOffer() {
        JobOffer offer = buildOffer("1", "Dev Java");
        when(jobOfferRepository.findById("1")).thenReturn(Optional.of(offer));
        assertThat(jobOfferService.findById("1")).contains(offer);
    }

    @Test
    void findById_missing_returnsEmpty() {
        when(jobOfferRepository.findById("x")).thenReturn(Optional.empty());
        assertThat(jobOfferService.findById("x")).isEmpty();
    }

    @Test
    void save_persistsAndReturns() {
        JobOffer offer = buildOffer(null, "Dev Java");
        when(jobOfferRepository.save(offer)).thenReturn(offer);
        assertThat(jobOfferService.save(offer)).isSameAs(offer);
    }

    @Test
    void update_existing_updatesFields() {
        JobOffer existing = buildOffer("1", "Old Title");
        JobOffer details = buildOffer(null, "New Title");
        details.setCompany("NewCo");
        details.setLocation("Lyon");
        details.setDescription("Updated desc");

        when(jobOfferRepository.findById("1")).thenReturn(Optional.of(existing));
        when(jobOfferRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        JobOffer result = jobOfferService.update("1", details);

        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getCompany()).isEqualTo("NewCo");
    }

    @Test
    void update_missing_throwsException() {
        when(jobOfferRepository.findById("x")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> jobOfferService.update("x", new JobOffer()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_existing_callsRepository() {
        when(jobOfferRepository.existsById("1")).thenReturn(true);
        jobOfferService.delete("1");
        verify(jobOfferRepository).deleteById("1");
    }

    @Test
    void delete_missing_throwsException() {
        when(jobOfferRepository.existsById("x")).thenReturn(false);
        assertThatThrownBy(() -> jobOfferService.delete("x"))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
