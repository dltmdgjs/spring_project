// DOM 객체 연결 (DOCUMENT OBJECT MODEL : 문서를 객체화 한 모델)
// jsp 파일에 있는 태그, 객체를 자바스크립트와 연결시키는 과정.
const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

// csrf 토큰과 헤더명 가져오기
// 자바스크립트에서 벡엔드 단으로 json타입의 데이터를 넘기기 위해 csrf 토큰이 필요함.
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');