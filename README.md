# mongodbclient
Set the following parms when creating the OpenShift app:

$ oc new-app --image-stream=jboss-eap64-openshift --code=https://github.com/bkoz/mongodbclient.git --labels=name=journaldev
$ oc new-app --template=mongodb-ephemeral --params=MONGODB_USER=user,MONGODB_PASSWORD=password,MONGODB_DATABASE=journaldev

