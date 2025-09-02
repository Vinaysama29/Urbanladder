Feature: Complete Home Page Interaction Flow

  Scenario: Perform login and verify social media icons and home logo navigation
    Given the user launches the browser and navigates to the Urban Ladder home page
    When the user clicks on the profile icon
    And the user clicks on the login option
    And the user enters valid credentials and submits the login form
    Then the user should be logged in successfully
    And the Facebook icon should be visible in the footer
    And the Twitter icon should be visible in the footer
    When the user clicks on the home logo
    Then the user should be redirected to the home page
