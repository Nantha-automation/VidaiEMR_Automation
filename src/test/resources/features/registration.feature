@regression @registration
Feature: Register

  @register
  Scenario: Verify all elements are visible on the Registration Page
    Given User is on the Registration Page
    Then All required UI elements should be displayed
    And  SearchBy radio options should be present as per json test data

  @register @patient
  Scenario: Verify user can register a new patient using patient-couple registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the "PATIENT_COUPLE" registration type from JSON test data
    When User enters patient details from JSON test data
    And User clicks the Save and Next button
    And User enters partner details from JSON test data
    And User clicks the Register button on the Patient_Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The patient registered successfully message should be displayed from "patientInformation" JSON node
    And User searches newly registered patient from "patientInformation" JSON node

  @register @patient @single
  Scenario: Verify user can register a new patient using patient-Single registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the "PATIENT_SINGLE" registration type from JSON test data
    When User enters patient-single details from JSON test data
    And User clicks the Register button on the Patient_Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The patient registered successfully message should be displayed from "patient-singleInformation" JSON node
    And User searches newly registered patient from "patient-singleInformation" JSON node

  @register @sperm_donor
  Scenario: Verify user can register a new patient using SPERM_DONOR registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the "SPERM_DONOR" registration type from JSON test data
    When User enters SPERM_DONOR patient details from JSON test data
    And User clicks the Register button on the Patient_Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The donor registered successfully message should be displayed
    And User clicks the Exit Patient button from the sidebar
    And User clicks on "sperm_donor" radio button on the Registration Page
    And User searches newly registered patient from "sperm_donorInformation" JSON node

  @register @OOCYTE_DONOR
  Scenario: Verify user can register a new patient using EGG_DONOR registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the "OOCYTE_DONOR" registration type from JSON test data
    When User enters EGG_DONOR patient details from JSON test data
    And User clicks the Register button on the Patient_Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The patient registered successfully message should be displayed from "egg_donorInformation" JSON node
    And User clicks the Exit Patient button from the sidebar
    And User clicks on "egg_donor" radio button on the Registration Page
    And User searches newly registered patient from "egg_donorInformation" JSON node

  @register @surrogate
  Scenario: Verify user can register a new patient using SURROGATE registration type
    Given User is on the Registration Page
    And User clicks the New Registration button
    And User selects the "SURROGATE" registration type from JSON test data
    When User enters SURROGATE patient details from JSON test data
    And User clicks the Register button on the Patient_Partner Details page
    And User confirms the registration by clicking Yes on the confirmation popup
    Then The patient registered successfully message should be displayed from "surrogateInformation" JSON node
    And User clicks the Exit Patient button from the sidebar
    And User clicks on "surrogate" radio button on the Registration Page
    And User searches newly registered patient from "surrogateInformation" JSON node
