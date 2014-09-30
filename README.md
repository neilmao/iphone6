iphone6
=======

This is a widget to monitor the iPhone 6 & plus stock availability in Apple Store Au which is refreshing daily after midnight.

The "Important Only" option indicates to monitor iphone 6 plus all colours and iphone 6 gold stocks only.

The "Sydney Only" option indicates to monitor Sydney Apple Stores only rather than Australia wide stores.

To compile and run: 

mvn assembly:assembly -DdescriptorId=jar-with-dependencies

java -jar iphone6.jar [logFileName] [importantOnly] [sydneyOnly]

e.g:
java -jar target/iphone6-1.0-SNAPSHOT-jar-with-dependencies.jar log.txt i s
