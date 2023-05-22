MVN_PROP := -Dorg.slf4j.simpleLogger.showDateTime=true $\ 
						-Dorg.slf4j.simpleLogger.dateTimeFormat=HH:mm:ss,SSS
MVN_REPORT := target/site/surefire-report.html

TIMESTAMP := $(shell date +'%F %T')

compile: # compile project
	./mvnw clean compile

start-api:
	./mvnw -pl :social-network-web clean spring-boot:run -Dspring-boot.run.profiles=dev -Dspring.jmx.enabled=true

report-maven: # Gerar relatorio HTML utilizando maven
	@./mvnw  surefire-report:report
	
	$(info $(TIMESTAMP) [INFO] maven report generate in: $(MVN_REPORT))
