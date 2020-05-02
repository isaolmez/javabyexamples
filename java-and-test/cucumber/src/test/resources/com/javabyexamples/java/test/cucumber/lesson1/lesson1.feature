Feature: Scenario with multiple scenarios.
  Also shows the usage of Background which is run for each defined scenario
  Also shows AND for logical and operation
  Also shows BUT for logical or operation

#This is how background can be used to eliminate duplicate steps
  Background: User navigates to Facebook
    Given I am on Facebook login page

#Scenario with AND
  Scenario: Login should fail
    When I enter username as "john"
    And I enter password as "doe"
    Then Login should fail

#Scenario with BUT
  Scenario: Login should fail again
    When I enter username as "jane"
    And I enter password as "doe"
    Then Login should fail
    But Relogin option should be available