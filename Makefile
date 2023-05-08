MVN_PROP := -Dorg.slf4j.simpleLogger.showDateTime=true $\ 
						-Dorg.slf4j.simpleLogger.dateTimeFormat=HH:mm:ss,SSS

compile: # compile project
	./mvnw clean compile
