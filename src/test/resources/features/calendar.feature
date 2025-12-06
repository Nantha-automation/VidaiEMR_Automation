@regression @calendar
Feature: Calendar

  @UiCheck
  Scenario: Verify all elements are visible on the Calendar Page
    Given User is on the Calendar Page
    Then All required UI elements should be displayed on the Calendar Page

  @defaultSelection
  Scenario: Verify default selections on the Calendar Page
    Given User is on the Calendar Page
    Then The Admin_Clinic_Month_MonthNameAndYear should be selected by default

  @bookAppointment
  Scenario: Verify user can book appointment to existing patient for all tabs from JSON test data
    Given User is on the Calendar Page
    When User books appointment for "Semen Collection" tab from JSON test data
    When User books appointment for "Pathology" tab from JSON test data
    When User books appointment for "Admin" tab from JSON test data
    When User books appointment for "Consultation" tab from JSON test data
    When User books appointment for "Ultrasound" tab from JSON test data
    When User books appointment for "Surgery" tab from JSON test data
    Then Appointments should be booked successfully and verified on Calendar Page