FROM open-liberty


COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/liberty/wlp/usr/servers/liberty/bootstrap.properties /config/
COPY --chown=1001:0 target/liberty/wlp/usr/servers/liberty/server.env /config/
COPY --chown=1001:0 target/*.war /config/apps/

RUN configure.sh