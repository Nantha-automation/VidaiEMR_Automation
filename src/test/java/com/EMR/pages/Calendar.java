package com.EMR.pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.Driver;
import com.EMR.utilities.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class Calendar extends BasePage {

    private static final int dayToPlus = 5;

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

    @FindBy(xpath = "//input[contains(@placeholder,'Search Patient or Partner')]")
    public WebElement searchPatientOrPartnerInput;

    @FindBy(xpath = "(//div[contains(@class,'MuiInputBase-root') and contains(@class,'MuiOutlinedInput-root')]//input[@role='combobox'])[3]")
    public WebElement doctorSelectionDropdown;

    @FindBy(xpath = "//label[contains(., 'Appointment Reason')]/following::input[@role='combobox'][1]")
    public WebElement appointmentReasonDropdown;

    @FindBy(id = "room-label")
    public WebElement roomDropdown;

    @FindBy(id = "table-select")
    public WebElement tableDropdown;

    @FindBy(id = "timeFrom")
    public WebElement timeFrom;

    @FindBy(id = "timeTo")
    public WebElement timeTo;

    @FindBy(id = "remark")
    public WebElement remark;

    @FindBy(xpath = "//button[normalize-space()='Book Appointment']")
    public WebElement bookAppointmentButton;

    @FindBy(xpath = "//button[normalize-space()='Yes']|//span[normalize-space()='Yes']")
    public WebElement yesButton;

    @FindBy(xpath = "//button[normalize-space()='No']|//span[normalize-space()='No']")
    public WebElement noButton;

    @FindBy(xpath = "//div[contains(@class,'MuiDialogActions-root')]//button[contains(@class,'appointmentModal_save')]")
    public WebElement forceBookAppointmentButton;

    @FindBy(xpath = "//p[normalize-space()='Patient Information']")
    public WebElement patientInformationPageTitle;

    @FindBy(xpath = "//label[contains(normalize-space(),'Reschedul')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input")
    public WebElement reschedulingReasonInput;

    @FindBy(xpath = "//label[contains(normalize-space(),'Cancellation')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input")
    public WebElement cancelReasonInput;

    @FindBy(xpath = "//div[contains(@style,'cursor')][.//p[normalize-space()='Reschedule']]")
    public WebElement rescheduleButton;

    @FindBy(xpath = "//button[normalize-space()='Reschedule']")
    public WebElement rescheduleAppointmentButton;

    @FindBy(xpath = "//div[contains(@style,'cursor')][.//p[normalize-space()='Cancel']]")
    public WebElement cancelButton;

    @FindBy(xpath = "//button[normalize-space()='Yes, Sure']")
    public WebElement yesSureButton;

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
            System.out.println("📅 Booking appointment for tab: " + cfg.tabName + ", Patient: " + cfg.patientName);

            // Navigate to the specific tab
            clickTopTab(cfg.tabName);

            // Double click on the date to open dialog
            doubleClickOnDatePlusDays(dayToPlus);

            // Verify dialog is visible
            verifyAppointmentDialogVisible();

            // Fill and submit the appointment
            fillAppointmentDialog(cfg, false);

            // Verify appointment was created (without re-navigating to tab)
            verifyAppointmentCreatedOnCurrentTab(cfg.patientName);

            System.out.println("✅ Completed appointment for " + cfg.tabName + "\n");
        }
    }

    private void clickTopTab(String tabName) {
        By tabLocator = By.xpath("//button[normalize-space()='" + tabName + "']"
                + "|//span[normalize-space()='" + tabName + "']");
        WebElement tab = BrowserUtils.waitForClickablility(tabLocator, 10);
        BrowserUtils.click(tab);
        BrowserUtils.waitFor(3);
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

    private void fillAppointmentDialog(AppointmentConfig cfg, boolean forceBooking) {
        // 4. Patient search & select
        BrowserUtils.clearAndSendKeys(searchPatientOrPartnerInput, cfg.patientName);

        // wait for dropdown option and click
        By patientOptionLocator = By.xpath(
                "//ul[@role='listbox']//li[@role='option' and contains(normalize-space(),'" + cfg.patientName + "')]");

        WebElement patientOption = BrowserUtils.waitForClickablility(patientOptionLocator, 10);
        BrowserUtils.click(patientOption);

        BrowserUtils.waitFor(3);

        // ⭐ choose doctor based on mode
        if (forceBooking == true) {
            selectUnavailableDoctor(); // red doctor
        } else {
            selectAvailableDoctor(); // green doctor
        }

        // selectAvailableDoctor();

        BrowserUtils.waitFor(1);

        BrowserUtils.click(appointmentReasonDropdown);

        By reasonOptionLocator = By.xpath(
                "//li[contains(normalize-space(),'" + cfg.appointmentReason + "')]"
                        + "|//div[contains(normalize-space(),'" + cfg.appointmentReason + "')]");

        WebElement reasonOption = BrowserUtils.waitForClickablility(reasonOptionLocator, 10);
        BrowserUtils.click(reasonOption);

        // 🔹 If tag name is "Surgery" → select Room and Table
        if ("Surgery".equalsIgnoreCase(cfg.tagName)) {

            // Room dropdown
            BrowserUtils.click(roomDropdown);
            BrowserUtils.waitFor(3);
            BrowserUtils.selectFirstOptionFromOpenDropdown();

            // Table dropdown
            BrowserUtils.click(tableDropdown);
            BrowserUtils.waitFor(3);
            BrowserUtils.selectFirstOptionFromOpenDropdown();
        }
        setTimeFields();
        BrowserUtils.clearAndSendKeys(remark, cfg.remarks);
        if (forceBooking == true) {
            BrowserUtils.click(forceBookAppointmentButton);
            BrowserUtils.waitForPageToLoad(10);
            BrowserUtils.click(yesButton);
            BrowserUtils.waitForPageToLoad(10);
        } else {
            BrowserUtils.click(bookAppointmentButton);
            BrowserUtils.waitForPageToLoad(10);
            BrowserUtils.click(yesButton);
            BrowserUtils.waitForPageToLoad(10);
        }
    }

    private void selectAvailableDoctor() {
        // Open the personnel dropdown
        BrowserUtils.click(doctorSelectionDropdown);
        BrowserUtils.waitFor(1); // allow animation

        // Locate all doctor option rows inside listbox
        List<WebElement> doctorOptions = Driver.get().findElements(
                By.xpath("//ul[@role='listbox']//li[@role='option']"));

        // Expected RGBA for GREEN ("green" = #008000)
        String GREEN_RGB = "0,128,0";

        WebElement availableDoctor = null;

        for (WebElement option : doctorOptions) {
            try {
                // the green color is inside <p>
                WebElement label = option.findElement(By.xpath(".//p"));
                String color = label.getCssValue("color").replace(" ", ""); // e.g. "rgba(0,128,0,1)"

                System.out.println("Checking doctor: " + label.getText() + " | Color: " + color);

                if (color.contains(GREEN_RGB)) {
                    availableDoctor = option;
                    break;
                }
            } catch (Exception ignored) {
                // ignore options without <p> tag
            }
        }

        Assert.assertNotNull("❌ No available doctor shown in GREEN.", availableDoctor);

        // Click the selected doctor
        String doctorName = availableDoctor.findElement(By.xpath(".//p")).getText();
        availableDoctor.click();

        System.out.println("✔ Selected available doctor: " + doctorName);
    }

    public void selectUnavailableDoctor() {
        try {
            // Opens the doctor dropdown (change locator to your own dropdown element)
            BrowserUtils.click(doctorSelectionDropdown);
            BrowserUtils.waitFor(1);

            // XPath: p tag in list option containing style='red'
            By unAvailableDoctor = By.xpath(
                    "//li[@role='option']//p[contains(@style,'red')]");

            // Wait and click the entire option (click parent li)
            WebElement redDoctorOption = BrowserUtils.waitForClickablility(unAvailableDoctor, 10);

            // Clicking the li, not p (p has no click)
            BrowserUtils.click(redDoctorOption);

            System.out.println("✔ Selected doctor marked in red color." + redDoctorOption);

        } catch (Exception e) {
            Assert.fail("❌ Failed to select unavailable doctor. Error: " + e.getMessage());
        }
    }

    private void setTimeFields() {

        WebElement timeFromInput = BrowserUtils.waitForVisibility(timeFrom, 10);
        WebElement timeToInput = BrowserUtils.waitForVisibility(timeTo, 10);

        LocalTime now = LocalTime.now();
        LocalTime baseTime;

        if (now.getMinute() < 30) {
            baseTime = now.withMinute(30).withSecond(0).withNano(0);
        } else {
            baseTime = now.plusMinutes(30).withSecond(0).withNano(0); // ensure > current time
        }

        LocalTime toTime = baseTime.plusMinutes(30);

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        String fromStr = baseTime.format(timeFmt);
        String toStr = toTime.format(timeFmt);

        BrowserUtils.clearAndSendKeys(timeFromInput, fromStr);
        BrowserUtils.clearAndSendKeys(timeToInput, toStr);
    }

    /**
     * Verifies appointment on the current tab without navigating
     * Use this when you're already on the correct tab
     * 
     * @param patientName The name of the patient
     */
    private void verifyAppointmentCreatedOnCurrentTab(String patientName) {
        try {
            System.out.println("🔍 Verifying appointment for patient: " + patientName);

            // Wait for calendar to refresh and load the new appointment
            BrowserUtils.waitForPageToLoad(3);
            BrowserUtils.waitFor(3);

            String firstName = patientName.split("\\s+")[0];

            // Calculate the appointment date (same as booking: today + 6 days)
            LocalDate appointmentDate = LocalDate.now().plusDays(dayToPlus);
            String monthDayYear = appointmentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

            // Search for appointment with patient name and date on current view
            By appointmentLocator = By.xpath(
                    "//div[@id=//div[contains(@aria-label,'" + monthDayYear + "')]/@aria-describedby]"
                            + "//div[contains(@class,'mbsc-calendar-label-wrapper')"
                            + " and contains(@title,'" + firstName + "')]");

            WebElement appointment = BrowserUtils.waitForVisibility(appointmentLocator, 10);

            if (appointment != null && appointment.isDisplayed()) {
                System.out.println("✔ Appointment verified for patient: " + patientName + " on date: " + monthDayYear);
                return;
            }

            Assert.fail("❌ Appointment element not visible for Patient: " + patientName);

        } catch (TimeoutException e) {
            Assert.fail("❌ Appointment NOT found for Patient: " + patientName + ". Timeout waiting for element. "
                    + e.getMessage());
        } catch (Exception e) {
            Assert.fail("❌ Failed to verify appointment for Patient: " + patientName + ". Error: " + e.getMessage());
        }
    }

    /**
     * Clicks the appointment for the given patient on the current tab.
     * This method expects the appointment to already exist.
     *
     * @param patientName Name of the patient to click appointment for
     */
    private void clickAppointmentOnCurrentTab(String patientName) {
        try {
            System.out.println("🖱 Trying to click on the appointment for: " + patientName);

            String firstName = patientName.split("\\s+")[0];

            LocalDate appointmentDate = LocalDate.now().plusDays(dayToPlus);
            String monthDayYear = appointmentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

            By appointmentLocator = By.xpath(
                    "//div[@id=//div[contains(@aria-label,'" + monthDayYear + "')]/@aria-describedby]"
                            + "//div[contains(@class,'mbsc-calendar-label-wrapper')"
                            + " and contains(@title,'" + firstName + "')]");

            WebElement appointment = BrowserUtils.waitForClickablility(appointmentLocator, 10);

            BrowserUtils.click(appointment);
            System.out.println("🖱 Clicked appointment for: " + patientName);

        } catch (Exception e) {
            Assert.fail("❌ Failed to click appointment for patient: " + patientName + ". Error: " + e.getMessage());
        }
    }

    // Inner class to hold appointment configuration
    public static class AppointmentConfig {
        public String tabName;
        public String patientName;
        public String appointmentReason;
        public String remarks;
        public String tagName;

        public AppointmentConfig(String tabName, String patientName, String appointmentReason,
                String remarks) {
            this.tabName = tabName;
            this.patientName = patientName;
            this.appointmentReason = appointmentReason;
            this.remarks = remarks;
            this.tagName = tabName; // Set tagName same as tabName for Surgery check
        }
    }

    public void forceBookAppointment(AppointmentConfig cfg) {
        System.out.println("📅 Booking appointment for tab: " + cfg.tabName + ", Patient: " + cfg.patientName);

        // Navigate to the specific tab
        clickTopTab(cfg.tabName);

        // Double click on the date to open dialog
        doubleClickOnDatePlusDays(dayToPlus);

        // Verify dialog is visible
        verifyAppointmentDialogVisible();

        // Fill and submit the appointment
        fillAppointmentDialog(cfg, true);

        // Verify appointment was created (without re-navigating to tab)
        verifyAppointmentCreatedOnCurrentTab(cfg.patientName);

        System.out.println("✅ Completed appointment for " + cfg.tabName + "\n");
    }

    public void verifyPatientInformationPage() {
        BrowserUtils.waitForVisibility(patientInformationPageTitle, 10);
        Assert.assertTrue("❌ Patient Information page is NOT displayed!", patientInformationPageTitle.isDisplayed());
        System.out.println("✔ Patient Information page verified successfully.");
    }

    public void rescheduleAppointment(AppointmentConfig cfg) {
        try {
            System.out.println("🔄 Rescheduling appointment for patient: " + cfg.patientName);

            // Click on the existing appointment to open options
            clickAppointmentOnCurrentTab(cfg.patientName);

            BrowserUtils.waitFor(2);

            // Click on Reschedule button
            BrowserUtils.click(rescheduleButton);
            BrowserUtils.waitForPageToLoad(10);

            JsonNode appointmentsArray = JsonUtils.getNestedNode("calendar", "appointments");

            Assert.assertTrue("'appointments' is not an array in JSON", appointmentsArray.isArray());

            JsonNode metaNode = appointmentsArray.get(appointmentsArray.size() - 1); // last element

            String rescheduleReason = metaNode.path("rescheduleReason").asText();

            BrowserUtils.clearAndSendKeys(reschedulingReasonInput, rescheduleReason);

            System.out.println("✅ Rescheduled appointment for patient: " + cfg.patientName + "\n");

        } catch (Exception e) {
            Assert.fail("❌ Failed to reschedule appointment for Patient: " + cfg.patientName + ". Error: "
                    + e.getMessage());
        }

        clickRescheduleButtonWithToastCheck();

        BrowserUtils.waitForPageToLoad(10);
    }

    private void clickRescheduleButtonWithToastCheck() {
        // 🔎 Check if the toast appears (within 2 seconds)
        if (BrowserUtils.waitForElementPresent(close, 2)) {
            System.out.println("⚠ Error toast detected! Closing toast…");
            BrowserUtils.click(close);
            BrowserUtils.waitFor(1);
        }

        // 🖱 Now safely click reschedule
        BrowserUtils.click(rescheduleAppointmentButton);
        System.out.println("🖱 Clicked the Reschedule button.");
    }

    public void verifyRescheduleSuccessMessage() {
        By successMsgLocator = By.xpath(
                "//div[contains(@class,'MuiAlert-message') and contains(normalize-space(),'Appointment has been rescheduled')]");
        WebElement successMsg = BrowserUtils.waitForVisibility(successMsgLocator, 10);
        Assert.assertTrue("❌ Reschedule success message NOT displayed!", successMsg.isDisplayed());
        System.out.println("✔ Reschedule success message verified successfully.");
    }

    public void cancelAppointment(AppointmentConfig cfg) {
        try {
            System.out.println("❌ Cancelling appointment for patient: " + cfg.patientName);

            // Click on the existing appointment to open options
            clickAppointmentOnCurrentTab(cfg.patientName);

            BrowserUtils.waitFor(2);

            // Click on Cancel button
            BrowserUtils.click(cancelButton);
            BrowserUtils.waitForPageToLoad(10);

            JsonNode appointmentsArray = JsonUtils.getNestedNode("calendar", "appointments");

            Assert.assertTrue("'appointments' is not an array in JSON", appointmentsArray.isArray());

            JsonNode metaNode = appointmentsArray.get(appointmentsArray.size() - 1); // last element

            String cancelReason = metaNode.path("cancelReason").asText();

            BrowserUtils.clearAndSendKeys(cancelReasonInput, cancelReason);

            System.out.println("✅ Cancelled appointment for patient: " + cfg.patientName + "\n");

        } catch (Exception e) {
            Assert.fail(
                    "❌ Failed to cancel appointment for Patient: " + cfg.patientName + ". Error: " + e.getMessage());
        }

        BrowserUtils.click(yesSureButton);

        BrowserUtils.waitForPageToLoad(10);
    }

    public void verifyCancelSuccessMessage() {
        By successMsgLocator = By.xpath(
                "//div[contains(@class,'MuiAlert-message') and contains(normalize-space(),'The appointment has been cancelled successfully.')]");
        WebElement successMsg = BrowserUtils.waitForVisibility(successMsgLocator, 10);
        Assert.assertTrue("❌ Cancel success message NOT displayed!", successMsg.isDisplayed());
        System.out.println("✔ Cancel success message verified successfully.");
    }

    /**
     * Load all appointment configurations from JSON test data
     * 
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
                String remarks = appointment.path("remarks").asText();

                configs.add(new AppointmentConfig(tabName, patientName, appointmentReason,
                        remarks));
            }
        }

        return configs;
    }

    /**
     * Load appointment configuration for a specific tab from JSON test data
     * 
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
                    String remarks = appointment.path("remarks").asText();

                    return new AppointmentConfig(currentTabName, patientName, appointmentReason,
                            remarks);
                }
            }
        }

        throw new IllegalArgumentException("Appointment for tab '" + tabName + "' not found in JSON");
    }
}
