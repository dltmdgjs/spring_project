// DOM 객체 연결 (DOCUMENT OBJECT MODEL : 문서를 객체화 한 모델)
// jsp 파일에 있는 태그, 객체를 자바스크립트와 연결시키는 과정.
const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

// csrf 토큰과 헤더명 가져오기
// 자바스크립트에서 벡엔드 단으로 json타입의 데이터를 넘기기 위해 csrf 토큰이 필요함.
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

// 데이터를 조회할 때 사용할 기능을 정의
// restapi 방식.
function fetchMenus() {
    fetch("/menu/all").then(response => response.json())
    .then(menus => {
        menuList.innerHTML = ''; // 기존 메뉴 목록을 초기화
        menus.forEach(menu => {
            // 각 메뉴 아이템을 생성해 리스트에 추가.
            // html 태그를 생성함.
            const menuItem = document.createElement('div');
            menuItem.className = 'menu-item';
            // 태그 안에 값넣기
            menuItem.innerHTML = `
                <a href="#" class="menu-link" style="text-decoration:none; color:black;">
                    <h3>${menu.title}</h3>
                    <p>${menu.content}</p>
                    <small>작성자:${menu.writer}, 작성일:${menu.indate}, 조회수:${menu.count}</small>
                </a>
                <br/>
                <br/>
            `
            // 게시글 클릭 시.
            menuItem.querySelector(".menu-link").addEventListener('click',(event) => {
                event.preventDefault();
                incrementCount(menu.idx).then(()=>window.location.href=`/noticeCheckPage?idx=${menu.idx}`)
            });
            menuList.appendChild(menuItem);
        })
    })
}

//조회수 기능
function incrementCount(idx) {
    return fetch(`/menu/count/${idx}`,{
        method: 'PUT',
        headers:{
            [csrfHeader]:csrfToken
        }
    }).then(response=>{
        if(!response.ok) {
            console.log('데이터가 프론트에서 백으로 안넘어감.오류');
        }
    }).catch(error => {
        console.log(`Error: ${error}`);
    })
}

// 메인 페이지가 열리면 자동 실행.
window.addEventListener('load',fetchMenus);