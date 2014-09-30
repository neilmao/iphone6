iphone6
=======


To compile and run: 

mvn assembly:assembly -DdescriptorId=jar-with-dependencies

java -jar iphone6.jar [logFileName] [importantOnly] [sydneyOnly]

e.g:
java -jar target/iphone6-1.0-SNAPSHOT-jar-with-dependencies.jar log.txt i s
