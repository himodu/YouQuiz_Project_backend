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
<blockquote>cf> git: Command not found 에러 발생 시, 아래 명명령으로 git 설치<br>sudo yum install -y git</blockquote>
  
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
   - plain "안" 붙은 jar로 실행.
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
   - 혹은, Filezilla 등 (S)FTP 프로그램을 활용하여 작업용 노트북으로 로그파일을 옮긴 후, 메모장 등으로 열어도 된다.

끝.

<br>

## Nginx Proxy 설정

### Proxy
Proxy Server 는 Client 와 Server 사이에서 연결을 중재하고 데이터를 전달해 주는 서버이다. 본래 Proxy Server 는 캐싱을 목적으로 구축되었으나, 요즈음 캐싱 기능은 CDN으로 대체하는 추세이고, Proxy Server는 주로 보안 유지의 목적 (프록시 서버를 통해서만 서버에 접속할 수 있게 하는 등 - VPN개념) 으로 활용되곤 한다. 어찌 됐든, Client <--> Proxy <--> Server 의 형태로 데이터가 흐르며, Client 가 서버에 접속하기 위해 Proxy Server 를 경유한다.<br><br>그러나, 본 프로젝트에 사용된 것은 Reverse Proxy 로, 사용자가 Proxy 서버로 접속하도록 설정한 것이 아니라, 서버 측에서 Proxy를 설정해 둔 경우이다. 즉, Client는 그저 서버에 접속하기만 하면, Server 측에서 구축한 Proxy 가 Client's Connection 을 적절히 제어한다. 그러므로 Reverse Proxy 를 잘 활용한다면, 특정 IP 대역에서 날아오는 Request 는 모두 Refuse (ex. 특정 국가에서의 접속 차단) 한다거나, 때로는 Load Balancing(부하분산) 기능을 별도의 하드웨어 (L4 / L7 Switch) 없이 소프트웨어로 처리하는 등, 서버 운영의 안정성과 보안을 향상시킬 수 있다. Reverse Proxy 의 사용 목적과 기능을 고려한다면, NAT환경 (내부 네트워크 IP 대역이 달라, 내부 네트워크 안에서 각각의 여러 망으로 분리된 환경), 혹은 보안을 위해 고의로 서버를 여러 개로 분리시킨 경우, 혹은 단일 서버이더라도 보안상의 이유로 Traffic 을 걸러내야 하는 경우 등에 적절히 사용될 수 있는 기술이라 쉽게 추측할 수 있을 것이다.<br><br>사실, 본 프로젝트에서 굳이 Reverse Proxy 기능을 사용할 필요는 없었다. React 프로젝트의 package.json 에서 Service Port 를 80 으로 설정해주면, 별다른 작업 없이도 도메인을 사용하여 React Project 로의 접속이 가능하였기 때문이다. 하지만 Reverse Proxy (Load Balancing) 를 사용하여, Port 80 (http Protocol 기본 포트) 에 대한 Connection 을 Port 3000 (React 기본 포트) 으로 Redirection 처리하는 것이 더 간편할 뿐더러, 무엇보다 보안 측면에서 필수적인 SSL Encryption 을 적용하려면 Reverse Proxy 를 필수적으로 사용하여야 하기에 ― SSL 적용 후에도 http 에서의 접속을 허가한다면, SSL 적용 의미가 없으므로, http 에서의 Connection 을 https 로 Redirection 해주어야 한다. ― Nginx 웹서버를 활용한 Reverse Proxy 를 적용하였다.

### DATA Flow
현재 YouQuiz 서비스에서 데이터 처리 과정은 다음과 같은 흐름으로 이루어진다.

<blockquote>Client <---> Nginx <---> React <---> SpringBoot <---> MySQL</blockquote>

Client가 Domain (youquiz.site) 으로 접속하면, 우선 Nginx 웹서버를 경유한다. Nginx 에서는 80 포트로 들어온 Connection 을 3000 포트로 Redirection 시키면, React 와 Client 는 통신을 시작하는데, 이 과정에서 필요한 Data 를 React 가 SpringBoot 로 요청한다. SpringBoot 는 요청을 받고 MySQL 에서 필요한 데이터를 꺼내 적절히 가공하여 React 로 반환한다. 데이터를 저장할 때에도, Client 가 React 로 raw data 를 넘겨주면, React 는 데이터를 적절한 형식에 맞추어 SpringBoot 로 보내고, SpringBoot 는 받은 data 를 추출하여 MySQL 이 관리하는 Database 의 적절한 table 에 저장한다. 이와 같은 흐름이 Client 의 Request 마다 반복되어 서비스가 운용되는 것이다.

### Nginx Settings
본 프로젝트에서는 복잡한 Connection 설정이 필요하지 않으므로, 단일 conf 파일로 설정을 진행하였다.
```
vi /etc/nginx/conf.d/root.conf
```
파일명은 root.conf 인데, 어떤 이름이든 관계 없다. 다만 여러 (서브)도메인을 운용하는 경우, 관례적으로, 설정 현황 파악 및 관리의 편의성을 위해 [(서브)도메인.conf] 형태로 설정 파일을 저장한다. 본 프로젝트에서는 단일 도메인만을 사용하므로, 임의로 이름을 결정하여 root.conf 파일에 설정값들을 저장하였다.
```
// /etc/nginx/conf.d/root.conf
1  server {
2          listen 80;     
3          server_name youquiz.site www.youquiz.site;
4  #        location ^~ /.well-known/acme-challenge/ {
5  #               default_type "text/plain";
6  #               root /var/www/letsencrypt;
7  #       }
8          location / {
9                 proxy_pass http://localhost:3000;
10         }
11         access_log /youquiz_log/access.log;
12         error_log /youquiz_log/error.log;
13 }
```
해당 설정 파일을 해석해 보면,

   - Line 1 - server {} 블럭을 통해 nginx 에 하나의 도메인에 대한 설정 내용임을 알린다.
   - Line 2 - 아래 설정은 Port 80 으로 Inbound 하는 Connection 중,
   - Line 3 - youquiz.site 혹은 www.youquiz.site 도메인으로 Inbound 하는 Traffic 에 대한 것인데,
   - (Line 4 ~ 7 - SSL Encryption (https) 적용 관련 설정)
   - Line 8 - Domain 뒤에 / 가 붙은 경우, (즉 http://(www.)youquiz.site/ 인 경우)
   - Line 9 - 해당 Connection 을 http://localhost:3000 으로 Redirection 시키며,
   - Line 11 - 해당 Traffic 의 Inbound 사실을 /youquiz_log/access.log 에 기록하고,
   - Line 12 - 해당 Traffic 에 대한 Connection 이 종료될 때 까지 발생한 ERROR 를 /youquiz_log/error.log 에 기록하라.

는 뜻이다. 참고로, Line 8 에서, 만약 "location /admin" { 으로 되어 있다면, youquiz.site/admin 으로 Inbound하는 Connection 에 대한 설정이 된다. 또한, Redirection 하면서 header 의 내용까지 함게 전달해야 한다면, (ex. Client IP) location 블럭 아래쪽으로 관련 옵션들을 기재해주면 된다. (ex. proxy_pass_request_headers)

끝.

<br>
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
