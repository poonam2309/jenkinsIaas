pipelineJob('cloud_automation/terraform/cloudrun/cloudrundeploy1') {
    parameters {
      stringParam('RELEASE_NUMBER', '08.05.00', 'Release Version')
      stringParam('INVOKERIMAGE_TAG', '201', 'Invoker image Version')
      stringParam('RUNNERIMAGE_TAG', '202', 'Runner image Version')
    }
            
    }
