# mongodbclient
Creating the OpenShift application.

$ oc new-project journaldev

$ oc new-app --template=mongodb-ephemeral --param=MONGODB_USER=user,MONGODB_PASSWORD=password,MONGODB_DATABASE=journaldev

$ oc new-app --image-stream=jboss-eap64-openshift --code=https://github.com/bkoz/mongodbclient.git --param=MONGODB_USER=user,MONGODB_PASSWORD=password,MONGODB_DATABASE=journaldev

$ oc expose service mongodbclient

Visit: http://mongodbclient-journaldev.apps.haveopen.com/MongoDBWebapp

This all needs to be in an OpenShift template.
