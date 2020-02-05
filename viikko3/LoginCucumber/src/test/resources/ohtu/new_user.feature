Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new is selected
    When  user "eero" with password "salainen1" is created
    Then  system will respond with "new user registered"
      
  Scenario: creation fails with already taken username and valid password
    Given command new is selected
    When  user "pekka" with password "salainen1" is created
    Then  system will respond with "new user not registered"

  Scenario: creation fails with too short username and valid password
    Given command new is selected
    When  user "a" with password "salainen1" is created
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and too short password
    Given command new is selected
    When  user "eero" with password "1" is created
    Then  system will respond with "new user not registered"

  Scenario: creation fails with valid username and password long enough but consisting of only letters
    Given command new is selected
    When  user "eero" with password "salainenz" is created
    Then  system will respond with "new user not registered"

  Scenario: can login with successfully generated account
    Given command new is selected
    When  user "eero" with password "salainen1" is created
    Then  system will respond with "new user registered"
    When  command login is selected
    And   username "eero" and password "salainen1" are entered
    Then  system will respond with "logged in"
