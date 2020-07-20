package com.example.springwebservice.CreateTable;


import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CreateTable {
    static ResultSet rs, rs2;

    // 테이블이 있는지 확인하고 없으면 생성
    public static void CreateTable(String DbName, Statement stmt, PreparedStatement pstmt, Connection conn) {
        try {

            // cabinet
            String tableSQL = "CREATE TABLE IF NOT EXISTS cabinet("
                    + "CABINET_IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "CABINET_NAME VARCHAR(20) NOT NULL,"
                    + "LATITUDE DOUBLE NOT NULL,"
                    + "LONGITUDE DOUBLE NOT NULL"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);

            // member
            tableSQL = "CREATE TABLE IF NOT EXISTS member("
                    + "USER_ID VARCHAR(20) NOT NULL PRIMARY KEY,"
                    + "USER_NAME VARCHAR(20),"
                    + "USER_PHONE VARCHAR(20),"
                    + "USER_EMAIL VARCHAR(30),"
                    + "USER_PW VARCHAR(20),"
                    + "USER_GENDER VARCHAR(7),"
                    + "USER_NICKNAME VARCHAR(20),"
                    + "STAMP INT DEFAULT 0"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);

            //category
            tableSQL = "CREATE TABLE IF NOT EXISTS category("
                    + "CATEGORY_IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "CATEGORY_NAME VARCHAR(20) NOT NULL"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);


            // item
            tableSQL = "CREATE TABLE IF NOT EXISTS item("
                    + "ITEM_IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "ITEM_NAME VARCHAR(20) NOT NULL,"
                    + "CATEGORY_IDX INT NOT NULL,"
                    + "RENT_PRICE INT NOT NULL DEFAULT 0,"
                    + "START_CABINET_IDX INT,"
                    + "END_CABINET_IDX INT,"
                    + "START_TIME DATETIME,"
                    + "END_TIME DATETIME,"
                    + "STATE INT DEFAULT 1,"
                    + "FOREIGN KEY (CATEGORY_IDX) REFERENCES category(CATEGORY_IDX) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (START_CABINET_IDX) REFERENCES cabinet(CABINET_IDX) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (END_CABINET_IDX) REFERENCES cabinet(CABINET_IDX) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);

            // rent
            tableSQL = "CREATE TABLE IF NOT EXISTS rent("
                    + "IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "ITEM_IDX INT NOT NULL,"
                    + "START_CABINET_IDX INT NOT NULL,"
                    + "END_CABINET_IDX INT NOT NULL,"
                    + "USER_ID VARCHAR(20) NOT NULL,"
                    + "APPROVED_AT DATETIME,"
                    + "AMOUNT INT NOT NULL,"
                    + "START_TIME DATETIME NOT NULL,"
                    + "END_TIME DATETIME NOT NULL,"
                    + "STATE INT DEFAULT 1,"
                    + "CATEGORY_IDX INT NOT NULL,"
                    + "FOREIGN KEY (ITEM_IDX) REFERENCES item (ITEM_IDX) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (START_CABINET_IDX) REFERENCES cabinet (CABINET_IDX) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (END_CABINET_IDX) REFERENCES cabinet (END_CABINET_IDX) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (USER_ID) REFERENCES member(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (CATEGORY_IDX) REFERENCES category(CATEGORY_IDX) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);


            // inquiry
            tableSQL = "CREATE TABLE IF NOT EXISTS inquiry("
                    + "IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "REQUEST_DATE DATETIME NOT NULL,"
                    + "USER_ID VARCHAR(20) NOT NULL,"
                    + "TITLE VARCHAR(20) NOT NULL,"
                    + "CONTENT VARCHAR(300) NOT NULL,"
                    + "FOREIGN KEY (USER_ID) REFERENCES member(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);


            // payment
            tableSQL = "CREATE TABLE IF NOT EXISTS payment("
                    + "IDX INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "PARTNER_ORDER_ID VARCHAR(20) NOT NULL DEFAULT 12345,"
                    + "TID VARCHAR(25) NOT NULL,"
                    + "PARTNER_USER_ID VARCHAR(20) NOT NULL,"
                    + "ITEM_NAME VARCHAR(20) NOT NULL,"
                    + "QUANTITY INT NOT NULL,"
                    + "TOTAL_AMOUNT INT NOT NULL,"
                    + "TAX_FREE_AMOUNT INT NOT NULL,"
                    + "STATE INT DEFAULT 1,"
                    + "START_TIME DATETIME NOT NULL,"
                    + "FOREIGN KEY (PARTNER_USER_ID) REFERENCES member(USER_ID) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")CHARSET = UTF8";
            stmt.execute(tableSQL);

            System.out.println("Table Creation complete.");



        } catch(Exception e) {
            System.out.println("CreateTable err : " + e);
        }
    }
}
