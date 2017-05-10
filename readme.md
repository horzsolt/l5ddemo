This is a POC to evaluate the features of linkerd.
The demo consists of:
- a simple SpringBoot REST service (with an arbitrarily set wait time to simulate network lag)
- dockerized instance with env. variables
- kubernetes cluster with 1 node 
- docker image is built by https://github.com/bmuschko/gradle-docker-plugin

During the development we used:
- native Windows Docker https://docs.docker.com/docker-for-windows/install/
- 

Artifacts:
- docker-machine-env.bat: after each console restart should be run (pls change the IP)
- service_a.yml: deployment + service descriptor for ServiceA
	- contains env. setup, health and readiness probes
	
	
Useful commands:
- 

Useful links:
