# mongodbclient
Creating the OpenShift application.

$ oc new-app --template=mongodb-ephemeral --params=MONGODB_USER=user,MONGODB_PASSWORD=password,MONGODB_DATABASE=journaldev

$ oc new-app --image-stream=jboss-eap64-openshift --code=https://github.com/bkoz/mongodbclient.git --params=MONGODB_USER=user,MONGODB_PASSWORD=password,MONGODB_DATABASE=journaldev

Visit: http://mongodbclient-journaldev.apps.haveopen.com/MongoDBWebapp

This all needs to be in an OpenShift template.
