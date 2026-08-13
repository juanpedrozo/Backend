# Especifica a imagem base
# Ele instala ubunto com Java e Maven
FROM maven:3.9-eclipse-temurin-21-noble

# Habilita a instalação não interativa
ENV DEBIAN_FRONTEND=noninteractive

# Instala as ferramentas que não estão na imagem base
# apt-get update - Atualiza os repositórios do software
# apt-get install -y - Instala as ferramentas
# curl - Testa APIs
# git-lfs - Armazenamento de arquivos binários
RUN apt-get update && apt-get install -y \

    mysql-server-8.0 \
    git \
    git-lfs \
    curl \
    unzip \
    zip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
