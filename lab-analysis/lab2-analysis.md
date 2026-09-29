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

