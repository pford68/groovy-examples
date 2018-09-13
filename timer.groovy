def now(){
   return new Date().getTime()
}

def execute(){
    def count = 0
    Timer timer = new Timer()
    timer.schedule({
        println now()
        ++count
        if (count >= 10){
           println 'Done!'
           timer.cancel()
        }
    } as TimerTask, new Date(), 1000 * 60) 
}

execute()