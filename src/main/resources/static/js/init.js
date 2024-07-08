/**
 * [ebook 함수] 상세 정보 조회 요청
 * ajax 요청
 * @author ryugyungyoon
 * @param url 요청 url
 * @param key 요청 파라터
 * @returns {void}
 */
function cinit_view(url, key){
	let viewReqData = key +'='+$('#searchForm #'+key).val();
	cfn_ajaxRequest(url, 'GET', viewReqData);
}