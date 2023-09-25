
# Test plan

## List of classes

* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.

Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.

## Classes to be tested
### 1.Player
* stringToPlayer

After calling **getColorName(color)**,the color returned by 
player() has changed to an char which is easier to compare. Otherwise, it's hard to 
compare color objects with char in java.

### 2.Assam
* stringToAssam

After calling getAssamX(assam),the position returned by assam() has changed to
a int, same as the getAssamY(assam).
### 3.Board
* stringToBoard
### 4.Rug
* stringToAbbreviatedRug
### 5.Square
* isSquareValid

After calling stringToAbbreviatedRug(), we can create a new square for testing,
### 6.Viewer
* displayState - JavaFX - won't test
* createPlayer - create a Player from a game string
* createAssam - create an Assam from a game string
* createBoard - create a Board from a game string

  