# EXTRA_CREDIT_PROJECT
## High-Level Description
- The project would involve the creation of an auto battler video game application.
- The game would consist of building a team from a selection of animals who wil fight for you.
- Each animal will have unique abilities which will improve when the animal is upgraded.
- The game mode will be asynchronous where your team will battle against other pre-made teams each turn.
- The goal is to gain 10 wins.

## Milestones
### Milestone 1
- The team should implement a welcome screen for the application which includes a start button (to start the game).
- Pressing the start button should take the player to the initial configuration screen which will have the following requirements:
  - allows the player to enter their name
  - allows the player to select a difficulty
  - allows the player to proceed to the game screen
- The team should implement the initial game screen which should display graphically:
  - 5 locations for "friendly" pets to be placed
  - display the starting money (varies based on difficulty selected)
  - display a location for 1 "enemy" pet to be placed

### Milestone 2
- The team should implement and design a pet menu where the player can buy and place pets.
  - the player should be able to purchase pets from the menu
  - pet should cost money and the pet cost for each type of pet should vary based on game difficulty
  - each pet type in the menu should have a name and description (of the unique ability)
- The pets purchased from the tower should be able to be placed into the 3 alloted locations for "friendly" pets.
  - the player should only be able to place pets into the 3 alloted slots for "friendly" pets
  - there should be at least 5 different types of pets (at the moment, just descriptively distinct)

### Milestone 3
- The team should add stats to their pets.
  - each pet should have attack, health, cost, and a unique ability
- The team should implement turns.
  - each turn, the pet menu should be randomly replaced with a selection of 3 pets (from the at least 5 pets you implement)
  - each turn, the player should gain a set amount of money to buy pets with (the player should always be able to buy all 3 pets)
  - each turn, the player's "friendly" pets should attack the "enemy" pet.
  - each turn, the "enemy" pet should randomly pick 1 "friendly" pet to attack.
  - when a "friendly" pet is attacked, it should lose health based on the "enemy" pet's attack
  - when an "enemy" pet is attacked, it should lose health based on the "friendly" pet's attack
- The team should implement an end turn button.
  - the end turn button should end the buy phase and automate attacking of friendly pets and enemy pet
  - "friendly" pets that lose all their health will faint (removed from the current turn, respawned at the start of the next turn)
  - "enemy" pet will not respawn when health is lost

### Milestone 4
- The team should implement a game over screen.
  - if the "enemy" pet loses all its health, the player wins
  - if the all the "friendly" pets faint within the same turn, the player loses
  - the game over screen should include:
    - a restart button (which takes you to the main menu)
    - a quit button (which closes the application)
- The team should implement "faint".
  - when a friendly pet faints, there should be a visible sign, signifying a faint pet

### Milestone 5
- The team should implement pet upgrades.
  - placing the same pet from the pet menu onto a "friendly" pet should upgrade it to the next level
  - an upgraded pet should have increased stats (attack, health)
