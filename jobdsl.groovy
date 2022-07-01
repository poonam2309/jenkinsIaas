job('cloudrundeploy') {
    parameters {
        choiceParam('myParameterName', ['option 1 (default)', 'option 3', 'option 5'], 'my description')
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
        activeChoiceReactiveReferenceParam('VPC_CONNECTOR') {
            description('Allows user choose from multiple choices')
            omitValueField()
            choiceType('FORMATTED_HTML')
            groovyScript {
                script {
                script('''def vpc1= "usc1-kcfn01-sss-cor-cnc01" 
                      def vpc2= "use1-kcfn01-sss-cor-cnc01" 
                      if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") 
                      return  "<b>${vpc1}</b><input type='hidden' name='value' value='${vpc1}'>"
                      
                      else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") 
                      return "<b>${vpc2}</b><input type='hidden' name='value' value='${vpc2}'>" 
                      else  
                      return "no match condition exist"''') 
                sandbox(sandbox=true)
                }
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
                script {
                script('''def mailserver1= "10.239.124.83" \r\n
                        def mailserver2= "10.239.124.84" \r\n
                        if(PROJECT=="gcp-kcfn01" && REGION=="us-central1") \r\n
                        return  "<b>${mailserver1}</b><input type='hidden' name='value' value='${mailserver1}'>"
                        else if (PROJECT=="gcp-kcfn01" && REGION=="us-east1") \r\n
                       return  "<b>${mailserver2}</b><input type='hidden' name='value' value='${mailserver2}'>"
                        else \r\n
                        return "no match condition exist"''') 
              sandbox(sandbox=true)
              }
              fallbackScript('"No region"')
            }
            referencedParameter('PROJECT')
            referencedParameter('REGION')
        }
            
    }
}
