Feature: API Testing

  @getAllUsers
  Scenario: Get all users
    Given I have the API endpoint "https://jsonplaceholder.typicode.com/users"
    When I send a GET request
    Then the response status should be 200
    And the response should contain "Leanne Graham"
  
  Scenario: Create a new user
    Given I have the API endpoint "https://jsonplaceholder.typicode.com/users"
    When I send a POST request with user data
    Then the response status should be 201
    And the response should contain the created user name