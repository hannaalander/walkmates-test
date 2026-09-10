package com.walkmates.lab1;

import com.walkmates.model.Seeker;
import com.walkmates.model.TrustTier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lab 1, Part B — specification-based tests for {@link Seeker}.
 *
 * <p>Design your tests on paper first (equivalence partitions, boundary values, decision table)
 * from {@code docs/REQUIREMENTS.md} FR-1.1 / FR-1.3 / FR-1.2, then implement them here. One
 * worked example is provided; the {@code TODO}s are yours.</p>
 */
class SeekerSpecBasedTest {

        // ---- Worked example: boundary value at the maximum single top-up (FR-1.3) ----
        @Test
        @DisplayName("Top-up exactly at the 5000 SEK single-transaction maximum is accepted")
        void topUpAtSingleMaximumIsAccepted() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");

            seeker.addFunds(Seeker.MAX_SINGLE_TOP_UP); // 5000.00, the boundary value

            assertThat(seeker.getBalance()).isEqualTo(Seeker.MAX_SINGLE_TOP_UP);
        }
        

        // TODO (EP): one valid + one invalid equivalence class for email, name, and phone (FR-1.1).
        @Test
        @DisplayName("Valid email, name, and phone are accepted at registration")
        void validEmailNamePhoneAreAccepted() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            assertThat(seeker).isNotNull();
        }
 
        @Test
        @DisplayName("Invalid email is rejected at registration")
        void invalidEmailIsRejected() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Seeker("not-an-email", "Sam", "0707654321"));
        }
        @Test
        @DisplayName("Invalid name is rejected at registration")
        void invalidNameIsRejected() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Seeker("sam@example.com", "Invalid Name That Is Too Looooooooooooooooong", "0707654321"));
        }
    
        @Test
        @DisplayName("Invalid phone is rejected at registration")
        void invalidPhoneIsRejected() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Seeker("sam@example.com", "Sam", "not-a-phone"));
        }
    
        // TODO (BVA): just-below / at / just-above the 10.00 minimum top-up (FR-1.3).
        @Test
        @DisplayName("Top-up just below the 10.00 minimum is rejected")
        void topUpJustBelowMinimumIsRejected() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            assertThrows(IllegalArgumentException.class,
                    () -> seeker.addFunds(9.99));
        }
        @Test
        @DisplayName("Top-up at the 10.00 minimum is accepted")
        void topUpAtMinimumIsAccepted() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.addFunds(10.00);
            assertThat(seeker.getBalance()).isEqualTo(10.00);
        }
        @Test
        @DisplayName("Top-up just above the 10.00 minimum is accepted")
        void topUpJustAboveMinimumIsAccepted() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.addFunds(10.01);
            assertThat(seeker.getBalance()).isEqualTo(10.01);
        }
    
        // TODO (BVA): a top-up that would push the balance above 20000.00 is rejected (FR-1.3).
        @Test
        @DisplayName("Top-up that would push the balance above 20000.00 is rejected")
        void topUpAboveMaximumIsRejected() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.addFunds(5000.00);
            seeker.addFunds(5000.00);
            seeker.addFunds(5000.00);
            seeker.addFunds(5000.00);
            assertThrows(IllegalArgumentException.class,
                    () -> seeker.addFunds(1.00));
        }
    
        // TODO (Decision table): expected fee + max-bookings for each trust tier (FR-1.2).
        @Test
        @DisplayName("Trust tier NEW has expected fee 15% and max bookings 1")
        void trustTierNewHasExpectedFeeAndMaxBookings() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            assertThat(seeker.getTrustTier()).isEqualTo(TrustTier.NEW);
            assertThat(seeker.getTrustTier().getPlatformFee()).isEqualTo(0.15);
            assertThat(seeker.getMaxConcurrentBookings()).isEqualTo(1);
        }
        @Test
        @DisplayName("Trust tier VERIFIED has expected fee 12% and max bookings 3")
        void trustTierVerifiedHasExpectedFeeAndMaxBookings() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.setTrustTier(TrustTier.VERIFIED);
            assertThat(seeker.getTrustTier()).isEqualTo(TrustTier.VERIFIED);
            assertThat(seeker.getTrustTier().getPlatformFee()).isEqualTo(0.12);
            assertThat(seeker.getMaxConcurrentBookings()).isEqualTo(3);
        }
        @Test
        @DisplayName("Trust tier TRUSTED has expected fee 8% and max bookings 5")
        void trustTierTrustedHasExpectedFeeAndMaxBookings() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.setTrustTier(TrustTier.TRUSTED);
            assertThat(seeker.getTrustTier()).isEqualTo(TrustTier.TRUSTED);
            assertThat(seeker.getTrustTier().getPlatformFee()).isEqualTo(0.08);
            assertThat(seeker.getMaxConcurrentBookings()).isEqualTo(5);
        }
        @Test
        @DisplayName("Trust tier PRO_SITTER has expected fee 5% and max bookings 10")
        void trustTierProSitterHasExpectedFeeAndMaxBookings() {
            Seeker seeker = new Seeker("sam@example.com", "Sam", "0707654321");
            seeker.setTrustTier(TrustTier.PRO_SITTER);
            assertThat(seeker.getTrustTier()).isEqualTo(TrustTier.PRO_SITTER);
            assertThat(seeker.getTrustTier().getPlatformFee()).isEqualTo(0.05);
            assertThat(seeker.getMaxConcurrentBookings()).isEqualTo(10);
        }
 
}
