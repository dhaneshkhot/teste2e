@web @wikipedia
Feature: Wikipedia Demo Tests
  Wikipedia page title and search functionality

Scenario: Page title is Wikipedia
  Given I hit Wikipedia home page
  Then I see page title as "Wikipedia"

Scenario: An article can be searched on Wikipedia
  Given I hit Wikipedia home page
  When I search for "furry rabbits"
  Then A "Did you mean: " suggestion appears
  Then I click on appeared suggestion
  Then I see 20 search results
  Then On clicking first suggestion, I see that article has title and table of content

