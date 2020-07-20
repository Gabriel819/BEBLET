package com.example.springwebservice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;

import com.example.springwebservice.domain.member.LoginAuthInfo;
import com.example.springwebservice.domain.member.Member;
import com.example.springwebservice.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoLoginService {
    private static final int KEY_SIZE = 1024;
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPSsWLEFCcQZbQ5Ls0OHqOioSGW9F6aS52CrxIMovwy7bawFNh4wIV3SNQDoPCaN0VW16cBb86UEExz4DcvpJ/39q6wFzYNZDfcdG32cGsAecIioLPiBWrd3G2wlR6n3FL84IN4Mq59DTHpclvprW2C2Gmwx0XYPdNdiz3VBxxVQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM9KxYsQUJxBltDkuzQ4eo6KhIZb0XppLnYKvEgyi/DLttrAU2HjAhXdI1AOg8Jo3RVbXpwFvzpQQTHPgNy+kn/f2rrAXNg1kN9x0bfZwawB5wiKgs+IFat3cbbCVHqfcUvzgg3gyrn0NMelyW+mtbYLYabDHRdg9012LPdUHHFVAgMBAAECgYB1ihdTUC/1OjezQU9pP/E2bBLTQLMaLxP6OQIkCg1Rv6FyjoV5RshC7357gY9yqBVca8/MtnGFNkaRYPDXunPEakGn/orFmghmW9em6obwGErYCwpxbK6/H9ucE73a4XZyTDv1StXwGziIe0oy52cnJs/1tHCD+5FOKSjTsIpVgQJBAPt0ZF31hXmTpiDZ2oyTKVHOk2GuqwvJViyUn1aVbofuzG8HnNfDVvommnDSZAmIj1l7jZkceIec49bIlV3N2fUCQQDTCgRZeGLXjwaSFt3KFEeut9oI+k3WyIO7yDmvHuAB3ozA26zjSGJbDd5e/WHBjLJsb9UOptyPQrdsUxCdbr3hAkBNsDAyN2D9pvh0vdogBHeHpuMyEt0Vzyaor2JrRc/Yf5b51shGgTumHbcplFcD+wUPCHU/EOTMQVTDT0WWrbrhAkAT7shGYxaC4aFvPvWn1o6GtBrF6k5b0QiQPfMk1Te3NXv540XLGdUgYIj2U9xd2Zoon89x2NTQOy7z7kyNch5BAkEAwH4pI7JjMecI4YMFwBuXLLsLwhxGaqert2n4HgZvAq687NMZtWfUiTkhyqzHr8V3mp7zGFYBgRPyPK6DUIXb+Q==";

    @Autowired
    private MemberRepository memberRepository;

    public String getPublicKey () {
        return PUBLIC_KEY;
    }

    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("grant_type=authorization_code");
            sb.append("&client_id=baa4e7d7295bf5dcd24c9292d6038516");
            sb.append("&redirect_uri=http://localhost:8088/ego-login");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();

            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return access_Token;
    }

    public HashMap<String, String> createKeyPairAsString() {
        HashMap<String, String> stringKeyPair = new HashMap<>();
        String pubkey = "HANTA";
        try {
            SecureRandom secureRandom = new SecureRandom(pubkey.getBytes());
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            stringKeyPair.put("publicKey", stringPublicKey);
            stringKeyPair.put("privateKey", stringPrivateKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringKeyPair;
    }

    String encode(String plainData) {
        String encryptedData = null;
        try {
            //평문으로 전달받은 공개키를 공개키객체로 만드는 과정
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePublicKey = Base64.getDecoder().decode(PUBLIC_KEY.getBytes());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            //만들어진 공개키객체를 기반으로 암호화모드로 설정하는 과정
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //평문을 암호화하는 과정
            byte[] byteEncryptedData = cipher.doFinal(plainData.getBytes());
            encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    public String decode(String encryptedData) {
        String decryptedData = null;
        try {
            //평문으로 전달받은 개인키를 개인키객체로 만드는 과정
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePrivateKey = Base64.getDecoder().decode(PRIVATE_KEY.getBytes());
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            //만들어진 개인키객체를 기반으로 암호화모드로 설정하는 과정
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            //암호문을 평문화하는 과정
            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
            decryptedData = new String(byteDecryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }
    public boolean isMember(String userId){
        List<Member> memberList=memberRepository.findAll();
        //userId=decode(userId);
        for(Member member: memberList){
            if(member.getUSER_ID()==userId){
                return true;
            }
        }
        return false;
    }
    public boolean join(LoginAuthInfo info) {
        System.out.println(info.getUserId() + "님 회원가입 완료!");
        return true;
//        if (PUBLIC_KEY.equals(info.getPubKey())) {
//            //res.id 가지고 회원가입 로직
//            //String userId = decode(info.getEncryptedUserId());
//            System.out.println(userId + "님 회원가입 완료!");
//            return true;
//        }
//        return false;
    }

    public boolean login(LoginAuthInfo info) {
        System.out.println(info.getUserId() + "님 로그인 완료!");
        return true;
//        if (PUBLIC_KEY.equals(info.getPubKey())) {
//            //res.id 가지고 로그인 로직
//            //String userId = decode(info.getEncryptedUserId());
//            System.out.println(info.getUserId() + "님 로그인 완료!");
//            return true;
//        }
//        return false;
    }

}