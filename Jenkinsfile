properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'Environment',
      script: [
        $class: 'ScriptlerScript',
        scriptlerScriptId:'Environments.groovy'
      ]
    ],
    [
      $class: 'CascadeChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'Host',
      referencedParameters: 'Environment',
      script: [
        $class: 'ScriptlerScript',
        scriptlerScriptId:'HostsInEnv.groovy',
        parameters: [
          [name:'Environment', value: '$Environment']
        ]
      ]
   ]
 ])
]

pipeline {
  parameters {
        choice(
            name: 'BranchName',
            choices: "${BRANCH_NAMES}",
            description: 'to refresh the list, go to configure, disable "this build has parameters", launch build (without parameters)to reload the list and stop it, then launch it again (with parameters)'
        )
    }
  agent any
  stages {
    stage('Build') {
      steps {
        echo "${params.Environment}"
        echo "${params.Host}"
       // echo "${params.BranchName}"
      }
    }
  }
}
