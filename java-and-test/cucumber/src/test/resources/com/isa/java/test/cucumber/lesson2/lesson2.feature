Feature: Scenario outline test with "Examples" part

  Background: User navigates to Facebook
    Given I am on Facebook login page

    # Scenario outline defines the repeatable parameterized scenarios. These parts are run with given example values
  Scenario Outline: Login attempt
    When I enter username as "<username>" and password as "<password>"
    Then Login should fail
    Examples: # These are feed to the above scenario
      | username  | password  |
      | username1 | password1 |
      | username2 | password2 |