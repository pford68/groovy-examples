import groovy.time.*
ports = 9000..9090
def startTime = new Date()
ports.each { port ->
    try {
        Socket socket = new Socket("127.0.0.1",port)
        println "Port ${port}: In use \n"
    }catch (e){
         println "Port ${port} is available."
    }
}
def endTime = new Date()
TimeDuration duration = TimeCategory.minus(endTime, startTime)
println "It took ${duration} to run this port scan..."