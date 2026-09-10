## Part A — Fundamentals (M1)

### Activity 1.1 — Quality-attribute analysis (ISO/IEC 25010)

**FR-1.4 Swedish identity (optional verification):**
- Risk identification, if a user inserts their social security number, will the number still be in the HTML if the website is inspected then it is a huge risk for the user.
- Confidentiality is important to check so that the users social security number is safely stored in the database, only authorized people can access the data.

A testable requirement for Swedish identity (risk identification):
Can social security number be accessed through the HTML after it has been submitted? 
Social security numbers should not be accessible via HTML after they have been submitted.

**FR-2.1 Properties:**
- Functional correctness, to make sure that every provider has the correct data and attributes.
- User error protection, to make sure that the provider can only enter correct values and is made aware of what these values are.

A testable requirement for Properties (functional correctness):
A user who is the provider need to have correct data: a unique id, a name, a location and a capacity between 1-20.

**FR-4.1 Duration:**
- Functional correctness because a user should not be able to insert incorrect values.
- User error protection so that if a user inserts incorrect values, they will get an error messages so they know what is a correct value and how to solve it.

A testable requirement for Duration (user error protection):
Can a user insert a value outside the given boundaries?
A user should only be able to insert a value between the given boundaries.


### Activity 1.2 — Bug analysis (error → fault → failure)
1. **Human error**
The developer missed to add an "=" in the Rule 2 implementation of BookingService (src/main/java/service/BookingService.java). Probably also missed to test it.

**Fault**
There is a "=" missing in the BookingService.

**Failure**
The failure that the user will see, should be just another booking confirmation INSTEAD of the intended error message/exception ("Seeker booking limit reached for tier " + seeker.getTrustTier()).

2. The code currently states that if the seeker's number of bookings is MORE than the max allowed number (in this example more than 1) it will be accepted. There should be a ">="  instead of a ">", so that if the new seeker has the maximum amount of current bookings, the new booking will be rejected as per the rule in FR  4.2.

3. It should have been caught at the unit test-level. During AAA-testing of the methods in BookingService.


### Activity 1.3 — Your first test (day-one win)
Confirmation that `mvn test -Dtest=BeginnerFirstTest` became green and Build Success showed.