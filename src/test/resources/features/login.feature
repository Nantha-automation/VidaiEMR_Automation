@regression @login
Feature: Login

Background:
    Given The user is on the Login page
  
  @smoke @login1
  Scenario: Login User with correct email and password
    When The user enters correct username and password
    And The user clicks login button
    Then Verify that Logged in as username is visible
    When The user clicks Logout menu button
    Then Verify that user is navigated to login page

  @smoke @login2
  Scenario: Login User with incorrect email and password
    When The user enters incorrect username and password
    And The user clicks login button
    Then Verify error Invalid credentials is visible

  