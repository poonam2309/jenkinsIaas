
pipeline {
   agent any
    stages {
   stage('Terraform Variable declaration') {
	 steps {
	      sh 'echo "${PROJECT}"'
	      sh 'echo "${REGION}"'
	      sh 'echo "${MAILSERVER}"'
	      sh 'echo "${VPC_CONNECTOR}"'
	      sh '/bin/bash Cloudrun/scripts/cloudrun.sh'
	 }
     }
    }
}
