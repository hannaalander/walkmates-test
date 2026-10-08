### Trend - Flakiness
We have compared two sources, an academic text and a text from the developer-focused site medium.com. According to both texts, a flaky test is a test that shows different results even though it's the same code and data. These usually occur due to a variety of reasons, most commonly asynchronous processes, hardcoded delays/waits or dependencies interacting with the program in unexpected ways. 

The major difference between the two outlooks on this issue is whether to see it purely as a problem or not. The academic source encourages readers to use flakiness as a diagnostic tool while the practitioner source mainly views it as a problem to be solved. 

https://medium.com/@alexraii/what-is-a-flaky-test-causes-impacts-how-to-deal-with-them-38edd483b76e

https://www.researchgate.net/publication/408935876_Flaky_Tests_as_a_Diagnostic_Tool_A_Conceptual_Model_for_Early_Detection_of_Systemic_Problems_in_Infrastructure_and_Test_Quality


### Justification

In the test --explainMatch ensures prompt injection is contained--, we have added an assertion that isn't stritcly necessary for the test to pass or fail. It does however ensure that the first part of the test functions as intended, thus reducing the likelihood of confusing or misleading test results. This follows the recommendation of the academic source to reduse unstable test data in order to avoid flaky tests. 
