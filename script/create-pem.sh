#!/bin/bash
cat <<EOT >> "${1}/openssl.cnf"
[ req ]
prompt = no
distinguished_name = req_distinguished_name
[ req_distinguished_name ]
C = IE
ST = Test State
L = Test Locality
O = Org Name
OU = Org Unit Name
CN = Common Name
emailAddress = test@email.com
EOT
