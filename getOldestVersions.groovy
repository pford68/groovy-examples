List versions = ['data_root_1.21.0', 'data_root_1.20.0','data_root_1.19.0', 'data_root_1.18','data_root_1.17.0']
List versions2 = ['data_root_1.21.0']
List versions3 = ['data_root.zip']


def extractVersion(String str){
   str.findAll(/\d+/)
}

def getOldest(String path, Closure filter, cutoff=2){
    def result = new File(path)
        .listFiles()?.findAll(filter)
        ?.sort {it.lastModified()}
    result.size() - cutoff > 0 ? result.take(cutoff) : []
 }
 
 def getOldestVersions(String path, Closure filter, int cutoff=2){
    def result = new File(path)
        .listFiles()
        ?.findAll(filter)
        ?.collect {
           def semver = it.name.findAll(/\d+/) ?: []
           [ file: it, semver: semver*.toInteger() ]
        }?.sort { a, b ->
            a.semver[0] <=> b.semver[0] ?: a.semver[1] <=> b.semver[1] ?: a.semver[2] <=> b.semver[2]
        }
    result.size() - cutoff > 0 ? result.take(result.size() - cutoff).collect { it.file } : []
 }
 
 def sortByVersion(String path, Closure filter){
    new File(path)
            .listFiles()
            ?.findAll(filter)
            ?.collect {
        def semver = it.name.findAll(/\d+/) ?: []
        [ file: it, semver: semver*.toInteger() ]
    }?.sort { a, b ->
        a.semver[0] <=> b.semver[0] ?: a.semver[1] <=> b.semver[1] ?: a.semver[2] <=> b.semver[2]
    }?.collect {
       it.file
    }
}

 //println getOldest('/users/paford/Downloads'){ it.name.startsWith('data_root') }
 //println getOldestVersions('/users/paford/Downloads'){ it.name.startsWith('data_root') }
 println sortByVersion('/users/paford/Downloads'){ it.name.startsWith('data_root') }.dropRight(2)
 println sortByVersion('/users/paford/Downloads'){ it.name.startsWith('data_test') }.dropRight(2)
 
 
// println extractVersion(versions[0])
// println extractVersion(versions3[0])