echo %USERNAME%
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://10.29.160.24:2376

SET DOCKER_CERT_PATH=C:\Users\%USERNAME%\.docker\machine\machines\default
SET DOCKER_MACHINE_NAME=default
SET COMPOSE_CONVERT_WINDOWS_PATHS=true
REM Run this command to configure your shell:
REM     @FOR /f "tokens=*" %i IN ('docker-machine env default') DO @%i