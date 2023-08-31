# YouQuiz_Backend_API_Server
	
## 프로젝트 설명
YouQuiz 프로젝트는 2023년 멋쟁이사자처럼11기 중앙해커톤 출전을 위해 개발한 서비스이며
대한민국 청소년들의 디지털 문해력 향상을 위한 교육용 웹사이트로써
실제 youtube 영상과 댓글을 보고, 관련된 문제를 풀면서 디지털 문해력을 기르는 서비스이다.

본 레포지토리는 해당 서비스의 동작을 위한 api를 제공하는 backend API 서버로<br/>
서비스 자체 로그인, 회원가입<br/>
학습할 문제의 목록을 확인<br/>
학습할 문제를 확인<br/>
문제를 풀고 정답을 저장<br/>
자신의 정답에 대한 피드백<br/>
등 의 기능을 제공하는 16개의 API를 제공한다.<br/>

### API 명세서 노션페이지
<a href="https://www.notion.so/API-1e699ca81d11435a86296438df798b39?pvs=4"><img src="https://img.shields.io/badge/Notion-FFFFFF?style=for-the-badge&logo=Notion&logoColor=black"></a>

### 사용된 기술
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">  <img src="https://img.shields.io/badge/Naver-03C75A?style=for-the-badge&logo=Naver&logoColor=white"> 

네이버 클라우드 플랫폼 (NCP) 를 사용하였고
본래 도커와 Github action 을 통한 배포를 시도했으나 실패하여<br/>
백엔드서버에는 직접 인스턴스 서버에 java를 설치하였고<br/>
DB서버는 네이버 클라우드에서 제공하는 SaaS를 이용하였다.<br/>
 
### 아키텍쳐 노션페이지
<a href="https://www.notion.so/5d04f492bc3442ff8ddcad1437f7d734?pvs=4"><img src="https://img.shields.io/badge/Notion-FFFFFF?style=for-the-badge&logo=Notion&logoColor=black"></a>


## 프로젝트 설치 및 실행 방법
초반 프로젝트 계획 시, 단일 시스템(클라우드 인스턴스)으로 Docker (Nginx + React + SpringBoot + MySQL) 를 통해 서비스를 구축하려 시도하였다.
그러나, 시스템의 컴퓨팅 파워 한계로 인해 단일 시스템에서 여러 도커 이미지를 구동할 수 없어, 부득이하게 기능별로 시스템을 분리하여 운용하였다.
또한 보안 유지에 있어서 적절한 권한 설정(chown, chmod)은 필수적이나, 상용 서비스 목적으로 작업하는 것이 아니므로, 권한을 비롯한 보안 설정은 고려하지 않았다.

1. FrontEnd - Nginx + React / Port Open 80, 443 (SSL)
- OS: CentOS 7.6
- Nginx: 1.20.1
- NPM: 6.4.18
- NodeJS: 14.21.3
  
2. BackEnd - SpringBoot / Port Open 8080
- OS: 상동
- Java: 17.0.8 (LTS)

3. Database - MySQL / Port Open 3306
- SaaS (Powered by NAVER Cloud Platform)
<br>
<br>
### 1. System 기본 설정 - FrontEnd
cf> root 계정으로 로그인하지 않은 경우, yum 명령이 잘 실행되지 않을 수 있음.
<br>이 경우 명령어 앞에 sudo 를 붙여주거나, su 명령어를 통해 root 계정으로 진입 후 작업.

I. 시스템 패키지 업데이트
```
yum update -y
```
II. epel-release Repository 설치
   - CentOS 에서 기본으로 제공하는 Repository 에는 NPM & NodeJS 제공되지 않아, 별도의 Repository 필요함.
```
yum install -y epel-release
```  
III. NPM, NodeJS 설치
```
yum install -y npm nodejs
```
IV. yum-utils 설치
   - nginx 설치를 위한 Repository 활성화에 사용
```
yum install -y yum-utils
```
V. Nginx Repository 설정
   - nginx.repo 파일 작성
```
vi /etc/yum.repos.d/nginx.repo
```
vi Editor 실행 후 Insert 를 눌러 입력 모드로 전환한 뒤, 아래 내용을 붙여넣고 저장. <br> <blockquote>cf> 붙여넣기: Shift + Insert / 저장: ESC → : (콜론) → wq! (엔터)</blockquote>
```
[nginx-stable]
name=nginx stable repo
baseurl=http://nginx.org/packages/centos/$releasever/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true

[nginx-mainline]
name=nginx mainline repo
baseurl=http://nginx.org/packages/mainline/centos/$releasever/$basearch/
gpgcheck=1
enabled=0
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true
```
VI. Repository 버전 선택
   - nginx 버전은 Mainline 의 두 가지 버전이 존재.
   - Mainline 버전에 버그 수정, 보안 패치 등이 우선 적용.
   - Stable 버전은 Mainline 버전에서 발견된 Bug Fix, third-party 모듈 등을 포함.
   - Nginx 공식 설명에는 Mainline 버전 사용을 권장.
```
yum-config-manager --enable nginx-mainline
```
VII. Nginx 설치
```
yum install -y nginx
```
VIII. Version 확인
```
nginx -v
```
```
node -v
```
```
npm -v
```
<blockquote>cf> nginx 설정파일 경로: /etc/nginx/nginx.conf<br>cf> 도메인 별 개별 설정하려는 경우, /etc/nginx/conf.d/[도메인명].conf 파일에 설정내용 작성 후 저장<br>cf>설정 적용되지 않을 시 /etc/nginx/nginx.conf 파일의 http{} 블럭 안에 아래 코드가 입력되어 있는지 확인.<br>   - include /etc/nginx/conf.d/*.conf<br>cf>설정 변경 후 nginx 재기동 필수!<br>   - systemctl restart nginx 또는 systemctl reload nginx (restart: nginx 재시작, reload: 설정파일만 Re-import)</blockquote>

끝.

<br>

### 2. System 기본 설정 - BackEnd
cf> root 계정으로 로그인하지 않은 경우, yum 명령이 잘 실행되지 않을 수 있음.
<br>이 경우 명령어 앞에 sudo 를 붙여주거나, su 명령어를 통해 root 계정으로 진입 후 작업.

I. 시스템 패키지 업데이트
```
yum update -y
```
II. Java 17 RPM 패키지 다운로드
   - OpenJDK Java 17 은 CentOS 8.x 이상 버전의 AppStream Repository 에서 사용하능함.
   - CentOS 7.x 버전에서는 Repository 를 통해 설치할 수 없어, 별도의 RPM 패키지를 내려받아 설치해야 함.
```
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.rpm
```
III. Java 17 RPM 패키지 설치
```
rpm -ivh jdk-17_linux-x64_bin.rpm
```
IV. Java 17 Version 확인
```
java -version
```

끝.

<br>

### 3. React Project 실행하기
<blockquote>좀 지저분하고, 번거롭지만, 일단 프로젝트에 사용한 방법을 기재하였습니다. <br>그러나 웬지 더 좋은 방법이 있을 것만 같습니다.</blockquote>

I. npm 환경 구축
   - React 실행시킬 디렉토리로 이동 후, 아래 명령 실행.
```
create-react-app ./
```

II. Project Clone
   - create-react-app 명령을 실행한 곳과 "다른" 디렉토리로 이동 후, 아래 명령으로 github Project Clone. 
```
git clone [Github Repository Address]
```
<blockquote>cf> git: Command not found 에러 발생 시, 아래 몀령으로 git 설치<br>sudo yum install -y git</blockquote>
  
   - [현재 디렉토리]/[Repository 이름] 에 프로젝트 파일이 저장됨

III. 중복 파일 제거
   - create-react-app 을 실행한 경로로 이동하여 ls 를 치면, 디렉토리에 들어있는 파일/폴더가 나옵니다.
   - node_modules 를 제외하고, 전부 삭제합니다.
```
rm -rf public README.md src package*
```

   - rf -rf 는 모든 파일/폴더를 경고 없이 완전삭제하므로, 삭제할 파일 경로를 잘 확인해야 합니다.

IV. 프로젝트 Sources 옮기기
```
mv [git clone 한 Directory]/* [create-react-app 실행한 Directory]/
```

V. 프로젝트 빌드
   - Build 중 발생하는 Module not found 에러는, 프로젝트에 사용된 모듈이 설치되지 않아서 발생. (V-I 참고)
```
cd [create-react-app 실행한 Directory] && npm run build
```

V-I. 모듈 설치 (→ 뭔가 자동화 방법이 있을 것 같은데.. 모르겠습니다 ㅜㅜ)
   - Module not found: Error: Can't resole '[Module Name]' in '[pwd]' -> xxx 모듈이 설치되지 않아 발생하는 ERROR.
   - 아래 명령어로 Module 설치
   - 설치 후 found 1 high severity vulnerability 문구 → 모듈 보안 관련 문제이나 일단 신경쓰지 않아도 됨.
```
npm install [Module Name]
```

VI. 프로젝트 실행
   - The build folder is ready to be deployed. 문구 출력 시, 아래 명령으로 React 실행
   - Log가 쭉 내려가다가, 멈춘 것 같으면 서버로 접속해보자.
   - 접속 잘 되면 성공. (403 이나 404, 502 에러가 발생하면 React 실행이 올바르게 되지 않은 것)
```
cd [create-react-app 실행한 Directory] && npm run start
```

VII. 백그라운드 실행
   - npm run start 명령을 사용하는 경우, 커맨드 창을 끄면 React 서버도 함께 내려감
   - forever 명령을 사용하여 백그라운드에서 실행 가능하도록 함
   - 커맨드 창을 꺼도, 서버는 잘 굴러간다.
```
cd [create-react-app 실행한 Directory] && forever start -c "npm run start" ./
```
   
VII. React 로그 확인
   - React 서버가 돌아가는 번호 확인하여야 함.
```
forever list
```
실행 시 아래와 같은 결과가 출력된다.
```
info:    Forever processes running
data:        uid  command       script forever pid  id logfile                 uptime
data:    [0] mv0O npm run start        9325    9333    /root/.forever/mv0O.log 0:0:0:11.032
```
여기서 [0] ← 이것이 React 구동되는 번호이다. 로그 확인은 아래와 같다.
```
forever logs [프로그램 번호]
```

끝.

<br>

### 4. SpringBoot Project 실행하기

I. Project Clone
   - 프로젝트 저장시킬 디렉토리로 이동 후, 아래 명령 실행
```
git clone [Github Repository Address]
```

II. 권한 설정
   - 원활한 프로젝트 빌드를 위해, 파일 소유권을 rwxrwxrwx (chmod 777) 로 설정
```
chmod -R 777 [SpringBoot Project 저장경로]
```

III. 프로젝트 빌드
```
cd [SpringBoot 프로젝트 저장경로] && ./gradlew build
```

IV. 프로젝트 실행
   - plain 이 붙은 jar 는 의존성 제거 후 빌드한 것이라고 한다.
   - plain 안 붙은 jar로 실행.
```
java -jar [SpringBoot 프로젝트 저장경로]/build/libs/xxx.jar
```

V. 백그라운드 실행
   - 커맨드 창을 끄면 SpringBoot 서버도 함께 내려간다.
   - 아래 내용을 스크립트로 만들어 sh 파일로 저장하면 서버 실행이 용이하다.
```
nohup java -jar [SpringBoot Project 저장경로]/build/libs/xxx.jar &
```

VI. 로그 확인
   - nohup 명령어를 실행한 디렉토리에, nohup.out 으로 로그가 저장된다.
   - Putty 커맨드에서 cat nohup.out 혹은 vi nohup.out 으로 확인할 수 있다.
   - 혹은, Filezilla 등 FTP (SFTP) 프로그램을 활용하여 작업용 노트북으로 로그파일을 옮긴 후, 메모장 등으로 열어도 된다.

끝.

<br>

### 5. Nginx Proxy 설정

<작업중>


## 팀원 및 참고 자료
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/himodu"><img src="https://avatars.githubusercontent.com/u/71763322?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀장 : 이동건 (himodu)</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/SubinPyeon"><img src="https://avatars.githubusercontent.com/u/105070397?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 편수빈 (SubinPyeon) </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/wjdqh6544"><img src="https://avatars.githubusercontent.com/u/77498822?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 서형철 (wjdqh6544) </b></sub></a><br /></td>
    </tr>
  </tbody>
</table>
