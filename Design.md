game
====
###Design
***
#####Design Goals

I wanted to create a game that could be added to with ease. For that reason, adding a new bad guy, or a new type of fish worth more points is very simple. The classes for my actors are relatively small and not functionally complicated, so replicating and modifying one is not difficult. I have four different Actors: Fish, Shark, Hook, and Player. Each one of these behaves a little differently from the rest, though they mainly handle their drawing and movement. The design of my code revolves around the Game class. That is where most everything actually happens, from building the scene to handling collisions. Within the Game class, I make instances of all my Actors, and then call their public methods to have them interact with each other. Therefore, most changes to my game would be made by altering the Game class. 
***

#####Adding Features

To add a new level to my game, one would have to create a new method in the Game class, we'll assume called levelThree(). Inside this method they would have to call the methods that draw fish, sharks, or an added new actor, after calling reset to ensure the game starts the new level with all variables clear. Then it would require setting a boolean that tracked whether or not we were on level three to true within levelThree, and setting it to false in the other level's methods. Lastly, they would have to implement a new Button that, when pressed, likely after winning level two, called the method levelThree. 

To add a new actor to my game, you would need to create a new class that extended Actor, and therefore implemented all of the methods defined in the Actor superclass. In that class, you would set up the basic functionality of that new actor. This entails deciding which image you would like to use for this actor and adding it to the resources folder and creating the basic movement of this actor. Then, you would have to add a new instance of that actor to the Game class, and from there you can choose how you want to let the new actor interact with existing actors in the step, handleKeyInput, or handleMouseInput methods. 
***

#####Design Choices

One of the biggest design choices I made was to have my Hook, Fish, Player, and Shark classes all be subclasses of an Actor class. This was so I could ensure each class that would be drawing something to the screen had some necessary core functionality, such as getting or setting it's x and y values. The pros of this are that changing what is required to be an actor is as simple as adding or removing a method from the Actor class. This will then effect all of my subclasses, and, while they could continue to use certain methods, the new subclass could not implement the now removed method. One drawback of this implementation is that it couples these classes together, as the Actor class cannot be used without a subclass, and a subclass cannot be used without the Actor class. 