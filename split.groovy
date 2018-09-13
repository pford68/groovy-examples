def ver = 'R1.25'
def ver2 = 'R1.26.0'

def ver_ = ver.split('\\.')
println ver_.length
println ver2.split('\\.').length

if (ver_.length < 3){
   ver += '.0'
}

println ver