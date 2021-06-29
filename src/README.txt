To compile the java files: 
	- cd into the "src" folder
	- Type:  javac Main.java

To run the java files:
	- Type: java Main
	- Program will ask for a number of Computer players you want to play against

To compile the Test file:
	- Type: javac -cp .:junit-platform-console-standalone-1.6.0.jar TESTCLASS.java

To run the Test file:
	- Type: java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path