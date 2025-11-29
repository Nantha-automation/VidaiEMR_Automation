@regression @registration
Feature: Register

Background:
    Given The user is on the Login page
    When The user enters correct username and password
    And The user clicks login button
    Then Verify that Logged in as username is visible

  @register
  Scenario: Verify all elements are visible on the Registration Page
    Given User is on the Registration Page
    Then All required UI elements should be displayed
    And  SearchBy radio options should be present as per json test data

  @register
  Scenario: Verify user can register a new patient using patient-couple registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the Patient-Couple registration type from JSON test data
    When User enters patient details from JSON test data
    And User clicks the Save and Next button
    And User enters partner details from JSON test data
    And User clicks the Register button on the Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The patient registered successfully message should be displayed
    And User searches for the newly registered patient by name from JSON test data


  