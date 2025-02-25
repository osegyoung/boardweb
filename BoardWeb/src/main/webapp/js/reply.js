/**
 * reply.js
 * 
 */
console.log(svc.showName());

//댓글.
function makeReply(reply = {}) {
	let html = `<li data-id="${reply.replyNo}">
				<span class="col=sm-2">${reply.replyNo}</span> 
				<span class="col=sm-5">${reply.reply}</span>
				<spanclass ="col=sm-2">${reply.replyer}</span> 
				<span class="col=sm-2"><button onclick="deleteRow('${reply.replyNo}')">삭제</button></span>
				</li>`;
	return html;
}
//글삭제
function deleteRow(rno){
	if(!confirm("삭제하겠습니까?")){
		alert('취소합니다.');
		return;
	}
	
	svc.removeReply(rno
		, function(result){
			if(result.retCode == 'OK') {
				document.querySelector('li[data-id="' +rno + '"]').remove();
			}
		}
		, function(err) { console.log(err); })
}
//목록.
svc.replyList(bno//원본글 번호
	, // 성공함수
	function(result) {
		let resultAry = result;
		resultAry.forEach(function(reply) {
			let target = document.querySelector('.reply> .content> ul');
			target.insertAdjacentHTML('beforeend', makeReply(reply));
		});
	}
	,//실패함수.
	function(err) {
		console.log(err);
	}
);

//댓글등록 이벤트 id="addReply"
document.querySelector('#addReply').addEventListener('click', function() {
	// 글번호:bno, 작성자:logid, 댓글: ? 
	const reply = document.querySelector('#reply').value;
	const replyer = logid;
	if(!reply || !replyer){
		alert('필수입력값을 확인.');
		return;
	}
	
	const parm = { bno, reply, replyer }

	svc.addReply(parm,
		function(result) {
			if (result.retCode == 'OK') {
				const html = makeReply(result.retVal);
				console.log(html);
				let target = document.querySelector('.reply> .content> ul');
				target.insertAdjacentHTML('beforeend', html);
			} else {
			alert('처리 예외 발생.');
			}
		}
		, function(err) {console.log(err) });
});