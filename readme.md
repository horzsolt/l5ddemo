This is a POC to evaluate the features of linkerd.
The demo consists of:
- a simple SpringBoot REST service (with an arbitrarily set wait time to simulate network lag)
- dockerized instance with env. variables
- kubernetes cluster with 1 node 
- docker image is built by https://github.com/bmuschko/gradle-docker-plugin

Artifacts:
- docker-machine-env.bat: after each console restart should be run (pls change the IP)
- service_a.yml: deployment + service descriptor for ServiceA
	- contains env. setup, health and readiness probes

## Prerequisites
This setup is specifically for windows machines and Hyper-V.
On development machines the easiest way to use minikube which will create a one node cluster locally.

You can download the latest version from here:
https://github.com/kubernetes/minikube/releases

You'll also need kubectl for interacting with you cluster, from cmd.
You can always download from under the changelog of Kubernetes, downloading the Client library from the latest GA release:
https://github.com/kubernetes/kubernetes/blob/master/CHANGELOG.md

https://chocolatey.org/packages/kubernetes-cli

Download docker for windows, we'll use hyper-v containers instead of the legacy toolbox:
https://download.docker.com/win/stable/InstallDocker.msi

## Installation
1. Install docker for windows
2. Create a new Virtual switch (it will be used for the minikube VM), e.g. MyVirtualSwitch
https://docs.docker.com/machine/drivers/hyper-v/
3. Put minikube and kubectl on path
4. Start a new minikube cluster by using the newly created switch:
```
minikube start --vm-driver="hyperv" --memory=1024 --hyperv-virtual-switch="MyVirtualSwitch" --v=7 --alsologtostderr
```

## Alternative way of installation (has not been tested yet)
1. Download Chocolatey (https://chocolatey.org/install)
2. Install docker for windows
```
choco install docker
```
3. Install minikube for windows (it depends on kubectl already)
```
choco install minikube
```
4. Start a new cluster with minikube

## Troubleshooting
* If you are having issues with minikube, throwing errors like the ISO can't be find or having problems by resolving path under you user profile then you might try the following things:
  * Copy minikube.exe and kubectl.exe in the root of your C drive and at it on you path
  * Copy c:\Users\YOU_USER\.minikube to the root of C 
