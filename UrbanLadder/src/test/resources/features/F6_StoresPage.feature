Feature: Capture Hyderabad store details from Urban Ladder

	
	
  Scenario: Extract Hyderabad store names and return to homepage
    When the user navigates to the Hyderabad store section
    Then the user captures the list of Hyderabad store names
    And the user stores the names in the Excel sheet
    Then the user should be redirected to the homepage to see
