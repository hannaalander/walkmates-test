package com.walkmates.lab2;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.walkmates.model.Booking;
import com.walkmates.model.Listing;
import com.walkmates.model.ListingType;
import com.walkmates.model.Provider;
import com.walkmates.model.Seeker;
import com.walkmates.model.TrustTier;
import com.walkmates.repository.BookingRepository;
import com.walkmates.repository.ListingRepository;
import com.walkmates.repository.ProviderRepository;
import com.walkmates.repository.SeekerRepository;
import com.walkmates.service.BookingService;
import com.walkmates.service.NotificationService;
import com.walkmates.service.PricingCalculator;

@ExtendWith (MockitoExtension.class)
public class BookingServiceTest {
    
    @Mock 
    private BookingRepository bookingRepository;

    @Mock 
    private ListingRepository listingRepository;

    @Mock 
    private SeekerRepository seekerRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private NotificationService notificationService;

    @Mock 
    private PricingCalculator pricingCalculator;

    @InjectMocks BookingService bookingService;

    @Test 
    @DisplayName ("Verify that confirmation notification is sent after successful booking")
    void testBookingConfirmationNotification() {
        String seekerId = "seeker-1";
        String listingId = "listing-1";
        String providerId = "provider-1";
        
        Seeker seeker = new Seeker("john.doe@example.com","John Doe", "0701234567");
        seeker.setTrustTier(TrustTier.VERIFIED);
        seeker.addFunds(500.0);

        Listing listing = new Listing("provider-1", "A listing", "desc", ListingType.DOG_WALK);
        Provider provider = new Provider("provider-1", 1, 5.0);

        when(seekerRepository.findById(seekerId)).thenReturn(Optional.of(seeker));
        when(listingRepository.findById(listingId)).thenReturn(Optional.of(listing));
        when(providerRepository.findById(providerId)).thenReturn(Optional.of(provider));
        when(pricingCalculator.priceFor(any(), any(), any())).thenReturn(100.0);

        when(bookingRepository.findBySeekerId(seekerId)).thenReturn(Collections.emptyList());
        when(listingRepository.findByProviderId(providerId)).thenReturn(Collections.emptyList());

        bookingService.createBooking(seekerId, listingId, 60);

        verify(notificationService).sendBookingConfirmed(eq(seeker), any(Booking.class));
    }







}
