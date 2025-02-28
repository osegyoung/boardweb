/**
 * full.js
 */


document.addEventListener('DOMContentLoaded', function() {

	let eventAll = [];
	fetch('FullData.do')
		.then(result => result.json())
		.then(result => {
			console.log(result);
			eventAll=result
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
				console.log(title, arg.startStr,arg.endStr);
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
				if (confirm('Are you sure you want to delete this event?')) {
					arg.event.remove()
				}
			},
			editable: true,
			dayMaxEvents: true, // allow "more" link when too many events
			events: eventAll

		});
		calendar.render();

	}
});


