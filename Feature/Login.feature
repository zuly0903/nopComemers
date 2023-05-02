Feature: Login Functionality of the website

  Scenario: Login with valid credentials
    Given User launch chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com"
    And User enters password as "admin"
    And User click on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on logout button
    Then The page title should be "Your store. Login"
    And Close the browser

  Scenario Outline: Login Data Driven
    Given User launch chrome browser
    When User open URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "<email>" 
    And User enters password as "<password>"
    And User click on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on logout button
    Then The page title should be "Your store. Login"
    And Close the browser

    Examples: 
      | email               | password |
      | admin@yourstore.com | admin    |
      | admin@yourstore.com | admin    |
