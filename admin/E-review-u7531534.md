## Code Review

Reviewed by: Jiawei Li, u7531534

Reviewing code written by: Benjamin Campbell, u7471333

Component: https://gitlab.cecs.anu.edu.au/u7471333/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Viewer.java#L54-197

### Comments 

* What are the best features of this code?

Build each module to be displayed separately; take different 
building methods for different modules to be displayed;
use vbox for vertical information, use gridpane for square ones

* Is the code well-documented?

yes.Each created module is preceded by detailed comments

* Is the program decomposition (class and method structure) appropriate?

Quite appropriately, functions from other classes are used to build 
objects out of strings. Displaying an image based on several objects. 
After that we should use stringToGame in game instead of createAssam,
createBoard, createPlayer functions.

* Does it follow Java code conventions (for example, are methods and variables properly named), and is the style consistent throughout?

Sure.The naming of each variable and method follows the format.
The programming style is also consistent.

* If you suspect an error in the code, suggest a particular situation in which the program will not function correctly.

When setting the layout position in rows 113 and 114, 
the initial data should be 36 instead of 35 because of the addition of one pixel for the border