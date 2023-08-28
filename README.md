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
https://www.notion.so/API-1e699ca81d11435a86296438df798b39?pvs=4

### 사용된 기술
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">  <img src="https://img.shields.io/badge/Naver-03C75A?style=for-the-badge&logo=Naver&logoColor=white"> 

네이버 클라우드 플랫폼 (NCP) 를 사용하였고
본래 도커와 Github action 을 통한 배포를 시도했으나 실패하여<br/>
백엔드서버에는 직접 인스턴스 서버에 java를 설치하였고<br/>
DB서버는 네이버 클라우드에서 제공하는 SaaS를 이용하였다.<br/>
 
### 아키텍쳐 노션페이지
https://www.notion.so/5d04f492bc3442ff8ddcad1437f7d734?pvs=4

## 프로젝트 설치 및 실행 방법
Java 버전 : 11



## 팀원 및 참고 자료
<table>
  <tbody>
    <tr>
      <td align="center"><a href=""><img src="" width="100px;" alt=""/><br /><sub><b>BE 팀장 : 이동건 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/SubinPyeon"><img src="https://avatars.githubusercontent.com/u/105070397?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 편수빈 (SubinPyeon) </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/wjdqh6544"><img src="https://avatars.githubusercontent.com/u/77498822?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 서형철 (wjdqh6544) </b></sub></a><br /></td>
    </tr>
  </tbody>
</table>
