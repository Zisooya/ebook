/**
 * [기능 함수] 일반 기능 ajax 요청 함수
 * @author ryugyungyoon
 * @param url : 요청 url
 * @param type : method (post, get)
 * @param data : 전송 데이터
 * @returns {void}
 */
function cfn_ajaxRequest(url, method, data, callbackId){
	let callback = url.split('/').reverse()[0];
	if(callbackId != undefined && callbackId != null){
		callback = callbackId;
	}

	$.ajax({
		url: url,
		type: method,
		data: data,
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		//contentType: ' application/json; charset-utf-8',
		async: true,
		success: function(resData){
			fn_callback(callback, resData);
		},
		error : function(request, status, error){
			console.log(request.status);
			console.log(error);
		},

	});
}

/**
 * [일반 검증] 문자열의 null 확인
 *
 * @author ryugyunguoon
 * @param str 문자열
 * @returns {Boolean}
 */
function isEmpty(str){
	if(str === null || str === undefined || str === '' || str === 'undefined' || str === 'null'){
		return true;
	}
	else{
		return false;
	}
}

/**
 * [ebook 함수] 목차 아이콘 클릭 or 터치시 이벤트
 * @author zisooya
 * @returns {void}
 */
function openToc(){
	$('.toc-container').addClass('active');
}

/**
 * [ebook 함수] 목차 리스트의 닫기 아이콘 클릭 or 터치시 이벤트
 * @author zisooya
 * @returns {void}
 */
function closeToc(){
	$('.toc-container').removeClass('active');
}