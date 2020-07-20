package com.example.springwebservice.mybatis;

//DAO(database access object)클래스 -> Mapper인터페이스 -> Mapper XML -> DB
//SqlSession을 통해 자바에서 데이터베이스에 쿼리를 전달

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisConfig {
    private static SqlSessionFactory sqlSessionFactory;

    //static 초기화 블럭 중요

    static {
        String resource = "mybatis-config.xml";
        //Mybatis 환경설정 파일 읽기. src 경로에 저장.
        /*
         *xml파일
            보여주는 언어 라는뜻, 우리가 사용하는 자바와는 다른 형식의 문법
            다른형식의 문법 파일을 자바가 알수 있게 읽어온다.

            Reader reader = Resources.getResourceAsReader(resource);
            파일을 읽어올수 있는 스트림 같은 것
         */

        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            //내가 필요로하는 정보가 xml파일에 다있다
            //build를 통해 그 정보를 읽어와서
            //mybatis 설정정보가 들어있는 객체의 형태로 sqlSessionFactory를 딱하나만 만들겠다.
            //설정 정보는 변하면 안된다. 그정보들이 바뀔가능성이 있기때문에 한번만 만들어지고 유지될수있게 ~

            reader.close(); //스트림과 같기때문에 finally{}에서 닫아야한다.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //main 함수에서 부른 함수, 그 객체는 한번 만들어놓고 static 영역에 놓아둔 데이터
    //객체를 동일한 값으로 공유해서 사용하기위해서
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}