import React, {useState} from 'react';
import CheckBox from '@react-native-community/checkbox';
import {
  View,
  Text,
  TouchableOpacity,
  Image,
  SafeAreaView,
  StyleSheet,
  TextInput,
  ScrollView,
  Alert,
} from 'react-native';
import UserIcon from '../assets/icons/UserIcon.png';
import axios from 'axios'
const RegisterScreen = ({navigation}) => {
  const [StudentID, PutStudentID] = React.useState("default");
  const [Nickname, PutNickname] = React.useState("default");
  const [Pwd, PutPwd] = React.useState("default");
  const [Pwdd, PutPwdd] = React.useState("default");
  const [toggleCheckBox, setToggleCheckBox] = useState(false);
  let status = false;
  const regi = () => 
  {
    toggleCheckBox==true ? Tohome() : Alert.alert("약관 동의필요"); 
    
  };
  const Tohome = () =>
  {
    status == true ? navigation.navigate('Home') :  Alert.alert("중복 여부를 확인해주세요");
  }
  const [post, setpost] = useState('post dummy');
  async function Apiregister(){
    axios.post("http://10.0.2.2:8080/join",
    {
      studentId:StudentID,
      name:Nickname,
      password:Pwd
    })
    .then(function(res){
      
      setTimeout(() => {
        setpost(res.status);
      }, 2000);
      Alert.alert("가입 가능합니다")
      status=true;
    })
    .catch(function(error){
      setTimeout(() => {
        setpost(error.status);
      }, 2000);
      Alert.alert("중복된 학번입니다")
      status=false;
})};
  return (
    <ScrollView>
    <SafeAreaView style={Styles.Registerscreen}>
      <View style={{justifyContent: 'center', alignItems: 'center'}}>
        <Image
          source={UserIcon}
          style={{
            width: 180,
            height: 180,
            margin: 30,
          }}
        />
      </View>
      <View>
        <View >
          {/* <Text style={Styles.StudentId}>학번 입력</Text> */}
          <TextInput
            style={Styles.input5}
            PutStudentID={PutStudentID}
            onChangeText={(value) =>
              {
                PutStudentID(value);
              }}
            placeholder="학번입력"
            placeholderTextColor="#808080"
            keyboardType="url"
          />
          {/* <TextInput
            style={Styles.OverlapContainer1}
            PutOverlap={PutOverlap}
            value={Overlap}
            placeholder="중복 확인"
            placeholderTextColor="#ffffff"
            keyboardType="url"
          /> */}
          
        </View>
      </View>
      <View>
        <View >
          {/* <Text style={Styles.NickName}>닉네임 입력</Text> */}
          <TextInput
            style={Styles.input5}
            secureTextEntry={true}
            onChangeText={(value) =>
              {
                PutNickname(value);
              }}
            placeholder="이름 입력"
            placeholderTextColor="#808080"
            keyboardType="url"
          />
          
        </View>
      </View>
      <View>
        <TextInput
          style={Styles.input5}
          secureTextEntry={true}
          onChangeText={(value) =>
            {
              PutPwd(value);
            }}
          placeholder="비밀번호 입력"
          placeholderTextColor="#808080"
        />
        <TextInput
          style={Styles.input5}
          secureTextEntry={true}
          onChangeText={(value) =>
            {
              PutPwdd(value);
            }}
          placeholder="비밀번호 확인"
          placeholderTextColor="#808080"
          
        />
      </View>
      <View
        style={{
          flexDirection: 'row',
          alignItems: 'center',
          marginLeft: 42,
          marginTop: 20,
        }}>
        <CheckBox
          style={Styles.CheckBox}
          disabled={false}
          value={toggleCheckBox}
          onValueChange={newValue => setToggleCheckBox(newValue)}
        />
        <Text style={Styles.Terms}>약관 동의</Text>
      
      </View>
     
      <View style={Styles.TermsBox}>
        <Text style={Styles.TermsBoxText}>Demo</Text>
      </View>
      <View
        style={{
          justifyContent: 'center',
          alignItems: 'center',
          margin: 18,
        }}>
          <TouchableOpacity
          onPress={Apiregister}
            style={{
              height: 45,
              width: 94,
              borderWidth: 1,
              borderRadius: 10,
              backgroundColor: '#4B5965',
              marginLeft: 10,
              justifyContent: 'center',
            }}>
            <Text
              style={{
                color: 'white',
                textAlign: 'center',
                fontFamily: 'Inter-SemiBold',
              }}>
              중복 확인
            </Text>
          </TouchableOpacity>
        <TouchableOpacity
          style={{
            borderWidth: 1,
            width: 200,
            height: 50,
            borderRadius: 10,
            backgroundColor: '#BB1029',
            justifyContent: 'center',
            alignItems: 'center',
            textAlign: 'center',
          }}
          onPress={regi}>
          <Text
            style={{
              color: 'white',
              fontFamily: 'Inter-SemiBold',
              fontSize: 20,
            }}>
            회원가입
          </Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
    </ScrollView>
  );
};

const Styles = StyleSheet.create({
  StudendIdContainer: {
    // marginLeft: 50,
    flexDirection: 'row',
    // marginTop: 100,
    justifyContent: 'center',
  },
  input3: {
    height: 35,
    margin: 0,
    //  marginLeft: 60,
    borderWidth: 1,
    padding: 10,
    color: 'black',
    borderRadius: 10,
    fontFamily: 'Inter-SemiBold',
    fontSize: 14,
    width: 201,
    height: 45,
    backgroundColor: 'white',
  },
  OverlapContainer1: {
    width: 94,
    height: 45,
    borderWidth: 1,
    color: 'black',
    borderRadius: 10,
    fontFamily: 'Inter-SemiBold',
    fontSize: 14,
    backgroundColor: 'white',
    marginLeft: 10,
    textAlign: 'center',
    backgroundColor: '#4B5965',
  },
  NicknameContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 12,
  },
  input4: {
    height: 35,
    margin: 0,
    //  marginLeft: 60,
    borderWidth: 1,
    padding: 10,
    color: 'black',
    borderRadius: 10,
    fontFamily: 'Inter-SemiBold',
    fontSize: 14,
    width: 201,
    height: 45,
    backgroundColor: 'white',
  },
  OverlapContainer2: {
    width: 94,
    height: 45,
    borderWidth: 1,
    color: 'black',
    borderRadius: 10,
    fontFamily: 'Inter-SemiBold',
    // fontSize: 14,
    textAlign: 'center',
    backgroundColor: '#4B5965',
    marginLeft: 10,
  },
  input5: {
    
    borderWidth: 1,
    padding: 10,
    color: 'black',
    borderRadius: 10,
    fontFamily: 'Inter-SemiBold',
    fontSize: 14,
    width: 300,
    height: 45,
    marginLeft: 42,
    marginTop: 12,
    backgroundColor: 'white',
    
  },
  CheckBox: {
    width: 20,
    height: 20,
  },
  Terms: {
    textDecorationLine: 'underline',
    fontSize: 18,
    margin: 10,
  },
  TermsBox: {
    width: 300,
    height: 100,
    borderWidth: 1,
    marginLeft: 43,
  },
  TermsBoxText: {
    textAlign: 'auto',
  },
});

export default RegisterScreen;
