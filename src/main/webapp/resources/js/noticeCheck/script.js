document.getElementById("buttonUpdate").addEventListener('click', function(){
    const idx = document.getElementById("idx").value;

    window.location.href=`/noticeModifyPage?idx=${idx}`; // 수정 페이지로 이동
})

document.getElementById("buttonDelete").addEventListener("click",function(){
    const idx = document.getElementById("idx").value;

    const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    fetch(`/menu/delete/${idx}`, {
        method:'DELETE',
        headers:{
            [csrfHeader]:csrfToken
        }
    }).then(response => {
        if(!response.ok) {
            throw new Error("응답 에러");
        } else {
            return response.text();
        }
    }).then(_ => {
        alert("삭제 완료");
        window.location.href='/'; //메인페이지 이동
    }).catch(error => {
        console.log(`에러: ${error}`);
    })
})