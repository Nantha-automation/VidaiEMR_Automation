# Calendar Appointment Booking - JSON Integration Guide

## Overview
The `Calendar.java` page now supports loading appointment configurations from `testData.json` file, making test data management centralized and easier to maintain.

---

## JSON Structure

### Location: `src/test/resources/testData/testData.json`

```json
{
  "calendar": {
    "calendarPageUrl": "https://staging-use1.vidaisolutions.com/admin/calendar",
    "links": [...],
    "appointments": [
      {
        "tabName": "Semen Collection",
        "patientName": "John Doe",
        "appointmentReason": "Sample Collection",
        "doctorName": "Dr. Smith",
        "remarks": "Regular checkup appointment"
      },
      {
        "tabName": "Consultation",
        "patientName": "John Doe",
        "appointmentReason": "Initial Consultation",
        "doctorName": "Dr. Johnson",
        "remarks": "First time consultation"
      },
      {
        "tabName": "Ultrasound",
        "patientName": "Emma Williams",
        "appointmentReason": "Routine Scan",
        "doctorName": "Dr. Brown",
        "remarks": "Scheduled ultrasound scan"
      }
    ]
  }
}
```

---

## Available Methods in Calendar.java

### 1. Load All Appointments
```java
public static List<AppointmentConfig> loadAppointmentsFromJson()
```
- **Purpose**: Loads all appointments from JSON array
- **Returns**: List of AppointmentConfig objects
- **Use Case**: Book multiple appointments across different tabs

**Example:**
```java
List<AppointmentConfig> configs = Calendar.loadAppointmentsFromJson();
calendar.bookAppointmentsForAllTabs(configs);
```

---

### 2. Load Appointment by Index
```java
public static AppointmentConfig loadAppointmentFromJson(int index)
```
- **Purpose**: Loads a specific appointment by array index (0-based)
- **Parameters**: 
  - `index` - Position in the appointments array (0, 1, 2, etc.)
- **Returns**: Single AppointmentConfig object
- **Throws**: IllegalArgumentException if index not found

**Example:**
```java
// Load first appointment (index 0)
AppointmentConfig config = Calendar.loadAppointmentFromJson(0);
```

---

### 3. Load Appointment by Tab Name
```java
public static AppointmentConfig loadAppointmentByTabFromJson(String tabName)
```
- **Purpose**: Loads appointment for a specific tab
- **Parameters**: 
  - `tabName` - Name of the tab (e.g., "Semen Collection", "Consultation")
- **Returns**: Single AppointmentConfig object matching the tab
- **Throws**: IllegalArgumentException if tab not found

**Example:**
```java
AppointmentConfig config = Calendar.loadAppointmentByTabFromJson("Consultation");
```

---

## Step Definitions

### Available in `Calendar_StepDefs.java`

#### 1. Book All Appointments
```gherkin
When User books appointments for all tabs from JSON test data
```
- Books all appointments defined in JSON
- Iterates through all entries in the appointments array

#### 2. Book by Tab Name
```gherkin
When User books appointment for "Consultation" tab from JSON test data
When User books appointment for "Semen Collection" tab from JSON test data
```
- Books a single appointment for the specified tab
- Tab name must match JSON data exactly

#### 3. Book by Index
```gherkin
When User books appointment at index 0 from JSON test data
When User books appointment at index 1 from JSON test data
```
- Books a single appointment by its position in the array
- Index is 0-based (first appointment = 0)

---

## Usage Examples

### Example 1: Book All Appointments
```gherkin
@calendar @appointments
Scenario: Book appointments for all departments
  Given User is on the Calendar Page
  When User books appointments for all tabs from JSON test data
  Then Appointments should be successfully booked
```

### Example 2: Book Specific Tab Appointment
```gherkin
@calendar @consultation
Scenario: Book consultation appointment
  Given User is on the Calendar Page
  When User books appointment for "Consultation" tab from JSON test data
  Then Appointment should be successfully booked
```

### Example 3: Book by Index
```gherkin
@calendar @first-appointment
Scenario: Book the first appointment
  Given User is on the Calendar Page
  When User books appointment at index 0 from JSON test data
  Then Appointment should be successfully booked
```

---

## Appointment Configuration Fields

Each appointment in JSON must have these fields:

| Field | Type | Description | Example |
|-------|------|-------------|---------|
| `tabName` | String | Department tab name | "Semen Collection" |
| `patientName` | String | Patient's full name | "John Doe" |
| `appointmentReason` | String | Reason for appointment | "Sample Collection" |
| `doctorName` | String | Doctor's name | "Dr. Smith" |
| `remarks` | String | Additional notes | "Regular checkup" |

---

## Adding New Appointments

To add new appointments, simply add entries to the `appointments` array in JSON:

```json
{
  "tabName": "Surgery",
  "patientName": "New Patient",
  "appointmentReason": "Pre-surgery Consultation",
  "doctorName": "Dr. Anderson",
  "remarks": "Urgent appointment required"
}
```

**No code changes needed!** The methods automatically pick up new entries.

---

## Benefits

✅ **Centralized Test Data**: All appointment data in one JSON file  
✅ **Easy Maintenance**: Update test data without touching code  
✅ **Reusability**: Same data across multiple test scenarios  
✅ **Flexibility**: Three ways to load data (all, by tab, by index)  
✅ **Type Safety**: AppointmentConfig class ensures data integrity  

---

## Technical Details

### AppointmentConfig Class
```java
public static class AppointmentConfig {
    public String tabName;
    public String patientName;
    public String appointmentReason;
    public String doctorName;
    public String remarks;
}
```

### Booking Flow
1. **Click Tab** → Uses `BrowserUtils.waitForClickablility()` and `BrowserUtils.click()`
2. **Double-click Date** → Selects date 3 days from today using `BrowserUtils.doubleClick()`
3. **Verify Dialog** → Waits for appointment dialog using `BrowserUtils.waitForVisibility()`
4. **Fill Form** → All fields populated using `BrowserUtils.clearAndSendKeys()`
5. **Select Doctor** → Finds available doctor by green color indicator
6. **Set Time** → Automatically sets time based on date (future = 9 AM, today = current + 30 min)
7. **Submit** → Clicks Book button with `BrowserUtils.click()`

---

## Error Handling

### Common Errors and Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| `IllegalArgumentException: Appointment at index X not found` | Index out of bounds | Check appointments array size in JSON |
| `IllegalArgumentException: Appointment for tab 'X' not found` | Tab name doesn't match | Verify tab name matches JSON exactly (case-sensitive) |
| `No available (green) entry found for doctor` | Doctor not available | Check doctor schedule or update doctor name in JSON |

---

## Date Created: December 7, 2025
## Last Updated: December 7, 2025
## Status: ✅ Implemented and Tested
