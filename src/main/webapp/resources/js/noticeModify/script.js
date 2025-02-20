document.getElementById('buttonUpdate').addEventListener('click',function(){
    const idx = document.getElementById("idx").value;

    const formData = {
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
        writer: document.getElementById("writer").value,
    }

    // index.jsp에서 만든 meta CSRF 태그 두개를 js로 가져옴 (보안)
    const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    // 프론트에서 벡엔드로 데이터를 줌.
        fetch(`/menu/update/${idx}`,{
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]:csrfToken //CSRF 헤더와 토큰을 동적으로 추가
            },
            body: JSON.stringify(formData) // 객체를 JSON 타입으로 변경.
        }).then(response => {
            if(!response.ok) {
            throw new Error("공지사항 수정 실패.")
            }
            return response.text(); // "게시글이 수정되었음."
        }).then(_ => {
            alert("수정 완료");
            window.location.href="/"; // 메인으로 이동.
        }).catch(error => {
            console.log("Error 발생", error);
        })
})