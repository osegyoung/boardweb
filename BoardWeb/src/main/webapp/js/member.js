/**
 * member.js
 */
// 삭제 함수.
//DB 삭제, 화면에서 지우기.
function deleteRow(id) {
	console.log(this);
	let btn = this;
	fetch("removeMember.do?mid=" + id)//서버처리.
	.then(function(result){
		return result.json();
	})
	.then( (result) => {
		console.log(result);
		if(result.retCode == "OK"){
			document.querySelector('#tr_' + id).remove(); // 한건 지우기		
		} else if(result.retCode == "NG"){
			alert('삭제오류 발생'); // 에러.
		} else {
			alert('알수 없는 코드입니다.')
		}
		
	})
} // end of deleteRow.

//목록출력.
fetch("testData.do")
	.then(function(result) {
		return result.json(); // stream ->object
	})
	.then(function(result) {
		const memberAry = result;
		memberAry.forEach(function(member) {
			const target = document.querySelector('#list');
			const html = `<tr id=tr_${member.memberId}>
			<td>${member.memberId}</td>
			<td>${member.passwd}</td>
			<td>${member.memberName}</td>
			<td>${member.resposibility}</td>
			<td><button onclick ="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button></td>
			</tr>`
			target.insertAdjacentHTML('beforeend', html);
		});
	})
	
	//추가 이벤트
	document.querySelector('#addMember').addEventListener('click', function(e){
		alert('클릭됨.');
	})