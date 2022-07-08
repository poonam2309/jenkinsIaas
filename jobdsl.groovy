pipelineJob('cloudrundeploy') {
    parameters {
        activeChoiceParam('PROJECT') {
            description('Allows user choose from multiple choices')
            choiceType('SINGLE_SELECT')
            groovyScript {
                script('["gcp-kcfn01","gcp-cust01","gcp-dsl2","gcp-14s"]')
                fallbackScript('"No Region selected "')
            }
        }
         activeChoiceReactiveParam('REGION') {
            description('Allows user choose from multiple choices')
            choiceType('SINGLE_SELECT')
            groovyScript {
                script('''if(PROJECT=="gcp-kcfn01") 
                          return["us-central1","us-east1" ] 
                          else if (PROJECT=="gcp-cust01") 
                          return["us-east1"] 
                          else 
                          return ["null"]''')
                GroovySandbox()
                fallbackScript('"fallback choice"')
            }
            referencedParameter('PROJECT')
        }
        activeChoiceReactiveReferenceParam('VPC_CONNECTOR') {
            description('Allows user choose from multiple choices')
            omitValueField()
            choiceType('FORMATTED_HTML')
            groovyScript {
               // script {
                script('''def vpc1= "usc1-kcfn01-sss-cor-cnc01" 
                      def vpc3= "use1-kcfn01-sss-cor-cnc01" 
                      if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                      return  "<b>${vpc1}</b><input type='hidden' name='value' value='${vpc1}'>"
                      
                      else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") 
                      return "<b>${vpc3}</b><input type='hidden' name='value' value='${vpc3}'>" 
                      else  
                      return "no match condition exist"''') 
              // sandbox(boolean sandbox = true)
               // }
                fallbackScript('"no project"')
                
            }
            referencedParameter('PROJECT')
            referencedParameter('REGION')
        }
        activeChoiceReactiveReferenceParam('MAILSERVER') {
            description('Allows user choose from multiple choices')
            omitValueField()
            choiceType('FORMATTED_HTML')
            groovyScript {
            //    script {
                script('''def mailserver1= "10.239.124.83" 
                        def mailserver2= "10.239.124.84" 
                        if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                        return  "<b>${mailserver1}</b><input type='hidden' name='value' value='${mailserver1}'>"
                        else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") 
                       return  "<b>${mailserver2}</b><input type='hidden' name='value' value='${mailserver2}'>"
                        else 
                        return "no match condition exist for Region"''') 
            //  sandbox(boolean sandbox = true)
             // }
              fallbackScript('"No region"')
            }
            referencedParameter('PROJECT')
            referencedParameter('REGION')
        }
      stringParam('RELEASE_NUMBER', '08.05.00', 'Release Version')
      stringParam('INVOKERIMAGE_TAG', '201', 'Invoker image Version')
      stringParam('RUNNERIMAGE_TAG', '202', 'Runner image Version')
            
    }
  /*  scm 
    {
       github('https://github.com/ukg-cloud/cloud-automation', 'poonam')
       scriptPath('Jenkinsfile')
       credentials('github-ci-key')
    }*/
   definition {
        cps {
            script(readFileFromWorkspace('jobdsl.groovy'))
            sandbox()
        }
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
