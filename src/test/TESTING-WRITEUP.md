# M5 TESTING-WRITEUP

## Testing Process
- Testing will be thoroughly conducted using the Hamcrest matcher framework in conjunction with JUnit 5.0.
  Details about the specifics of each test are written more in depth below.

## Tests
1. testRifleTowerRange - test passes if the rifle tower can hit an enemy inside its range, but cannot hit an enemy outside of its range
2. testSniperTowerRange - test passes if the sniper tower can hit an enemy inside its range, but cannot hit an enemy outside of its range
3. testLaserTowerRange - test passes if the laser tower can hit an enemy inside its range, but cannot hit an enemy outside of its range
4. testWeakEnemyTakeDamage - test passes if rifle/sniper/laser tower causes the weak enemy to lose the expected amount of health
5. testNormalEnemyTakeDamage - test passes if rifle/sniper/laser tower causes the normal enemy to lose the expected amount of health
6. testStrongEnemyTakeDamage - test passes if rifle/sniper/laser tower causes the strong enemy to lose the expected amount of health
7. testWeakEnemyMoneyOnDeath - test passes if weak enemy death causes the player to gain the expected amount of money
8. testNormalEnemyMoneyOnDeath - test passes if normal enemy death causes the player to gain the expected amount of money
9. testStrongEnemyMoneyOnDeath - test passes if strong enemy death causes the player to gain the expected amount of money
10. testEnemyTypesHaveDifferentTravelSpeed - test passes if weak, normal, strong enemy types all have different travel speeds
11. testEnemyTypesHaveDifferentHealth - test passes if weak, normal, strong enemy types all have different health
12. testEnemyTypesHaveDifferentDamageDealtToMonument - test passes if weak, normal, strong enemy types all have different damages dealt to monument
13. testEnemyTypesHaveDifferentReward - test passes if weak, normal, strong enemy types all have different rewards


## Explanation
- The components we chose to test were the Enemy Reaches Monument Event, Enemy Hit By Bullet Event, Enemy Death Event and TowerType.
  These components were chosen because they are the components most critical to the users' experience for the game at this point in time,
  as the milestone focuses on the seamless ability to spawn multiple enemy types with differnt characteristics (travel speed, health, damage dealt to monument, money dropped on death) and use towers to kill the enemies.

---
# M4 TESTING-WRITEUP

## Testing Process
- Testing will be thoroughly conducted using the Hamcrest matcher framework in conjunction with JUnit 5.0.
  Details about the specifics of each test are written more in depth below.

## Tests
1. testWeakEnemyReachMonument - test passes if the monument health is adjusted accordingly for a weak enemy and the difficulty level
2. testNormalEnemyReachMonument - test passes if the monument health is adjusted accordingly for a normal enemy and the difficulty level
3. testStrongEnemyReachMonument - test passes if the monument health is adjusted accordingly for a normal enemy and the difficulty level
4. testWeakEnemyDeathAwardsMoney - test passes if the death of a weak enemy at the specified difficulty level awards money to the player
5. testNormalEnemyDeathAwardsMoney - test passes if the death of a normal enemy at the specified difficulty level awards money to the player
6. testStrongEnemyDeathAwardsMoney - test passes if the death of a strong enemy at the specified difficulty level awards money to the player
7. testRifleTowerDamageOnEnemy - test passes if the rifle tower does the specified amount of damage to an enemy
8. testSniperTowerDamageOnEnemy - test passes if the sniper tower does the specified amount of damage to an enemy
9. testLaserTowerDamageOnEnemy - test passes if the laser tower does the specified amount of damage to an enemy
10. testRestartGame - test passes if the game vars is reset

## Explanation
- The components we chose to test were the Enemy Reaches Monument Event, Enemy Hit By Bullet Event, and TowerType.
  These components were chosen because they are the components most critical to the users' experience for the game at this point in time,
  as the milestone focuses on the seamless ability to spawn multiple enemy types and use towers to kill the enemies.
  However, if the enemies are able to somehow make it past all the towers, the monument will lose health depending on the enemy type and difficulty level.
