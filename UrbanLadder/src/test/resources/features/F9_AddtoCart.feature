Feature: Shipping Address Form Validation
 
  Scenario: Fill shipping address with valid and Invalid data
    
    When I fill the shipping address form with valid data from Excel sheet "valid"
    Then the "Save and Continue" button should be enabled
    When I fill the shipping address form with invalid data from Excel sheet "Invalid"
    Then the "Save and Continue" button should be disabled and an error message should be displayed
 