Feature: Login

  # TCC.HR.001
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter a valid username and password
    And I click the login button
    Then I should be redirected to dashboard page

  # TCC.HR.002
  Scenario Outline: Invalid login with invalid credentials
    Given I am logout
    When I enter a invalid username "<username>" and password "<password>"
    And I click the login button
    Then I see message invalid credentials
    And I was on the login page

    Examples: 
      | username | password   |
      | Admin    | @admin     |
      | ademin   | admin.123  |

  # TCC.HR.003
  Scenario Outline: Empty username or password
    Given I am on the login page
    When I enter a username "<username>" or password "<password>"
    And I click the login button
    Then I see message required under username and password
    And I was on the login page

    Examples: 
      | username | password   |
      |          | admin.123  |
      |          | ademin.321 |
      |          | ademin#321 |
      | Admin    |            |
      | Adminnn  |            |
      | Adminn@  |            |