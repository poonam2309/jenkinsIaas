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
      filterable(false)
            description('Allows user choose from multiple choices')
            choiceType('ET_FORMATTED_HTML')
      script{
            groovyScript {
             script {
                 script('''def vpc1= "usc1-kcfn01-sss-cor-cnc01" 
                      def vpc3= "use1-kcfn01-sss-cor-cnc01" 
                      if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                      return  "<b>${vpc1}</b><input type='hidden' name='value' value='${vpc1}'>"
                      
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
            referencedParameters('PROJECT','REGION')
        }
  randomName('')
  filterLength(0)
}
   }}
