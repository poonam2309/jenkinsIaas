pipelineJob('sandbox') {
   parameters {
    choiceParameter {
  name('PROJECT')
  filterable(false)
  description('Project name')
  choiceType('PT_SINGLE_SELECT')
  script {
    groovyScript {
      script {
        script('''return ["gcp-kcfn01","gcp-cust01","gcp-dsl2","gcp-14s"]''')
        sandbox(true)
      }
      fallbackScript {
        script("return ['Unable to list branches']")
        sandbox(true)
      }
    }
  }
  randomName('')
  filterLength(0)
  }
   cascadeChoiceParameter {
            name('REGION')
      filterable(false)
            description('Allows user choose from multiple choices')
            choiceType('PT_SINGLE_SELECT')
      script{
            groovyScript {
             script {
                script('''if(PROJECT=="gcp-kcfn01") 
                          return["us-central1","us-east1" ] 
                          else if (PROJECT=="gcp-cust01") 
                          return["us-east1"] 
                          else 
                          return ["null"]''')
                sandbox(true)
             }
               fallbackScript {
        script("return ['Unable to list branches']")
        sandbox(true)
      }
            }
            referencedParameters('PROJECT')
        }
  randomName('')
  filterLength(0)
}
      dynamicReferenceParameter {
            name('VPC_CONNECTOR')
            description('Allows user choose from multiple choices')
            omitValueField(true)
            choiceType('ET_FORMATTED_HTML')
      script{
            groovyScript {
             script {
                 script('''def vpc1= "usc1-kcfn01-sss-cor-cnc01" 
                      def vpc3= "use1-kcfn01-sss-cor-cnc01" 
                      if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                      return "<b>${vpc1}</b><input type='hidden' name='value' value='${vpc1}'>"
                      
                      else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") 
                      return "<b>${vpc3}</b><input type='hidden' name='value' value='${vpc3}'>"
                      else  
                      return "no match condition exist"''') 
                sandbox(true)
             }
               fallbackScript {
        script("return ['Unable to list branches']")
        sandbox(true)
      }
            }
            referencedParameters('PROJECT,REGION')
            
        }
  randomName('')

}
       dynamicReferenceParameter {
            name('MAILSERVER')
            description('Allows user choose from multiple choices')
            omitValueField(true)
            choiceType('ET_FORMATTED_HTML')
      script{
            groovyScript {
             script {
                 script('''def mailserver1= "10.239.124.83" 
                        def mailserver2= "10.239.124.84" 
                        if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                        return  "<b>${mailserver1}</b><input type='hidden' name='value' value='${mailserver1}'>"
                        else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") 
                       return  "<b>${mailserver2}</b><input type='hidden' name='value' value='${mailserver2}'>"
                        else 
                        return "no match condition exist for Region"''') 
                sandbox(true)
             }
               fallbackScript {
        script("return ['Unable to list branches']")
        sandbox(true)
      }
            }
            referencedParameters('PROJECT,REGION')
            
        }
  randomName('')

}
     stringParam('RELEASE_NUMBER', '08.05.00', 'Release Version')
      stringParam('INVOKERIMAGE_TAG', '201', 'Invoker image Version')
      stringParam('RUNNERIMAGE_TAG', '202', 'Runner image Version')
   }
definition {
    cpsScm {
      scm {
        git {       
            remote {
            github('ukg-cloud/cloud-automation', 'https', 'github.com')
            credentials('github-auth-svc')
          }
          branch('*/poonam')
        }
      }
      scriptPath("Cloudrun/Jenkinsfile")
     // lightweight(true)
    }
  }
}
