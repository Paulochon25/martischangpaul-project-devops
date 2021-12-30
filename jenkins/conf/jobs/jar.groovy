#!groovy
println('Import Job CI/Jar')
def pipelineScript = new File('/var/jenkins_config/jobs/jar-pipeline.groovy').getText("UTF-8")

pipelineJob('CI/Jar') {
    description("Jenkins Pipeline")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description("Select branch fron github repo")
            trim(false)
        }
        booleanParam{
            name('SKIP_TESTS')
            defaultValue(false)
            description("Skip tests Unit & Integration")
        }
        choiceParam('VERSION_TYPE', ['SNAPSHOT', 'RELEASE'], "SNAPSHOT or RELEASE")
        stringParam {
            name('VERSION')
            defaultValue("SNAPSHOT")
            description("Jar type")
            trim(false)
        }
        stringParam {
            name('VERSION')
            defaultValue('SB3T-1.0.0-SNAPSHOT')
            description("Jar version")
            trim(false)
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}