import groovy.json.*
import java.net.URI


def ports = [10443, 20443]
def configFile = new File('/users/paford/Downloads/production.json')
def config = configFile.text
def slurper = new JsonSlurper()
def json = slurper.parseText(config)
json.urlMappings.each {
  if (it.path == '/medius-ui/*'){
    def target = new URI(it.target)
    def port = target.port == ports[0] ? ports[1] : ports[0]
    it.target = "https://${target.host}:$port/"
  }
}
configFile.write JsonOutput.prettyPrint(JsonOutput.toJson(json))
