pipelineJob('sandbox') {
   parameters {
    choiceParameter {
  name('Branch')
  description('Lists branches for integration job')
  filterable(true)
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
   }}
