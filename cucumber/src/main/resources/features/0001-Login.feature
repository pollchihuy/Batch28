Feature: Login

  # TCC.HR.001
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter a valid username and password
    And I click the login button
    Then I should be redirected to dashboard page

  # TCC.HR.002
  Scenario: Invalid login with invalid credentials
    Given I am logout
    When I login with invalid credentials from excel file "excel/scenario2.xlsx" sheet "sc2"

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

  # TCC.HR.004
  Scenario Outline: Empty password
    Given I am on the login page
    When I enter a username "<username>" or password "<password>"
    And I click the login button
    Then I see message required under username and password
    And I was on the login page

    Examples: 
      | username |
      | Admin    |
      | Adminnn  |
      | Adminn@  |