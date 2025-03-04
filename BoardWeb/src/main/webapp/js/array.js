/**
 * array.js
 * forEach, filter, map, reduce 메소드.
 */
let ary = [
	{ id: 100, name: "루피", score: 333 },
	{ id: 101, name: "조로", score: 111 },
	{ id: 102, name: "상디", score: 222 }
]

//reduce.
let result = ary.reduce((acc, item, idx, array) => {// acc(accumulator): 누적값
	console.log(acc);
	return acc + item.score;
}, 0);
console.log('최종결과: ', result);

result = ary.reduce((acc, item) => {
	return acc < item.score ? acc : item.score;
}, 1000);
console.log('최종결과Min: ', result);

//filter: 200점 이상.
result = ary.reduce((acc, item) => {
	if (item.score > 200) {
		acc.push(item); // [{}]<- 이런 모양
	}
	return acc;
}, []);
console.log('최종결과: ', result);

result = ary.reduce((acc, item) => {
	let li = document.createElement('li'); 
	li.innerHTML = 'id: ' + item.id + ', name:' + item.name;
	acc.appendChild(li);
	return acc; // <ul></ul>
}, document.getElementById('list')); // id값이 list라는 ul태그




/*
ary.forEach((item, idx, array) => { // funcion 대신 =>( 화살표 함수 )사용 ,  선언하고 싶은 부분만 적으면 됨.
	console.log(item, idx, array);

})

let filAry = ary.filter(item => {
	if (item.score > 300) {
		return true;		
	}
	return false;
})
console.log(filAry);

// map(ping)
let mapAry = ary. map(item => {
	// A:200, B:100, C:그외.
	 if(item.score > 250){
		item.group = 'A';
	 }	else if (item.score > 150) {
		item.group = 'B';
	 }else {
	 item.group ='C';		
	 }	 
	 return item;
});
console.log(mapAry);
*/