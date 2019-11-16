@web @travelex
Feature: Travelex Demo Tests
  Travelex slider functionality

Scenario: Window resize to 550px and swiping left twice displays third item in the list
  Given I hit Travelex home page
  When I resize the window to 550 px width
  And I scroll vertically down by 400 px to travelex services
  And A swipe left on the slider
  And I swipe left again
  Then I see the item displayed is the third item

