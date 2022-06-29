properties([pipelineTriggers([githubPush()])])
pipeline {
    agent any
        stages {
            stage('Parameters'){
                steps {
                    script {
                    }
                }
            }
            stage('Parameters print'){
                steps {
                    script { sh 'echo test'
                            sh ' echo ${Env}'
                    }
                }
            }
        }   
}
