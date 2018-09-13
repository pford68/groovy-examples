String host = 'https://dv-gem-wf-1.ipa.explorys.net/job/emra-test-pipeline/677/console'

def s = host =~ /(dv|qa|pd)-gem/
println s[0][1]