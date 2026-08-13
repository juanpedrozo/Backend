#!/bin/bash

# Se ocorrer erro o script aborta 
set -e

# Exibe uma mensagem no terminal 
echo "Iniciando MySQL..."
# Inicia o MySQL
service mysql start

echo "Aguardando MySQL ficar pronto..."
# Verifica se o MySQL já iniciou
# 
for tentativa in $(seq 1 30); do
    if mysqladmin ping --silent; then
        echo "MySQL pronto."
        break
    fi
    sleep 1
done

# Se passou das 30 tentativas sem resposta, para o script com erro.
if ! mysqladmin ping --silent; then
    echo "Erro: MySQL não respondeu a tempo." >&2
    exit 1
fi

echo "Configurando banco..."

# Envia para o MySQL os comandos a seguir
# CHARACTER e COLLATE - Define a configuração dos caracteres
# CREATE USER - Cria um usuário
# GRANT - Atribui permissões para o usuário
# FLUSH - 
mysql <<EOF
CREATE DATABASE IF NOT EXISTS ecommerce
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'juan'@'%' IDENTIFIED BY '123@Mudar';

GRANT ALL PRIVILEGES ON ecommerce.* TO 'sergio'@'%';

FLUSH PRIVILEGES;
EOF

echo "MySQL configurado com sucesso!"
