class DefaultConfig {

    String emraVersion
    boolean debug = false
    String root = '/users9/emra-sandbox'
    String apiRoot = '/users5/jenkins'
    String userSandboxRoot = "${root}/emra-sandbox-builds/null"
    String broker = 'tcp://broker1:61616'
    String exec = "gradlew"
    Map core = [
            buildLabel  : "latest",
            deployDir   : "${-> userSandboxRoot}/emra",
            buildDir    : "com.ibm.emra.bld",
            exec        : "${-> core.buildDir}/$exec",
            deployTask  : 'coreDeploy',
            tools       : [
                    buildDir  : "com.ibm.emra.tools.bld",
                    deployTask: 'toolsDeploy',
                    deployDir : "${-> userSandboxRoot}/tools"
            ],
            watsonx     : [
                    location: "${root}/builds/watsonx"
            ],
            functionTest: [
                    runConfig: 'emra-summarization-e2e-CC-TRY-CI.properties',
                    userName : 'jenkins2',
                    jobName  : 'emra-pipeline'
            ],
            // These configuration values for "services" are essentially JED properties:
            // key within each service  = property name.
            "services"  : [
                    all                 : [
                            userDir  : '/users5/jenkins',    // Logs are going here.
                            weeklyId : "${-> emraVersion}",
                            brokerUrl: broker
                    ],
                    summarization       : [
                            task                      : 'coreRegisterSummarization',
                            'summary-service-endpoint': "UIMA-AS:EmrProcessing_${-> emraVersion}:${broker}",
                    ],
                    'relation-detection': [
                            task       : 'coreRegisterRelationDetection',
                            serviceName: "RelationDetection_${-> emraVersion}"
                    ],
                    nlp                 : [
                            task       : 'coreRegisterNLP',
                            serviceName: "EmraNLP_${-> emraVersion}"  // See point 1 in Notes
                    ],
                    'semantic-find'     : [
                            task                : 'coreRegisterSemanticFind',
                            'semantic-find-name': "UIMA-AS:SemanticFind_${-> emraVersion}:${broker}"
                    ],
                    broker              : [
                            task              : 'coreRegisterBroker',
                            'service-endpoint': "CUSTOM:ApplicationBroker-${-> emraVersion}:${broker}"  // This will not work yet.
                    ]
            ]
    ]
    Map api = [
        deployDir : "${apiRoot}/services",
        buildLabel: 'nightly',
        emrStore  : "${apiRoot}/rest-emr-store",
        buildDir  : "com.ibm.emra.api.bld",
        profiles  : 'e2e,db2',
        deployTask: 'apiDeploy',
        exec: "${-> api.buildDir}/$exec"   
    ]
    Map systemTest = [
        duccSchedulingClass: 'prodws01',
        url: 'http://localhost:8443/services/emra'            
    ]
}



String emraVersion = '1.22.0'

def config = new DefaultConfig(emraVersion: emraVersion, userSandboxRoot: '/users9/emra-sandbox/emra-sandbox-builds/paford')

println config.properties
.sort{it.key}
.collect{it}
.findAll{!['class', 'active'].contains(it.key)}
.join('\n')