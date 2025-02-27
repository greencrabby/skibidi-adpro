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

# ---Reflection 4---
For the S.O.L.I.D principles here are the list along with explanations on whether it has been implemented or not:
- Single Responsibility Principle (SRP): I split the CarController from the ProductController, previously combined in one file. Now, each file (CarController and ProductController) serves a single purpose: managing either cars or products, respecting SRP.
- Open Closed Principle (OCP): Currently, the project does not adhere to OCP. It lacks flexibility for extension without modifying existing classes. Future extensions would require overriding methods that aren't used, limiting its open-for-extension capability.
- Liskov Substitution Principle (LSP): LSP isn't applicable in my project as there's no inheritance hierarchy between products and cars. Each type operates independently without a subclass relationship, thus not utilizing LSP.
- Interface Segregation Principle (ISP): Implemented ISP by creating separate interfaces for CarService and ProductService. This modularizes code, ensuring that CarServiceImpl only implements CarService and ProductServiceImple only implements ProductService, preventing unnecessary method implementation.
- Dependency Inversion Principle (DIP): DIP is applied through interfaces for CarService and ProductService, separating them from concrete implementations. This modular design ensures independence between service layers without interdependencies outside their respective interfaces.

Using the SOLID principles in a project enhances code maintainability, scalability, and flexibility. The Single Responsibility Principle (SRP) ensures that each class has a distinct purpose, making debugging and modifications easier. For example, instead of having a single `ProductController` handling both car and product-related logic, separating it into `CarController` and `ProductController` makes each class more focused and easier to manage. Open Closed Principle (OCP) allows for extending functionality without altering existing code, reducing the risk of introducing new bugs. If a new type of product needs to be added, an abstract `Product` class with separate `Car` classes allows expansion without modifying existing product logic. Liskov Substitution Principle (LSP) ensures that subclasses can seamlessly replace their parent classes, leading to reliable behavior. For instance, if `X` extends `Car`, it should still behave like the `Car` class without breaking functionality. Interface Segregation Principle (ISP) prevents unnecessary dependencies by ensuring classes only implement relevant methods. Instead of a single `Service` interface forcing both `CarServiceImpl` and `ProductServiceImpl` to implement unrelated methods, separate `CarService` and `ProductService` interfaces keep the code modular and focused. Finally, Dependency Inversion Principle (DIP) promotes the use of abstractions over concrete implementations, making the system more flexible. For example, by injecting a `CarService` interface rather than a concrete `CarServiceImpl` directly into `CarController`, switching service implementations becomes easier. Together, these principles create a scalable and maintainable codebase, reducing technical debt and improving long-term project sustainability.

Not following the SOLID principles can result in a rigid, error-prone, and difficult-to-maintain codebase. Without the Single Responsibility Principle (SRP), classes can become overly complex and harder to debug. For example, if a `ProductController` handles both car and product-related operations, making changes to one might unintentionally affect the other. Ignoring the Open Closed Principle (OCP) means that adding a new product type requires modifying the existing `Product` class, which increases the likelihood of breaking existing functionality. Without the Liskov Substitution Principle (LSP), subclass replacements may lead to unexpected errors. For instance, if an `X` subclass overrides a method, it could cause runtime issues when used interchangeably with `Car` class. Failing to apply the Interface Segregation Principle (ISP) forces classes to implement unnecessary methods. If a `Service` interface includes methods for both `CarServiceImpl` and `ProductServiceImpl`, classes may be forced to implement methods they don’t actually need, leading to redundant code. Lastly, neglecting the Dependency Inversion Principle (DIP) results in tight coupling between components. If `CarController` directly depends on `CarServiceImpl` instead of an interface, swapping implementations (e.g., replacing a local database service with an API-based service) becomes difficult. Overall, neglecting these principles leads to an inflexible, hard-to-maintain system that increases development costs and technical debt over time.