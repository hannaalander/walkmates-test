package com.walkmates.lab2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.walkmates.model.Booking;
import com.walkmates.model.Listing;
import com.walkmates.model.ListingType;
import com.walkmates.model.Seeker;
import com.walkmates.model.TrustTier;
import com.walkmates.service.PricingCalculator;

/**
 * Lab 2, Part A — structural testing for {@link PricingCalculator} (FR-4.3).
 *
 * <p>Run coverage with {@code mvn clean test jacoco:report} and open
 * {@code target/site/jacoco/index.html}. Find the uncovered branches and add tests to reach
 * them — then look hard at the <em>overnight surcharge boundary</em>: there is a path that your
 * happy-path test "covers" but does not actually check (coverage ≠ correctness).</p>
 */
class PricingCalculatorStructuralTest {

    private final PricingCalculator pricing = new PricingCalculator();

    private Seeker seeker(TrustTier tier) {
        Seeker s = new Seeker("p@example.com", "Pat", "0701112233");
        s.setTrustTier(tier);
        return s;
    }

    private Listing listing(ListingType type) {
        return new Listing("provider-1", "A listing", "desc", type);
    }

    // ---- Worked example: a short standard walk, no overnight surcharge ----
    @Test
    @DisplayName("60 min DOG_WALK for a VERIFIED seeker = 80 base + 12% fee = 89.60")
    void shortWalkPrice() {
        Booking booking = new Booking("seeker-1", "listing-1", 60);

        double price = pricing.priceFor(booking, listing(ListingType.DOG_WALK), seeker(TrustTier.VERIFIED));

        assertThat(price).isEqualTo(89.60);
    }

    // TODO (branch): a free SHELTER_VOLUNTEER listing always costs 0.00.
    // TODO (branch): a clearly-overnight booking (e.g. 600 min) includes the 20% surcharge.
    // TODO (BOUNDARY — this is the interesting one): a booking of exactly 480 minutes must NOT
    //      be surcharged (FR-4.3 says strictly > 480). Write this test and see what happens.

    // ---- Activity 3.2: Raise branch coverage ----

    @Test
    @DisplayName("SHELTER_VOLUNTEER listing always costs 0.00 regardless of duration or seeker tier")
    void shelterVolunteerIsFree() {
        Booking booking = new Booking("seeker-1", "listing-1", 120);

        double price = pricing.priceFor(booking, listing(ListingType.SHELTER_VOLUNTEER), seeker(TrustTier.NEW));

        assertThat(price).isEqualTo(0.00);
    }

    @Test
    @DisplayName("600 min booking includes 20% overnight surcharge (> 480 min)")
    void clearlyOvernightBookingIncludesSurcharge() {
        // Base rate = 80 per hour (600 min = 10 hours -> 800 base)
        // Surcharge = +20% -> 800 * 1.20 = 960
        // Service fee for VERIFIED (12%) -> 960 * 1.12 = 1075.20
        Booking booking = new Booking("seeker-1", "listing-1", 600);

        double price = pricing.priceFor(booking, listing(ListingType.DOG_WALK), seeker(TrustTier.VERIFIED));

        assertThat(price).isEqualTo(1075.20);
    }

    // ---- Activity 3.3: Boundary test (480 minutes) ----

    @Test
    @DisplayName("Exactly 480 min booking must NOT include the 20% overnight surcharge (FR-4.3: strictly > 480)")
    void exactly480MinBookingHasNoOvernightSurcharge() {
        // Base rate = 80 per hour (480 min = 8 hours -> 640 base)
        // No surcharge (strictly > 480) -> 640 base
        // Service fee for VERIFIED (12%) -> 640 * 1.12 = 716.80
        Booking booking = new Booking("seeker-1", "listing-1", 480);

        double price = pricing.priceFor(booking, listing(ListingType.DOG_WALK), seeker(TrustTier.VERIFIED));

        assertThat(price).isEqualTo(716.80);
    }

}
