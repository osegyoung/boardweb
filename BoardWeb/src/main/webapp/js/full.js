/**
 * full.js
 */


document.addEventListener('DOMContentLoaded', function() {

	let eventAll = [];

	//Ajax 호출
	fetch('FullData.do')
		.then(result => result.json())
		.then(result => {
			console.log(result);
			eventAll = result
			fullCalendarFunc()
		})
		.catch(err => console.log(err));

	//fullCalendar 호출.
	function fullCalendarFunc() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			initialDate: new Date(),
			navLinks: true, // can click day/week names to navigate views
			selectable: true,
			selectMirror: true,
			select: function(arg) {
				var title = prompt('Event Title:');
				console.log(title, arg.startStr, arg.endStr);
				//화면 출력
				if (title) {
					calendar.addEvent({
						title: title,
						start: arg.start,
						end: arg.end,
						allDay: arg.allDay
					})
				}
				calendar.unselect()// 화면출력 하는 부분
			},
			eventClick: function(arg) {
				console.log(arg);
				//Ajax 호출 -> 컨트롤 -> 삭제 -> 화면삭제.
				if (confirm('삭제 하시겠습니까?')) {
					fetch('removeDate.do?title=' + arg.event.title + '&start=' + arg.event.title.startStr + '&end=' + arg.event.title.endStr + '') // 값을 넘긴다.
						.then(result => result.json())
						.then(result => {
							if (result.retCode == 'OK') {
								arg.event.remove(); //  화면 event 삭제							
							} else {
								alert('삭제중 예외.')
							}

						})
						.catch(err => console.log(err));
				}
			},
			editable: true,
			dayMaxEvents: true, // allow "more" link when too many events
			events: eventAll

		});
		calendar.render();

	}
});


