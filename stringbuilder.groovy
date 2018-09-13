def emraVersion = '1.22.0'
Map props = [
        'java.home': '/usr/java/latest',
        duccHome: '/opt/ducc/ducc_runtime',
        userDir: '/watson/emra/users/emra',
        emrSource: 'Atrius',
       'service-broker-url': 'failover:ssl://dv-gem2-ducc-node-1:61616',
        emra_install_root: '/watson/emra',
        duccSchedulingClass: 'fixed',
        outputBaseDirectory: "/watson/emra/phi/rw/emra/outputs/summarization/${emraVersion}/\${projectLabel}",
        externalOverrides: 'emra-vbc.settings,emra-wcts.settings,emra.settings,nlp.settings',
        PlanAssertionModelPath: "/watson/emra/phi/root/shared_dir/experiments/gemstone_models/2017-43/PLAN/planAssertionCC_oct25/plan_model/"
]

def buffer = ''<<''
props.each { k, v ->
   buffer << "${k} = ${v}\n"
}

println buffer