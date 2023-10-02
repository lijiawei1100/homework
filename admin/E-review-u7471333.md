## Code Review

Reviewed by: Benjamin Campbell, u7471333

Reviewing code written by: Jiawei Li, u7531534

Component: https://gitlab.cecs.anu.edu.au/u7471333/comp1110-ass2/-/blob/main/src/comp1110/ass2/Marrakech.java#L182-208

### Comments 

* What are the best features of this code?

Efficient use of the positionBoolean and rugBoolean Booleans to check each placement condition; 
Implementation of getKey() and getValue(), plus new functions firstPosition and secondPosition
to compare and evaluate positions;

* Is the code well-documented?

Not so much. Only one comment exists and does not outline the reasoning behind each section, along with the 
reason for code structure + added Booleans. Needs more comments. 

* Is the program decomposition (class and method structure) appropriate?

The decomposition is appropriate and is object-oriented - a Game (with Assam, Players and Board) is created from the 
gameString input, and are called throughout the program. Good use of control flow with the 'if' function.

* Does it follow Java code conventions (for example, are methods and variables properly named), and is the style consistent throughout?

Yes - each variable follows a consistent naming style and the programming style is also consistent.
Suggest renaming positionBoolean and rugBoolean to 'isAdjacent' and 'doesNotCover', and firstPosition and secondPosition
to 'rug1' and 'rug2'. 
Code may be too long (too many characters per line)- suggest adding new lines to make it more readable or 
shortening existing function names. 

* If you suspect an error in the code, suggest a particular situation in which the program will not function correctly.

code seems to function fine - works intuitively and passes all tests. 

rugBoolean returns false if both occupied squares have the same colour and id
positionBoolean returns false if the occupied squares do not contain assam's positions but contains 
a square adjacent to assam. 