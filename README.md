# BEBLET
2020 Capstone Project. Tablet Rental App using Spring Boot, Vue.js, Apache Cordova, AWS, MYSQL


# 1. BeBlet_APK_File.apk

Apk executable file built as Hybrid App.

You can install on Andriod OS.

In case of iOS, cannot build one as we don't have any MAC OS device.

I didn't upload this file because of security reasons.



# 2. client_build

Project directory for building client application.

Cordova CLI and Plugins handling Native App functions included.

In package.json, details of commands and node.js module included.

In top-level folder's plugins directory, cordova plugins are stored.



# 3. front_end

Vue.js project consists of SPA.

Source codes for handling cliend's logics are included.

Details of commands and module are included in package.json.

After installing projects #2, 3, installing in local by 'npm install' command is needed.



# 4. spring-webservice

Server application using Java SpringBoot framework.

works as combination of API server and DB server.

No need to scaling our through additional WAS.

It is already issued on AWS and need to operate server before test.

You cannot test the files without server on.
