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

create-ssl-cert:
	@mkdir -p certs
	@bash ./script/create-pem.sh ./certs
# gere uma chave privada
	@openssl genpkey -algorithm RSA -out ./certs/chave-privada.key -pkeyopt rsa_keygen_bits:2048
# crie uma solicitação de assinatura de certificado (CSR)
	@openssl req -new -key ./certs/chave-privada.key -out ./certs/csr.csr  -config ./certs/openssl.cnf
# auto-assine o certificado
	@openssl x509 -req -in ./certs/csr.csr -signkey ./certs/chave-privada.key -out ./certs/certificado.crt
