Feature: Add product to wishlist from Urban Ladder


	
  Scenario: Search product from JSON and verify it in wishlist
    When the user searches for a product using JSON data
    And the user adds the first product to the wishlist
    Then the user verifies the product is added to the wishlist
    Then the user should be redirected to the homepage to Go
