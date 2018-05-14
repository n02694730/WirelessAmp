from firebase import firebase
import time
firebase = firebase.FirebaseApplication('https://wirelessamp.firebaseio.com/', None)
while True:
    volume = firebase.get('/Presets/current', 'volume')
    tone = firebase.get('/Presets/current', 'tone')
    print ("Volume: " + volume + "\nTone: " + tone +"\n")
    
    time.sleep(3)
    #GPIO.setup(25, GPIO.OUT, initial=GPIO.LOW)
    #GPIO.output(25, GPIO.HIGH)



