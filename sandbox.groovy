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
        script("return ['The list of branches']")
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
