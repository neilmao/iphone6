iphone6
=======

This is a widget to monitor the iPhone 6 & plus stock availability in Apple Store Au which is refreshing daily after midnight.

The "Important Only" option indicates to monitor iphone 6 plus all colours and iphone 6 gold stocks only.

The "Sydney Only" option indicates to monitor Sydney Apple Stores only rather than Australia wide stores.

To compile and run: 

mvn assembly:assembly -DdescriptorId=jar-with-dependencies

java -jar iphone6.jar <-i>[importantOnly] <-s>[sydneyOnly] <-t> <h|H|m|M|s|S[number]>[time]

e.g:
java -jar target/iphone6-1.0-SNAPSHOT-jar-with-dependencies.jar -i -s -t 1m
