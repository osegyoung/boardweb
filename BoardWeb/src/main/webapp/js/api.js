/**
 * api.js
 */

let centerAll = [];

//이벤트(select) 등록.
document.getElementById('centerList').addEventListener('change', function(e) {

	let sidoName = e.target.value; // "서울특별시" "인천광역시"
	console.log(centerAll, sidoName);

	let filterSido = [];
	filterSido = centerAll.filter(item => {
		if (item.sido == sidoName) {
			return true;
		}
		return false;
	});
	console.log(filterSido);
	makeCenterList(filterSido); // 함수 실행 해줘야 화면에 리스트 출력됨.
});// change 이벤트.

function makeCenterList(centerAry = []) {
	let fields = ['id', 'centerName', 'phoneNumber', 'sido'];
	//기존목록 삭제.
	document.getElementById('list').innerHTML = '';

	// 센터정보
	centerAry.forEach(center => {
		//tr>td*4
		let tr = document.createElement('tr');
		tr.addEventListener('click', function() {
			console.log(center.lat, center.lng);
			window.open('map.do?lat=' + center.lat + '&lng=' + center.lng + '&centerName=' + center.centerName); // 지도 열기
		});
		for (let i = 0; i < fields.length; i++) {
			let td = document.createElement('td');
			td.innerHTML = center[fields[i]];
			tr.appendChild(td);
		}
		document.getElementById('list').appendChild(tr);
	});
}


//Ajax
fetch('https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&returnType=json&serviceKey=zLzDOKLTH0MnOy%2FJvMhvAEg1nkrknsHN1qxM%2BolnBQqGf0Ode1qcka7A9PfgCO9UK8u4F%2By1PD1yntKEheP83Q%3D%3D')
	.then(result => result.json())
	.then(result => {
		console.log(result.data);
		centerAll = result.data; // sub로 온 데이터를 centerAll에 담는다.
		makeSidoList();
	})
	.catch(err => console.log(err))

//시도정보 중복제거 후 화면에 출력. 
function makeSidoList() {
	let sidoList = []; // ['서울특별시', '인천광역시', '대구광역시'.... ]
	for (let i = 0; i < centerAll.length; i++) {
		if (sidoList.indexOf(centerAll[i].sido) == -1) {
			sidoList.push(centerAll[i].sido);

		}
	}
	console.log(sidoList.sort());
	sidoList.forEach(sido => {
		let opt = document.createElement('option');
		opt.innerHTML = sido; // <opion>서울특별시></option>
		document.getElementById('centerList').appendChild(opt);

	})
};
