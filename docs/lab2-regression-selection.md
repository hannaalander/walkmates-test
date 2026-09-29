### Activity 4.3 — Regression selection
As the change mostly affects PricingCalculator, we decided that the only *exsisting* test that need running are the following:

1. "SHELTER_VOLUNTEER listing always costs 0.00 regardless of duration or seeker tier"
2. "600 min booking includes 20% overnight surcharge (> 480 min)"
3. "Exactly 480 min booking must NOT include the 20% overnight surcharge (FR-4.3: strictly > 480)"

This is to ensure the code affected by the change still works as inteded. However, we would need to write new tests to achieve sufficient coverage as these tests don't apply directly to the new weekend surcharge path. The priority is justified by how it affects the business, in that volunteering remaining free is, in our opinion, most important. Followed by the overnight surcharge as it is likely to affect significantly more bookings than the final test of exactly 480 min booking. 