Feature: Scenario with tags. These tags can be specified in test runner to run some scenarios.

  Background: User navigates to the Facebook login page
    Given I am on Facebook login page

  @HappyPath
  Scenario: Successful login
    When I enter username as "john" and password as "doe"
    Then Login should succeed

  @UnhappyPath
  Scenario: Unsuccesful login
    When I enter username as "jane" and password as "doe"
    Then Login should fail