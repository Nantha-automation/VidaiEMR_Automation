package com.EMR.pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.Driver;
import com.EMR.utilities.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class Calendar extends BasePage {

    @FindBy(xpath = "//li[text()='Calendar']")
    private WebElement calendarMenu;

    @FindBy(xpath = "//button[@title='Close']")
    public WebElement close;

    @FindBy(xpath = "//button[@aria-haspopup='menu']")
    public WebElement loggedInUser;

    @FindBy(xpath = "//img[@alt='dropdown']")
    public WebElement profileDropdown;

    @FindBy(xpath = "//li[text()='Logout']")
    public WebElement logout;

    @FindBy(xpath = "//button[normalize-space()='Semen Collection']")
    public WebElement semenCollectionLink;

    @FindBy(xpath = "//button[normalize-space()='Pathology']")
    public WebElement pathologyLink;

    @FindBy(xpath = "//button[normalize-space()='Admin']")
    public WebElement adminLink;

    @FindBy(xpath = "//button[normalize-space()='Consultation']")
    public WebElement consultationLink;

    @FindBy(xpath = "//button[normalize-space()='Ultrasound']")
    public WebElement ultrasoundLink;

    @FindBy(xpath = "//button[normalize-space()='Surgery']")
    public WebElement surgeryLink;

    @FindBy(xpath = "//div[@id='demo-simple-select-filled']")
    public WebElement clinicDropdown;

    @FindBy(xpath = "//*[@data-testid='CalendarViewMonthIcon']")
    public WebElement monthViewIcon;

    @FindBy(xpath = "//*[@data-testid='ListIcon']")
    public WebElement listViewIcon;

    @FindBy(xpath = "//span[contains(@class,'mbsc-calendar-month')]")
    public WebElement calendarMonth;

    @FindBy(xpath = "//span[contains(@class,'mbsc-calendar-year')]")
    public WebElement calendarYear;

    @FindBy(xpath = "//span[normalize-space()='Day']")
    public WebElement dayView;

    @FindBy(xpath = "//span[normalize-space()='Week']")
    public WebElement weekView;

    @FindBy(xpath = "//span[normalize-space()='Month']")
    public WebElement monthView;

    @FindBy(xpath = "//label[contains(@class,'mbsc-segmented-item-selected')]//span[normalize-space()='Month']")
    public WebElement monthLabelSelected;

    @FindBy(xpath = "//button[normalize-space()='Today']")
    public WebElement todayLink;

    @FindBy(xpath = "//button[@aria-label='Previous page']")
    public WebElement previousPageLink;

    @FindBy(xpath = "//button[@aria-label='Next page']")
    public WebElement nextPageLink;

    @FindBy(xpath = "//*[@data-testid='WorkOffIcon']")
    public WebElement workOffIcon;

    @FindBy(xpath = "//span[contains(normalize-space(),'CHECKED-OUT')]")
    public WebElement checkedOutStatus;

    @FindBy(xpath = "//span[contains(normalize-space(),'SCHEDULED')]")
    public WebElement scheduledStatus;

    @FindBy(xpath = "//span[contains(normalize-space(),'UNATTENDED')]")
    public WebElement unattendedStatus;

    @FindBy(xpath = "//div[contains(@role,'dialog')]")
    public WebElement appointmentDialog;

    public void clickCalendarMenu() {
        BrowserUtils.click(calendarMenu);
    }

    public void verifyUiElements() {
        BrowserUtils.verifyElementDisplayed(semenCollectionLink);
        BrowserUtils.verifyElementDisplayed(pathologyLink);
        BrowserUtils.verifyElementDisplayed(adminLink);
        BrowserUtils.verifyElementDisplayed(consultationLink);
        BrowserUtils.verifyElementDisplayed(ultrasoundLink);
        BrowserUtils.verifyElementDisplayed(surgeryLink);
        BrowserUtils.verifyElementDisplayed(clinicDropdown);
        BrowserUtils.verifyElementDisplayed(monthViewIcon);
        BrowserUtils.verifyElementDisplayed(listViewIcon);
        BrowserUtils.verifyElementDisplayed(calendarYear);
        BrowserUtils.verifyElementDisplayed(calendarMonth);
        BrowserUtils.verifyElementDisplayed(dayView);
        BrowserUtils.verifyElementDisplayed(weekView);
        BrowserUtils.verifyElementDisplayed(monthView);
        BrowserUtils.verifyElementDisplayed(todayLink);
        BrowserUtils.verifyElementDisplayed(previousPageLink);
        BrowserUtils.verifyElementDisplayed(nextPageLink);
        BrowserUtils.verifyElementDisplayed(workOffIcon);
        BrowserUtils.verifyElementDisplayed(checkedOutStatus);
        // BrowserUtils.verifyElementDisplayed(scheduledStatus);
        BrowserUtils.verifyElementDisplayed(unattendedStatus);
    }

    public void verifyDefaultSelections() {
        assertAdminSelected(adminLink);
        assertDropdownValue(clinicDropdown, "Centro Fertility Center 1", "Clinic Dropdown");
        assertMonthSelectedByDefault();
        assertCurrentMonthYear(calendarMonth, calendarYear);
    }

    public void logout() {
        BrowserUtils.click(profileDropdown);
        BrowserUtils.click(logout);

    }

    public static void assertCurrentMonthYear(WebElement monthEl, WebElement yearEl) {
        String uiMonth = monthEl.getText().trim(); // e.g. "December"
        String uiYear = yearEl.getText().trim(); // e.g. "2025"

        java.time.ZoneId zoneId = java.time.ZoneId.of("Asia/Kolkata"); // or your app timezone
        java.time.LocalDate today = java.time.LocalDate.now(zoneId);

        String expectedMonth = today.format(java.time.format.DateTimeFormatter.ofPattern("MMMM"));
        String expectedYear = today.format(java.time.format.DateTimeFormatter.ofPattern("yyyy"));

        // message, expected, actual
        org.junit.Assert.assertEquals(
                "Month is not current month",
                expectedMonth,
                uiMonth);

        org.junit.Assert.assertEquals(
                "Year is not current year",
                expectedYear,
                uiYear);
    }


    public static void assertAdminSelected(WebElement adminTab) {
        String color = adminTab.getCssValue("color").replace(" ", "");
        Assert.assertTrue("Admin tab is not selected based on color", color.contains("225,126,97"));
    }

    public static void assertDropdownValue(WebElement element, String expectedValue, String name) {
        String actualText = element.getText().trim();
        org.junit.Assert.assertEquals(
                "❌ Default value incorrect for " + name,
                expectedValue,
                actualText);
        System.out.println("✔ " + name + " default value correct: " + actualText);
    }

    public void assertMonthSelectedByDefault() {
        Assert.assertTrue("Month is NOT selected by default", monthLabelSelected.isDisplayed());
    }

    public void bookAppointmentsForAllTabs(List<AppointmentConfig> configs) {
        for (AppointmentConfig cfg : configs) {
            clickTopTab(cfg.tabName);                          
            doubleClickOnDatePlusDays(3);                     
            verifyAppointmentDialogVisible();                  
            fillAppointmentDialog(cfg);                       
        }
    }

    private void clickTopTab(String tabName) {
        By tabLocator = By.xpath("//button[normalize-space()='" + tabName + "']"
                + "|//span[normalize-space()='" + tabName + "']");
        WebElement tab = BrowserUtils.waitForClickablility(tabLocator, 10);
        BrowserUtils.click(tab);
    }

    private void doubleClickOnDatePlusDays(int days) {
        LocalDate target = LocalDate.now().plusDays(days);
        String monthDayYear = target.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        By cellLocator = By.xpath(
                "//div[contains(@class,'mbsc-calendar-cell-text') and @role='button' and contains(@aria-label,'"
                        + monthDayYear + "')]");

        WebElement cell = BrowserUtils.waitForClickablility(cellLocator, 10);
        BrowserUtils.doubleClick(cell);
    }

    private void verifyAppointmentDialogVisible() {
        BrowserUtils.waitForVisibility(appointmentDialog, 10);
    }

    private void fillAppointmentDialog(AppointmentConfig cfg) {
        // 4. Patient search & select
        By patientSearchLocator = By.xpath("//input[contains(@placeholder,'Select Patient') or @aria-label='Select Patient or Partner']");
        WebElement patientSearch = BrowserUtils.waitForClickablility(patientSearchLocator, 10);
        BrowserUtils.clearAndSendKeys(patientSearch, cfg.patientName);

        // wait for dropdown option and click
        By patientOptionLocator = By.xpath("//li[normalize-space()='" + cfg.patientName + "']"
                + "|//div[contains(@class,'option')][normalize-space()='" + cfg.patientName + "']");
        WebElement patientOption = BrowserUtils.waitForClickablility(patientOptionLocator, 10);
        BrowserUtils.click(patientOption);

        // 5. Appointment Reason
        By reasonDropdownLocator = By.xpath("//div[contains(@class,'reason') or @id='appointmentReason']");
        WebElement reasonDropdown = BrowserUtils.waitForClickablility(reasonDropdownLocator, 10);
        BrowserUtils.click(reasonDropdown);

        By reasonOptionLocator = By.xpath("//li[normalize-space()='" + cfg.appointmentReason + "']"
                + "|//div[normalize-space()='" + cfg.appointmentReason + "']");
        WebElement reasonOption = BrowserUtils.waitForClickablility(reasonOptionLocator, 10);
        BrowserUtils.click(reasonOption);

        // 6–7. Personnel dropdown – find doctor with green color
        selectAvailableDoctor(cfg.doctorName);

        // 8. Date >= today (already today+3 from calendar, but assert)
        By dateInputLocator = By.xpath("//input[@placeholder='Date' or @name='date']");
        WebElement dateInput = BrowserUtils.waitForVisibility(dateInputLocator, 10);
        String dateStr = dateInput.getAttribute("value"); // e.g. 12/18/2025
        LocalDate selectedDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Assert.assertFalse("Selected date should not be before today", selectedDate.isBefore(LocalDate.now()));

        // 9–10. Time From > now, Time To = +30 min
        setTimeFields(selectedDate);

        // 11. Remarks + click book
        By remarksLocator = By.xpath("//textarea[@name='remarks' or @placeholder='Remark']");
        WebElement remarks = BrowserUtils.waitForVisibility(remarksLocator, 10);
        BrowserUtils.clearAndSendKeys(remarks, cfg.remarks);

        By bookBtnLocator = By.xpath("//button[normalize-space()='Book Appointment']");
        WebElement bookBtn = BrowserUtils.waitForClickablility(bookBtnLocator, 10);
        BrowserUtils.click(bookBtn);
        BrowserUtils.waitForPageToLoad(10);
    }

    private void selectAvailableDoctor(String doctorName) {
        By personnelDropdownLocator = By.xpath("//div[contains(@class,'personnel') or @id='personnel']");
        WebElement personnelDropdown = BrowserUtils.waitForClickablility(personnelDropdownLocator, 10);
        BrowserUtils.click(personnelDropdown);

        // Wait for dropdown options to appear
        BrowserUtils.waitFor(1);

        // Find all options that contain the doctor name
        By doctorOptionsLocator = By.xpath("//li[contains(.,'" + doctorName + "')]"
                + "|//div[contains(@class,'option')][contains(.,'" + doctorName + "')]");
        List<WebElement> doctorOptions = Driver.get().findElements(doctorOptionsLocator);

        WebElement availableOption = null;

        for (WebElement opt : doctorOptions) {
            String color = opt.getCssValue("color").replace(" ", ""); // e.g. rgba(0,128,0,1)
            if (color.contains("0,128,0")) { // green-ish
                availableOption = opt;
                break;
            }
        }

        Assert.assertNotNull("No available (green) entry found for doctor: " + doctorName, availableOption);
        BrowserUtils.click(availableOption);
    }

    private void setTimeFields(LocalDate selectedDate) {
        By timeFromLocator = By.xpath("//input[@name='timeFrom' or @placeholder='Time From']");
        By timeToLocator = By.xpath("//input[@name='timeTo' or @placeholder='Time To']");

        WebElement timeFromInput = BrowserUtils.waitForVisibility(timeFromLocator, 10);
        WebElement timeToInput = BrowserUtils.waitForVisibility(timeToLocator, 10);

        LocalTime now = LocalTime.now();
        LocalTime baseTime;

        if (selectedDate.isAfter(LocalDate.now())) {
            baseTime = LocalTime.of(9, 0); // any default time for future day
        } else {
            baseTime = now.plusMinutes(30); // ensure > current time
        }

        LocalTime toTime = baseTime.plusMinutes(30);

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        String fromStr = baseTime.format(timeFmt);
        String toStr = toTime.format(timeFmt);

        BrowserUtils.clearAndSendKeys(timeFromInput, fromStr);
        BrowserUtils.clearAndSendKeys(timeToInput, toStr);
    }

    /**
     * Verifies that an appointment was successfully created on the calendar page
     * Checks for the appointment by patient name, tab, and scheduled status
     * 
     * @param patientName The name of the patient for whom the appointment was booked
     * @param tabName The tab where the appointment should appear (e.g., "Semen Collection", "Consultation")
     * @return true if appointment is found and verified, false otherwise
     */
    public boolean verifyAppointmentCreated(String patientName, String tabName) {
        try {
            // Step 1: Navigate to the specific tab
            clickTopTab(tabName);
            BrowserUtils.waitForPageToLoad(3);
            
            // Step 2: Wait for calendar to load appointments
            BrowserUtils.waitFor(2);
            
            // Step 3: Look for appointment card/event with patient name
            // This XPath searches for appointment elements containing the patient name
            By appointmentLocator = By.xpath(
                "//div[contains(@class,'appointment') or contains(@class,'event') or contains(@class,'mbsc-schedule-event')]" +
                "[contains(.,'" + patientName + "')]"
            );
            
            // Wait for appointment element to be visible
            WebElement appointment = BrowserUtils.waitForVisibility(appointmentLocator, 10);
            
            if (appointment != null && appointment.isDisplayed()) {
                System.out.println("✔ Appointment verified for patient: " + patientName + " in tab: " + tabName);
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("❌ Failed to verify appointment for patient: " + patientName + " in tab: " + tabName);
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
        return false;
    }
    
    /**
     * Verifies that an appointment was successfully created using AppointmentConfig
     * This is a convenience method that uses the appointment configuration object
     * 
     * @param config The AppointmentConfig object containing appointment details
     * @return true if appointment is found and verified, false otherwise
     */
    public boolean verifyAppointmentCreated(AppointmentConfig config) {
        return verifyAppointmentCreated(config.patientName, config.tabName);
    }
    
    /**
     * Verifies appointment with detailed information including date and time
     * 
     * @param patientName The name of the patient
     * @param tabName The tab where the appointment should appear
     * @param appointmentDate The expected date of the appointment
     * @return true if appointment is found with correct details, false otherwise
     */
    public boolean verifyAppointmentWithDetails(String patientName, String tabName, LocalDate appointmentDate) {
        try {
            // Navigate to tab
            clickTopTab(tabName);
            BrowserUtils.waitForPageToLoad(3);
            BrowserUtils.waitFor(2);
            
            // Format date to match calendar display
            String dateStr = appointmentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
            
            // Search for appointment with both patient name and date
            By appointmentLocator = By.xpath(
                "//div[contains(@class,'appointment') or contains(@class,'event') or contains(@class,'mbsc-schedule-event')]" +
                "[contains(.,'" + patientName + "')]" +
                "[contains(@aria-label,'" + dateStr + "') or ancestor::*[contains(@aria-label,'" + dateStr + "')]]"
            );
            
            WebElement appointment = BrowserUtils.waitForVisibility(appointmentLocator, 10);
            
            if (appointment != null && appointment.isDisplayed()) {
                System.out.println("✔ Appointment verified with details - Patient: " + patientName + 
                                   ", Tab: " + tabName + ", Date: " + dateStr);
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("❌ Failed to verify appointment details for patient: " + patientName);
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
        return false;
    }

    // Inner class to hold appointment configuration
    public static class AppointmentConfig {
        public String tabName;
        public String patientName;
        public String appointmentReason;
        public String doctorName;
        public String remarks;

        public AppointmentConfig(String tabName, String patientName, String appointmentReason,
                String doctorName, String remarks) {
            this.tabName = tabName;
            this.patientName = patientName;
            this.appointmentReason = appointmentReason;
            this.doctorName = doctorName;
            this.remarks = remarks;
        }
    }

    /**
     * Load all appointment configurations from JSON test data
     * @return List of AppointmentConfig objects
     */
    public static List<AppointmentConfig> loadAppointmentsFromJson() {
        List<AppointmentConfig> configs = new java.util.ArrayList<>();
        
        JsonNode appointmentsNode = JsonUtils.getNestedNode("calendar", "appointments");
        
        if (appointmentsNode != null && appointmentsNode.isArray()) {
            for (JsonNode appointment : appointmentsNode) {
                String tabName = appointment.path("tabName").asText();
                String patientName = appointment.path("patientName").asText();
                String appointmentReason = appointment.path("appointmentReason").asText();
                String doctorName = appointment.path("doctorName").asText();
                String remarks = appointment.path("remarks").asText();
                
                configs.add(new AppointmentConfig(tabName, patientName, appointmentReason, 
                        doctorName, remarks));
            }
        }
        
        return configs;
    }
    
    /**
     * Load appointment configuration for a specific tab from JSON test data
     * @param tabName The name of the tab (e.g., "Semen Collection", "Consultation")
     * @return AppointmentConfig object for the specified tab
     */
    public static AppointmentConfig loadAppointmentByTabFromJson(String tabName) {
        JsonNode appointmentsNode = JsonUtils.getNestedNode("calendar", "appointments");
        
        if (appointmentsNode != null && appointmentsNode.isArray()) {
            for (JsonNode appointment : appointmentsNode) {
                String currentTabName = appointment.path("tabName").asText();
                if (currentTabName.equalsIgnoreCase(tabName)) {
                    String patientName = appointment.path("patientName").asText();
                    String appointmentReason = appointment.path("appointmentReason").asText();
                    String doctorName = appointment.path("doctorName").asText();
                    String remarks = appointment.path("remarks").asText();
                    
                    return new AppointmentConfig(currentTabName, patientName, appointmentReason, 
                            doctorName, remarks);
                }
            }
        }
        
        throw new IllegalArgumentException("Appointment for tab '" + tabName + "' not found in JSON");
    }
}
