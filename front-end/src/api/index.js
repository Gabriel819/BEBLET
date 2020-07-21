import axios from 'axios';
// import { JSEncrypt } from "jsencrypt";

// CryptoJS
// var CryptoJS = require("crypto-js");
// var message = "123151563";  // id
// var key = "ZouFzBswbrhzhbKONckeJzCCyfoMiPWF"; // key
// var encrypt = CryptoJS.AES.encrypt(message, key); // 암호화 method
// console.log(encrypt.toString());



const instance = axios.create({
  baseURL: "http://13.125.236.67:8080",
  // baseURL: "http://124.50.218.182:8080/test-server",
  // baseURL: "http://192.168.219.108:8080/test-server",
  // baseURL: "http://localhost:8080"
});


export const cancelOrReturn = function(user_id, rent_idx) {
  let infoObj = {
    user_id: user_id,
    rent_idx: rent_idx
  }
  return instance.post("/CancleOrReturn", infoObj);
}

export const rentListReq = function (userId) {
  let userInfo = {
    user_id: userId
  }
  return instance.post("/returnRentList", userInfo)
    .then((res) => {
      return res.data;
    })
    .catch((error) => {
      window.alert(error);
    })
}

export const getUserInfo = function(user_id) {
  var reqObj = {
    user_id: user_id
  }
  return instance.post('/userInfo', reqObj);
}

export const returnItem = function(userId, itemIdx) {
  var returnItemObj = {
    user_id: userId,
    item_idx: itemIdx
  }
  return instance.post("/returnItem", returnItemObj);
}

export const kakaoCancel = function(userId, RENT_IDX) {
  var kakaoCancelObj = {
    user_id: userId,
    rent_idx: RENT_IDX
  }
  return instance.post("/kakaoCancel", kakaoCancelObj);
}

export const question = function(quesObj) {
  return instance.post("/inquire", quesObj);
}

export const rentCommit = function (rentObj) {
  return instance.post("/apply", rentObj)
      .then((res) => {
        kakaoPay(rentObj)
      });
}

export const kakaoPay = function (rentObj) {
  return instance.post("/kakaoPay", rentObj)
      .then((res) => {
        return res.data;
      }).then((payURL) => {
        var ref = cordova.InAppBrowser.open(
          payURL,
          "_system",
          "hideurlbar=yes"
        );
      }).
      catch((error) => {
        window.alert(error);
      })
}

export const sendAuthInfo = function(userData) {
  window.alert(JSON.stringify(userData));
  return instance.post("/getKakaoAuth", userData);
};

export const recommendCabinet = function(rentObj) {
  return instance.post("/recommendCabinet", rentObj)
    .then(function(res) {
      return res.data;
    })
    .then(function(availableCabinetArr){
      return availableCabinetArr;
    }).catch((error) => {
      window.alert(error);
    })
}


export const kakaoLogin = function() {
  let loginOptions = {
    authTypes: [1],
  };
  
  let failed = function () {
    window.alert("로그인에 실패했습니다.");
  }
  let success = function (kakaoRes){
    var userId = kakaoRes.id;
    var userNickName = kakaoRes.properties.nickname;
    let authObj = {
          userId: userId,
          nickname: userNickName,
        };

    // window.alert(JSON.stringify(kakaoRes));
    instance.post("/joinAndLoginRequest", authObj)
        .then(function (loginRes) {
          return loginRes.data;
        })
        .then(function (parsedRes) {
          if (parsedRes.loggedIn === true) {
            window.localStorage.setItem("id", userId);
            window.localStorage.setItem("nickname", userNickName);
          }
          // console.log(parsedRes.loggedIn);
          location.reload();
          return parsedRes;
        }).catch((error) => {
          // console.log(error);
          window.alert(error);
        });
  }
  KakaoCordovaSDK.login(loginOptions, success, failed);
}



// var sendAuthObjToServe = async function(unEncryptedUserId) {
// 	await getAuthObj(unEncryptedUserId);
// };

// var getEncryptedId = async function (unEncryptedUserId) {
//   var encryptedUserId = await
// }