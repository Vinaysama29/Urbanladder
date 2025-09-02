Feature: Capture Bookshelves details from Urban Ladder



  Scenario: Extract top bookshelves after applying filters and return to homepage
    When the user clicks on the Bookshelves section
    And the user applies the price filter
    And the user selects "Open" as the storage type
    And the user excludes out-of-stock items
    Then the user captures the top 3 bookshelves and stores them in the Excel sheet
    Then the user should be redirected to the homepage to use Next
