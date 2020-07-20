1. BeBlet_APK_File.apk

하이브리드 앱으로 빌드한 apk 설치파일입니다. 안드로이드 OS에 설치 가능합니다.
iOS의 경우 Mac OS 기기가 없어 빌드하지 못했습니다.


2. client_build.zip

클라이언트 애플리케이션 빌드를 위한 프로젝트 디렉터리입니다.
cordova CLI와 native 앱 기능을 담당하는 plugin들이 이 프로젝트에 포함되어 있습니다.
package.json에 명령어 및 node.js 모듈에 대한 명세 포함되어 있고, 최상위 디렉터리 plugins 디렉터리에 cordova plugin들이 저장 됩니다.


3. front_end.zip

SPA를 구성하는 Vue.js 프로젝트입니다. 클라이언트에서 실제 로직을 담당하는 소스코드가 포함되어 있으며, package.json 파일에 명령어 및 모듈에 대한 명세가 포함되어 있습니다.
2, 3번 프로젝트 모두 설치 후 npm install 명령어를 통해 module들을 로컬에 설치하는 작업이 필요합니다.

4. spring-webservice.zip

java spring boot로 제작한 서버 애플리케이션입니다. API 서버 및 DB 서버 통합으로 작동합니다.
별 다른 문제가 없어 추가 WAS를 통한 scale out 없이 내장 WAS로만 구동이 되며, 현재 AWS에 배포한 상태이기 때문에 테스트를 위해서는 서버를 켜는 작업이 추가로 필요합니다. 테스트 시 hovy199431@gmail.com로 연락 부탁드립니다.

