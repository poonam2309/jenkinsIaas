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
        script('return ["gcp-kcfn01","gcp-cust01","gcp-dsl2","gcp-14s"]')
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
   choiceParameter {
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
            //referencedParameter('PROJECT')
        }
  randomName('')
  filterLength(0)
}
   }}
