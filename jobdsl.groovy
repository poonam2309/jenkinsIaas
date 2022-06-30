job('cloudrundeploy') {
    parameters {
        choiceParam('myParameterName', ['option 1 (default)', 'option 2', 'option 3'], 'my description')
        activeChoiceParam('PROJECT') {
            description('Allows user choose from multiple choices')
            choiceType('SINGLE_SELECT')
            groovyScript {
                script('["gcp-kcfn01","gcp-cust01"]')
                fallbackScript('"No Region selected"')
            }
        }
         activeChoiceReactiveParam('REGION') {
            description('Allows user choose from multiple choices')
            choiceType('SINGLE_SELECT')
            groovyScript {
                script('if(PROJECT=="gcp-kcfn01") return["us-central1","us-east1" ] else if (PROJECT=="gcp-cust01") return["us-east1"] else return ["null"]')
                fallbackScript('"fallback choice"')
            }
            referencedParameter('PROJECT')
        }
            
    }
}
