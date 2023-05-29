MVN_PROP := -Dorg.slf4j.simpleLogger.showDateTime=true $\ 
						-Dorg.slf4j.simpleLogger.dateTimeFormat=HH:mm:ss,SSS
MVN_REPORT := target/site/surefire-report.html

TIMESTAMP := $(shell date +'%F %T')

CERT_PATH := $(HOME)/.openssl/dev

compile: # compile project
	./mvnw clean compile

start-api:
	./mvnw -pl :social-network-web clean spring-boot:run -Dspring-boot.run.profiles=dev -Dspring.jmx.enabled=true

report-maven: # Gerar relatorio HTML utilizando maven
	@./mvnw  surefire-report:report
	@echo $(TIMESTAMP) [INFO] maven report generate in: $(MVN_REPORT)

create-ssl-cert:
	@mkdir -p $(CERT_PATH)
# remove arquivos anteriores
	@find $(CERT_PATH) -mindepth 1 -exec rm -rf {} +
# gera arquivo de configuraç~åo do certificado
	@bash ./script/create-pem.sh $(CERT_PATH)
# cria um certificado auto assinado, gera chave privada, o certificado, validade e as configurações a serem utilizadas no certificado
	@openssl req -x509 -newkey rsa:4096 -keyout $(CERT_PATH)/key.pem -out $(CERT_PATH)/cert.pem -days 365 -nodes -config $(CERT_PATH)/openssl.cnf
# gera o arquivo PKCS12 e define a senha de acesso ao certificado
	@openssl pkcs12 -export -out $(CERT_PATH)/cert.p12 -inkey $(CERT_PATH)/key.pem -in $(CERT_PATH)/cert.pem -password pass:password
# testa o certificado
# openssl pkcs12 -info -noout -in certs/keyStore.p12
