# Java-Console-Role-Player-Game-Application

This is a simple role playing game written as a Java console application.

Currently the story on it is about the Fringe TV Series. But the topic, characters and the game properties like the 2-D map size can be changed by editing the game_info.properties, game_characters.properties, game_enemy_characters.properties and game_questions.data files in the data folder which must be on the working directory of the runnable jar file.

The persistent stores used in the game are game_players.data and saved_games.data files which are also in data folder.

For unit tests JUnit library and for logging slf4j with log4j is used. To continuosly build,test and package the application Maven is used with the maven-surefire-plugin to run JUnit tests and maven-assembly-plugin to create a runnable jar. Using 'mvn package' command, the application can be packaged as a runnable jar with the test cases run and using java -jar jar_name command the application can be started.
