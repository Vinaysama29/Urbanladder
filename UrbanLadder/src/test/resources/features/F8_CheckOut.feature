Feature: Checkout flow for a product on Urban Ladder

  Scenario: View product, add to cart, and proceed to checkout
    When the user clicks on the View Product button
    And the user adds the product to the cart
    Then the user proceeds to checkout
