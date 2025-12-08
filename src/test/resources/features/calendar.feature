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
    When User books appointment for "Admin" tab from JSON test data
    When User books appointment for "Semen Collection" tab from JSON test data
    When User books appointment for "Consultation" tab from JSON test data
    When User books appointment for "Ultrasound" tab from JSON test data
    When User books appointment for "Surgery" tab from JSON test data
    When User books appointment for "Pathology" tab from JSON test data

  @forceBookAppointment
  Scenario: Verify user can force book appointment by selecting a non-available doctor
    Given User is on the Calendar Page
    When User books appointment for "Semen Collection" tab from JSON test data with force booking option

  @rescheduleAppointment
  Scenario: Verify user can reschedule an existing appointment
    Given User is on the Calendar Page
    When User reschedules an existing appointment for "Semen Collection" tab from JSON test data
    Then The appointment should be rescheduled successfully message should be displayed

  @cancelAppointment
  Scenario: Verify user can cancel an existing appointment
    Given User is on the Calendar Page
    When User cancels an existing appointment for "Semen Collection" tab from JSON test data
    Then The appointment should be cancelled successfully message should be displayed

  @newAppointment
  Scenario: Verify that the user can book an appointment for a new patient and complete registration, check-in, and check-out process
    Given User is on the Calendar Page
    When User books appointment for a new patient for "Consultation" tab from JSON test data
    Then A success message should be displayed confirming that the appointment has been booked
    And The user opens the newly booked appointment from "Consultation" tab
    And Clicks on the Register button in the Patient information popup
    And Completes the registration for the new patient using JSON test data
    Then A success message should be displayed confirming that the registration has been completed
    And The user clicks on Exit Patient button from the sidebar
    And The user navigates back to the Calendar page
    And Opens the newly booked appointment again using "Consultation" tab
    And Clicks on the Check-In button in the patient information popup
    Then The patient status should be updated to "Checked-In" status in the patient information popup
    And User clicks on the Check-Out button in the patient information popup
    Then The patient status should be updated to "Checked-Out" status in the patient information popup