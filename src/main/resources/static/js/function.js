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
	if(callbackId != undefined && callbackId != null) {
		callback = callbackId;
	}

	$.ajax({
		url: url,
		type: method,
		data: data,
		dataType: 'text',
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