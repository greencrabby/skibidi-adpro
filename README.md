# skibidi-adpro

# ---Reflection 1---
During this first exercise, I found that there are important things that need to be kept in mind to be able to write clean code principles and have secure coding practices.
- Meaningful variable and function names : This helps me figure out what each variable or function is meant to do and it gives me a clear idea on when and where I should use said variable or function so the code is less confusing and more clean for me to understand.
- Implementing functions: Similar to meaningful variable and function names, it gives me the idea on what the function is supposed to do and what said function should do and when and where I should use it even though its only used once, its still good practice so that its more easily understandable what a part of code should do.
- Only keeping important parts of code: I found that during the first exercise, I overcomplicated some parts of the code and managed to make it simpler so its alot easier for me to understand and the code doesn't mog me with the complexity of it.

I found that I had some problems with the original version of my code before I pushed it to github and that being some wrong implementations such as forgetting to update my product model so that it generates with a product Id everytime. Minor mistakes like these are easy to miss but also easy to resolve and so I also learned that always being aware of the potential problems when implementing features is important even during planning of implementing said feature.

# ---Reflection 2---
After writing the unit test, I found that my previous sentiments of feeling that said unit test is pretty useless and sometimes even more time consuming than regular testing are proven to be more true. As testing this without unit test would feel quite slower as the size of the code is massive compared to the usual codes from previous courses such as from data structures and algorithms where testing the code is kind of better doing it manually since how short the code is. However with this code it's the complete opposite, and with unit testing with code of this size, it's also a lot easier to maximize the code coverage, and I feel like my unit testing has successfully covered 100% of the code with the tests so there shouldn't be any errors or L aura if the code were to be used officially. 

Now if I were to suppose that I was asked to create another functional test suite that verifies the number of items in the product list where I decide to make a new Java class similar to the prior functional test suites with the same setup, it would reduce the code quality. This is caused by the main reason being code duplication. Each test class will contain the identical setup code and any changes will require updates in multiple places (for example changing baseUrl logic) which increases the maintenance effort and risk of inconsistencies across test suites. Therefore its better to extract a common setup logic into a base class for all functional tests.

# ---Reflection 3---
For this week's exercise I only really had to solve only 1 quality issue within my code and that is this small cutout right here:
```java
package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("")
    // Before
    public String HomePage(){
        return "HomePage";
    }
    
    // After
    public String homePage(){
        return "HomePage";
}
```
Here I simply fixed the name of the function to be more inline with how java naming conventions is supposed to go so its more of a banger and this was also the only issue sonarcloud found with my code aswell.

I believe that the CI/CD workflow I added to my GitHub repository aligns with the definitions of Continuous Integration and Continuous Deployment. According to the definition provided by Swaraj, Continuous Integration involves the continuous and automated integration and verification of code changes and updates, facilitated by build scripts and tools. In this case, the files `ci.yml`, `sonarqube.yml`, and `scorecard.yml` serve as the build scripts, while Gradle, SonarCloud, and Scorecard function as the tools to ensure that the code I designed is free from issues.

Furthermore, I have implemented Continuous Deployment by using the pull-based PaaS Koyeb. Koyeb automatically pulls the latest changes from my repository whenever there is a new update and redeploys the application without requiring any intervention from the developer. This ensures that the web application stays up-to-date seamlessly and efficiently.

