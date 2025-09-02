Feature: Help Page Interaction Flow

  Scenario: Access help topics and navigate back to home
    Given the user launches the browser and navigates to the Urban Ladder mainpage
    When the user clicks on the Help link
    And the user extracts and stores all help topics
    When the user clicks on the home logo to see
    Then the user should be redirected to the home pages
