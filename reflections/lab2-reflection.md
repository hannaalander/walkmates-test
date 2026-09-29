**Lab:** 2
**Pair:** 5: Hanna Ålander, Anton Gotthardsson, Emma Hugod
**Repo commit/tag:** (link — Labs 1–3; write `N/A` for Lab 4)

---

### 1 & 2. What we did & what we found

4.1 -- We ran the pit-test and since we'd already changed the >= to a > in PricingCalculator during the previous lab, that part of the test came back with no surviving mutants. 

4.2 -- Initially we felt quite lost on where to start with mockito as it was a tool we'd never used before. Therefore we decided to ask gemeni for an introduction to mockito and how to use it for this task. With that as a starting point we did a bunch of not-so-educacted guesses as to how to write the tests for the given targets. After a few iterations and several red builds, we finally managed to correctly stub all the dependancies and successfully run the tests. 

### 3. AI use (be honest — it doesn't lower your grade)
As previously mentioned, we used Gemeni to give us an introduction to mockito. We then used that information to try and make tests ourselves, which turned out to be fairly difficult. The solution ended up being a mix of going over the code, attempting fixes and when necessary, posting the errors to Gemeni for help with the fix. Only 4.2 required AI assistance, both 4.1 and 4.3 were completed without it. 

### 4. Judgment
It was fairly tricky to judge the AI output in this task compared to the last one, as we had less of an understanding of the mockito tool to begin with. What we did to try and solve this was a lot of trial and error, while also cross-referencing the information from AI with other sources on best practices when using Mockito. 

### 5. What we'd test next
The natural next steps feel like adding tests to cover the new path added in 4.3 and also increasing coverage based on the results from the PIT-test in 4.1.

---