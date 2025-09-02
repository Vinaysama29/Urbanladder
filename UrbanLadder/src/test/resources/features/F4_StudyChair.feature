Feature: Capture Study Chair details from Urban Ladder



  Scenario: Extract top study chair products and return to homepage
    When the user navigates to the Study Chairs section
    And the user captures the top three study chair names and prices
    Then the product details should be written to the Excel sheet
    When the user clicks on the main logo to go
    Then the user should be redirected to the homepage to view
