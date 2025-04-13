Feature: Login Feature
	Background:
		Given Launch the application
		
	@Sanity
  Scenario: Login to the application
    When user enters valid username and password
    And user should be navigated to home page
    Then logout from the application
