List versions = ['data_root_1.21.0', 'data_root_1.20.0','data_root_1.19.0', 'data_root_1.18','data_root_1.17.0']
List versions2 = ['data_root_1.21.0']
List versions3 = ['data_root.zip']


List getLatestVersions(def versions, int size=2){
   versions.collect {  it.tokenize('.')[1] }
   .findAll{ it?.isInteger() }
   ?.sort()
   ?.takeRight(size)
}


println getLatestVersions(versions)
println getLatestVersions(versions, 3)
println getLatestVersions(versions2)
println getLatestVersions(versions2,3)
println getLatestVersions(versions3,3)


def getFiles(String filter){
  new File('/users/paford//Downloads')
   .listFiles()?.findAll { it.name.startsWith(filter) }?.collect {
      it.name
   }
}

def scalaVersions = getFiles('scala')   
println getLatestVersions(scalaVersions)


def emraVersions = getFiles('emra')
println emraVersions
println getLatestVersions(emraVersions)