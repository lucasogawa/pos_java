FROM mariadb:10.6.4

ENV MARIADB_USER admin
ENV MARIADB_PASSWORD admin
ENV MARIADB_ROOT_PASSWORD admin

COPY ./LucasAkioOgawa_Atividade02.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
