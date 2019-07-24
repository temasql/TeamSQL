var eventModal = $('#eventModal');

var modalTitle = $('.modal-title');
var editAllDay = $('#edit-allDay');
var editTitle = $('#edit-title');
var editStart = $('#edit-start');
var editEnd = $('#edit-end');
var editType = $('#edit-type');
var editColor = $('#edit-color');
var editDesc = $('#edit-desc');
var userName = $("#userName").val();

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');


var nameArray = userName.split(".jsp");
var name = nameArray[0];

/*
 	밑에서 insert 성공 후 성공한 id 값을 담을 변수
 */
var idData = 0;

/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

    $("#contextMenu").hide(); //메뉴 숨김

    /*
     	기본값 
     	시작일 : 해당 날짜 + 현재시간, 
     	마무리 일 : 해당날짜 + (현재시간 + 1시간) 
     */
    modalTitle.html('새로운 일정');	// 모달 창 이름
    editStart.val(start);		// 일정 시작 일	
    editEnd.val(end);			// 일정 마무리 일
    editType.val(eventType).prop("selected", true);	

    addBtnContainer.show();
    modifyBtnContainer.hide();
    eventModal.modal('show');

    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/
//    var eventId = 1 + Math.floor(Math.random() * 1000);
    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/

    // 새로운 일정 저장버튼 클릭 시 이벤트
    // pom.xml에 jackson dependency 추가 필수!
    $('#save-event').unbind();
    $('#save-event').on('click', function () {
    	
    	
//        var eventData = {
//        	  calendar_id: eventId,				
//            calendar_title: editTitle.val(),		
//            calendar_start_dt: editStart.val(),		
//            calendar_end_dt: editEnd.val(),
//            calendar_content: editDesc.val(),
//            calendar_type: editType.val(),
//            user_id_fk: name,
//            calendar_background: editColor.val(),
//            textColor: '#ffffff',
//            allDay: false
//        };
    	
//                _id: eventId,
    	var eventData = {
                title: editTitle.val(),
                start: editStart.val(),
                end: editEnd.val(),
                description: editDesc.val(),
                type: editType.val(),
                username: name,
                backgroundColor: editColor.val(),
                textColor: '#ffffff',
                allDay: false
        };
        
        
        
        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        var realEndDay;

        if (editAllDay.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');

            eventData.allDay = true;
        }

        $("#calendar").fullCalendar('renderEvent', eventData, true);
        eventModal.find('input, textarea').val('');
        editAllDay.prop('checked', false);
        eventModal.modal('hide');
        
        /*
	 	시퀀스 번호,
	 	제목,
	 	일정 시작일,
	 	일정 마무리일,
	 	내용(설명),
	 	그룹(생일, 업무, 행사)
        */
        //새로운 일정 저장
        $.ajax({
        	url: "/insertCal",					// 새로운 일정 저장 버튼 클릭 시 보내지는 URL
        	method : "post",
        	data : JSON.stringify(eventData),	// Controller에서는 Map형태로 받는다, serialize도 가능
        	contentType: "application/json",
        	dataType : "json",
        	success: function (response) {
        		idData = response;
        		alert(response);
        	}
        });
    });
};