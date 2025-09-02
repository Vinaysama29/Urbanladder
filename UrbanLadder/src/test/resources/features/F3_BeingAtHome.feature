Feature: Search and capture top items from Urban Ladder


  Scenario: Search for 'Being at Home' items and store top results
    When the user searches for the 'Being at Home' filter from JSON
    And the user captures the top three items from the search results
    Then the item names should be written to the Excel sheet

  Scenario: Navigate to Urban Ladder homepage
    When the user clicks on the main logo
    Then the user should be redirected to the homepage
