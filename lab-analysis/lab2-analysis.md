## Part A — Structural & coverage (M3)

### Activity 3.1 — Measure baseline coverage
The initial test only covers the basic feature of the PricingCalculator, and doesn't test any of the methods that deal with "edge-cases" like free listings, overnight surcharge and if any of the required parameters are null. 

### Activity 3.2 — Raise branch coverage
We added tests to cover free listings and overnight surcharge, both of which resulted in successful builds.

### Activity 3.3 — Coverage ≠ correctness
The test for excatly 480 minutes resulted in a failure with the following output: 
[ERROR] Failures: 
[ERROR]   PricingCalculatorStructuralTest.exactly480MinBookingHasNoOvernightSurcharge:89 
expected: 716.8
but was: 860.16
[INFO] 
[ERROR] Tests run: 4, Failures: 1, Errors: 0, Skipped: 0

This is because of a >= on line 49 of the PricingCalculator, where there should be just a >, causing a booking of the max amount of time to be assigned as an Overnight booking when it shouldn't be.


## Part B — Test optimization (M4)

### Activity 4.1 — Mutation testing (PIT)
We ran the PIT-test successfully and the coverage on PricingCalculator was all green. 

### Activity 4.2 — Component isolation with mocking


### Activity 4.3 — Regression selection
As the change mostly affects PricingCalculator, we decided that the only *exsisting* test that need running are the following:

1. "SHELTER_VOLUNTEER listing always costs 0.00 regardless of duration or seeker tier"
2. "600 min booking includes 20% overnight surcharge (> 480 min)"
3. "Exactly 480 min booking must NOT include the 20% overnight surcharge (FR-4.3: strictly > 480)"

This is to ensure the code affected by the change still works as inteded. However, we would need to write new tests to achieve sufficient coverage as these tests don't apply directly to the new weekend surcharge path. The priority is justified by how it affects the business, in that volunteering remaining free is, in our opinion, most important. Followed by the overnight surcharge as it is likely to affect significantly more bookings than the final test of exactly 480 min booking. 

