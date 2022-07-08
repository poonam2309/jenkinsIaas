pipelineJob('sandbox') {
   parameters {
    activeChoiceParam('pk') {
    delegate.description(this.description)
    delegate.choiceType('RADIO')
    delegate.groovyScript {
        script('[test1,test2]') {
            sandbox()
        }
    }
}
   }}
