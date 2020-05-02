Feature: Scenario with multiple hooks.

  Scenario: Login should fail
    Given I am on Facebook login page
    When I enter username as "john"
    And I enter password as "doe"
    Then Login should fail