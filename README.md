Just some sunday afternoon screwing around. An outline for a text-based game, uses Scala Streams as a data structure for linking rooms together ([thanks SO](http://stackoverflow.com/questions/8962044/how-do-i-refer-to-a-variable-while-assigning-a-value-to-it-whilst-retaining-imm/8962972#8962972), a fast text formatter with ANSI color coded output ([copied from here](http://stackoverflow.com/questions/6110062/simple-string-template-replacement-in-scala-and-clojure)), and a relatively simple means of defining a room - may play around with that later.

Running
======

After cloning, execute:

 chmod +x ./sbt
 ./sbt
 > run

(note: if running sbt run right from the commandline (./sbt run), the user does not see his own input. Need to fix that).

Playing
======

To travel, type the first letter of the direction you want to go or the full direction. To exit, type 'exit'.

Packaging
=========

To create a standalone executable .jar, run ./sbt proguard. Then wait. .jar will be placed in target/scala-2.9.1/maze_2.9.1-0.1.min.jar. Execute with java -jar maze_2.9.1-0.1.min.jar.

Todo:
 * Find a way to load / dynamically compile & execute or parse Scala code for arbitrary levels / games
 * Make the syntax for defining a room and linking them together idiot-proof, perhaps with a DSL of sorts
 * Add interactive objects, NPC's
 * Make it an online multiplayer game. Just because. Big hairy goal.
