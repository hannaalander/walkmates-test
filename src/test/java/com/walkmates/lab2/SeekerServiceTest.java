package com.walkmates.lab2;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.walkmates.model.Seeker;
import com.walkmates.repository.SeekerRepository;
import com.walkmates.service.PaymentService;
import com.walkmates.service.SeekerService;


@ExtendWith (MockitoExtension.class)
public class SeekerServiceTest {
    
    @Mock 
    private SeekerRepository seekerRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks SeekerService seekerService;

    @Nested
    @DisplayName ("topUp tests")
    class TopUpTests {
        private Seeker seeker;
        private final String seekerId = "seeker-1";

        @BeforeEach 
        void setUp() {
            seeker = new Seeker("john.doe@example.com","John Doe", "0701234567");
            seeker.addFunds(100.0);
            when(seekerRepository.findById(seekerId)).thenReturn(Optional.of(seeker));
        }

        @Test
        @DisplayName ("topUp succeeds when payment is successful and wallet is credited")
        void topUpSuccess() throws PaymentService.PaymentException {
            double amount = 50.0;
            String paymentMethodId = "pm-1";
            
            Seeker existingSeeker = new Seeker("john.doe@example.com","John Doe", "0701234567");
            existingSeeker.addFunds(100.0);

            when(seekerRepository.findById(seekerId)).thenReturn(Optional.of(existingSeeker));
            when(paymentService.charge(anyString(), anyString(), anyDouble())).thenReturn("confirmation-123");
            when(seekerRepository.save(any(Seeker.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Seeker updatedSeeker = seekerService.topUp(seekerId, paymentMethodId, amount);

            assertThat(updatedSeeker).isNotNull();
            assertThat(updatedSeeker.getBalance()).isEqualTo(150.0);
            verify(seekerRepository).save(updatedSeeker);
        }

        @Test 
        @DisplayName ("topUp fails when payment is declined and wallet is not credited")
        void topUpPaymentDeclined() throws PaymentService.PaymentException {
            double amount = 50.0;
            String paymentMethodId = "pm-1";

            // Mock the payment service to throw a PaymentException
            when(paymentService.charge(anyString(), anyString(), anyDouble()))
                .thenThrow(new PaymentService.PaymentException("Payment declined"));

            try {
                seekerService.topUp(seekerId, paymentMethodId, amount);
                assert(false); // Should not reach here
            } catch (PaymentService.PaymentException e) {
                // Expected exception
                assert(e.getMessage().equals("Payment declined"));
            }

            // Verify that the wallet was not credited
            assert(seeker.getBalance() == 100.0);
        }

        @Test 
        @DisplayName ("Timeout during payment does not credit wallet")
        void topUpTimeout() throws PaymentService.PaymentException {
            double amount = 50.0;
            String paymentMethodId = "pm-1";

            // Mock the payment service to throw a PaymentException
            when(paymentService.charge(anyString(), anyString(), anyDouble()))
                .thenThrow(new PaymentService.PaymentException("Payment timeout"));

            try {
                seekerService.topUp(seekerId, paymentMethodId, amount);
                assert(false); // Should not reach here
            } catch (PaymentService.PaymentException e) {
                // Expected exception
                assert(e.getMessage().equals("Payment timeout"));
            }

            // Verify that the wallet was not credited
            assert(seeker.getBalance() == 100.0);
        }
    }

}

