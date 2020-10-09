
 import codePush from 'react-native-code-push'
import React, {useState, useEffect} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
  Button,
  TextInput,
  Image,
  NativeModules, 
  TouchableOpacity,
  NativeEventEmitter

} from 'react-native';

import {
  Colors
} from 'react-native/Libraries/NewAppScreen';

let codePushOptions = { checkFrequency: codePush.CheckFrequency.ON_APP_START };


const App: () => React$Node = () => {

  const [name, setName] = useState('')
  const [location, setLocation] = useState('')
  const [isOn, setStatus] = useState('false')

  const turnOn = () => {
    NativeModules.BridgingModule.turnOn();
    updateStatus()
    }
  
  const turnOff = () => {
      NativeModules.BridgingModule.turnOff();
      updateStatus()
  }
  
  const sendAPIRequest = () => {
    NativeModules.BridgingModule.sendAPIRequest();
  }


  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NativeModules.BridgingModule);
  

  let eventListener = eventEmitter.addListener('onClick', (event) => {
      console.log("OnClickEvent EventParam. -->", event.eventParam);
      console.log("Sending get comment api request"); 
      sendAPIRequest()
 });
   
    

  });

  const updateStatus = () => {
    NativeModules.BridgingModule.getStatus( (error, isOn)=>{
      setStatus(isOn)
    })  
  } 

  return (
    <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <ScrollView
          contentInsetAdjustmentBehavior="automatic"
          style={styles.scrollView}>
          
          {global.HermesInternal == null ? null : (
            <View style={styles.engine}>
              <Text style={styles.footer}>Engine: Hermes</Text>
            </View>
          )}
          <View style={styles.body}>
          { isOn ? <TouchableOpacity onPress={turnOff}>
            <Image style={styles.imageStyle} source = {require('./assets/t.png')} />  
          </TouchableOpacity> : <TouchableOpacity onPress={turnOn}>
            <Text style={styles.sectionDescription}>  Show T Logo </Text>
          </TouchableOpacity>
          
          }
          
        
          
            
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 01</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 2</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 3</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 4</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 5</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 6</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 7</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Banner 8</Text>
              <Text style={styles.sectionDescription}>
                Here goes the <Text style={styles.highlight}>description</Text> of the banner.
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Enter your details to avail the offers </Text>
              <View>
                <Text style={styles.text}> Enter your name </Text>
                <TextInput 
                  style={styles.input}
                  autoCapitalize="none"
                  autoCorrect={false}
                  value={name}
                  onChangeText = {newValue => setName(newValue)}
                />
            <Text style={styles.text}> Location </Text>
            <TextInput 
              style={styles.input}
              autoCapitalize="none"
              autoCorrect={false}
              value={location}
              onChangeText = {newValue => setLocation(newValue)}
            />    

   

            <Button
              style= {styles.button}
              title= "Submit Form"
              onPress = {() => console.log("Button pressed") } />            
            

          </View>
            </View>
            
          </View>
        </ScrollView>
      </SafeAreaView>
    </>
  );
};

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
  text: {
    fontSize: 20,
    alignSelf: "center",
    marginTop: 5
  },
  align: {
    alignContent: "center"
  },
  input: {
    borderColor: 'black',
    borderWidth: 2,
    padding: 3,
    marginHorizontal: 20,
    marginVertical: 10
},
imageStyle : {
  height: 100,
  width: 200,
  resizeMode: "center"
}
});

 export default codePush(codePushOptions)(App);
//export default App;