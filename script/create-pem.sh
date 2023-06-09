#!/bin/bash
cat <<EOT >> "${1}/openssl.cnf"
[ req ]
default_bits = 2048
prompt = no
default_md = sha256
distinguished_name = dn
[ dn ]
C = IE
ST = Test State
L = Test Locality
O = Org Name
OU = Org Unit Name
emailAddress = test@email.com
CN = localhost
EOT
