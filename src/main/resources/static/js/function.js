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
 * [기능 함수] 책 출력하는 함수 - double 모드
 * @author zisooya
 * @returns {void}
 */
function printDoubleBook() {
	let ebookImageInfo = g_ebookImageInfo;
	let numberOfPages = ebookImageInfo.imageCount;
	//모바일 영역 비활성화
	$('#mobile-wrap').css('display', 'none');

	//책 생성 및 출력
	$('#book').turn({
		acceleration: true, //하드웨어 가속 모드를 설정
		pages: numberOfPages+3,   //전체 페이지 수
		elevation: 50,  //페이지 쌓임 효과
		//gradients: !$.isTouch,  //그라데이션 모드 설정
		gradients: true,  //그라데이션 모드 설정
		when: { //turn 이벤트 옵션 정의
			//페이지가 출력되기 전 실행
			turning: function (e, page, view) { //e: 이벤트 객체, page: 새로운 페이지 번호, view: 새로운 뷰

				//메모리 보관에 필요한 책의 페이지 범위를 가져옴
				let range = $(this).turn('range', page);

				//각 페이지가 책의 범위에 속하는 지 확인후 반복 추가
				for (page = range[0]; page <= range[1]; page++)
					addPage(page, $(this));
			},
			//페이지가 출력된 후 실행
			turned: function (e, page) {
				//페이지 리로드시 사용할 페이지 변수
				g_pageNumber = page;

				//이미지맵(링크) 있는 페이지
				if(page === 28 || page === 29){
					//반응형 이미지맵 좌표 조정
					$('map').imageMapResize();

					//이미지맵 링크 터치,클릭 이벤트
					$('area').on('touchstart click', function(e) {
						e.preventDefault(); //현재 이벤트의 기본 동작 중단
						e.stopPropagation(); //현재 이벤트의 상위 전파 중단
						e.stopImmediatePropagation(); //현재 이벤트의 현재 레벨 전파 중단

						let href = $(this).attr('href');
						window.open(href, '_blank');
					});
				}

				if((page%2===0)){
					$('#toc-page-number').text(page);
				}
				else{
					$('#toc-page-number').text(page-1);
				}
			}
		}
	});

	$('.number-pages').html((numberOfPages+3)); //전체 페이지수 출력
	$('#toc-number-pages').html((numberOfPages+3)); //전체 페이지수 출력

	$('#left-page-number').keydown(function (e) {
		if (e.keyCode === 13) {
			// $('#book').turn('page', $('#left-page-number').val()-1);
		}
	});

	$('#right-page-number').keydown(function (e) {
		if (e.keyCode === 13) {
			//  $('#book').turn('page', $('#right-page-number').val());
		}
	});
}

/**
 * [기능 함수] 책 출력하는 함수 - single 모드
 * @author zisooya
 * @returns {void}
 */
function printSingleBook() {
	let ebookImageInfo = g_ebookImageInfo;
	let numberOfPages = ebookImageInfo.imageCount;
	//모바일 영역 활성화
	$('#mobile-wrap').css('display', 'block');
	$('.wrap').css('display', 'none');

	//책 생성 및 출력
	$('#book-m').turn({
		display: 'single',
		duration: 400,  //페이지 전환 효과 0.4초
		elevation: 50,  //페이지 쌓임 효과
		acceleration: true, //하드웨어 가속 모드를 설정
		pages: numberOfPages,   //전체 페이지 수
		gradients: true,  //그라데이션 모드 설정
		when: { //turn 이벤트 옵션 정의
			//페이지가 출력되기 전 실행
			turning: function (e, page, view) { //e: 이벤트 객체, page: 새로운 페이지 번호, view: 새로운 뷰
				//메모리 보관에 필요한 책의 페이지 범위를 가져옴
				let range = $(this).turn('range', page);

				//각 페이지가 책의 범위에 속하는 지 확인후 반복 추가
				for (page = range[0]; page <= range[1]; page++) {
					addPage(page, $(this));
				}
			},
			//페이지가 출력된 후 실행
			turned: function (e, page) {
				$('.page-number').val(page);

				//이미지맵(링크) 있는 페이지
				if(page === 25 || page === 26){
					//반응형 이미지맵 좌표 조정
					$('map').imageMapResize();

					//이미지맵 링크 터치 이벤트
					$('area').on('touchstart', function(e) {
						e.preventDefault(); //현재 이벤트의 기본 동작 중단
						e.stopPropagation(); //현재 이벤트의 상위 전파 중단
						e.stopImmediatePropagation(); //현재 이벤트의 현재 레벨 전파 중단

						let href = $(this).attr('href');
						window.open(href, '_blank');
					});
				}

				//책 터치시 페이지 넘김 이벤트
				$('#data_' + page).on('touchstart', function (e) {
					console.log('Event triggered:', e.type);

					e.preventDefault(); //현재 이벤트의 기본 동작 중단
					e.stopPropagation(); //현재 이벤트의 상위 전파 중단
					e.stopImmediatePropagation(); //현재 이벤트의 현재 레벨 전파 중단

					let orientation = screen.orientation.type.includes('portrait') ? 'portrait' : 'landscape';
					//getBoundingClientRect(): 요소에 대한 뷰포트 기준의 상대적인 위치 정보를 구하는 메소드.(터치 이벤트는 offset 좌표가 없으므로 계산해서 구해야 함)
					let offset = e.target.getBoundingClientRect();

					//세로 길게 방향이면 X축 기준 이벤트
					if(orientation === 'portrait') {
						//터치 이벤트는 여러 손가락 터치를 인식하므로 첫번째 터치를 의미하는 touches[0] 사용

						let offsetX = e.touches[0].clientX - offset.x;

						let half = $(this).width()/2;

						//책의 왼쪽 클릭시 이전 페이지 이동
						if(offsetX < half) {
							$('#book-m').turn('previous');
						}
						//책의 오른쪽 클릭시 다음 페이지 이동
						else {
							$('#book-m').turn('next');
						}
					}
					//가로 길게 방향이면 Y축 기준 이벤트
					else {
						let offsetY = e.touches[0].clientY - offset.y;
						let half = $(this).height()/2;

						//책의 왼쪽 클릭시 이전 페이지 이동
						if(offsetY < half) {
							$('#book-m').turn('next');
						}
						//책의 오른쪽 클릭시 다음 페이지 이동
						else {
							$('#book-m').turn('previous');
						}
					}
				});
			}
		}
	});

	$('.number-pages').html(numberOfPages); //전체 페이지수 출력

	//책 터치시 페이지 넘김 이벤트
	/*
            for (let i = 1; i < numberOfPages; i++) {   //페이지마다 반복
                $('#data_' + i).bind('touchstart', function (e) {
                    //터치 이벤트는 여러 손가락 터치를 인식하므로 첫번째 터치를 의미하는 touches[0] 사용
                    //getBoundingClientRect(): 요소에 대한 뷰포트 기준의 상대적인 위치 정보를 구하는 메소드.(터치 이벤트는 offset 좌표가 없으므로 계산해서 구해야 함)
                    const offset = e.target.getBoundingClientRect();
                    const offsetX = e.touches[0].clientX - offset.x;

                    let half = $(this).width()/2;

                    //책의 왼쪽 클릭시 이전 페이지 이동
                    if(offsetX < half) {
                        $('#book-m').turn('previous');
                    }
                    //책의 오른쪽 클릭시 다음 페이지 이동
                    else {
                        $('#book-m').turn('next');
                    }
                });
            }
     */

}

/**
 * [기능 함수] book 요소에 page 추가하는 함수
 * @author zisooya
 * @returns {void}
 */
function addPage(page, book) {
	let ebookImageInfo = g_ebookImageInfo;
	let numberOfPages = ebookImageInfo.imageCount;
	let imagePath = ebookImageInfo.imagePath;

	if (!book.turn('hasPage', page)) { //페이지가 이미 책에 있는지 체크
		//페이지를 위한 요소
		let element;
		//모바일 아닌 경우
		if (!isMobile()) {
			let oddOrEven = (page%2===0) ? 'odd' : 'even';
			element = $('<div />', {'class': 'page '+oddOrEven, 'id': 'page-'+page, 'onclick': 'turnBook(\''+oddOrEven+'\')'}).html('<i class="loader"></i>');
		}
		//모바일인 경우
		else {
			element = $('<div />', {'class': 'page', 'id': 'page-' + page}).html('<i class="loader"></i>');
		}

		//page에 element 요소를 추가
		book.turn('addPage', element, page);
		element.html('<img id="data_'+page+'" src="'+imagePath+page+'.png">');
		let controller = '';

		if (!isMobile()) {
			controller = '' +
				'	<div id="controls">\n' +
				'		<div class="page-navi">\n' +
				'			<label for="page-number"></label> <input type="text" id="page-number" value="' + page + '">/<span class="number-pages">' + numberOfPages + '</span>\n' +
				'		</div>\n' +
				'	</div>';
		}
		else {
			//페이지 컨트롤러 삽입
			controller = '    <div id="controls-m">\n' +

				'        <div class="page-navi">\n' +
				'            <label for="page-number"></label> <input type="text" class="page-number" value="' + page + '" readonly>/<span class="number-pages">' + numberOfPages + '</span>\n' +
				'        </div>\n' +
				'        <div class="toc-icon" ontouchstart="openToc()">\n' +
				'            <span class="material-symbols-outlined" style="font-size:24px;">list_alt</span>\n' +
				'        </div>\n' +
				'    </div>';
		}

		element.append(controller);
	}
}

/**
 * [기능 함수] 목차 아이콘 클릭 or 터치시 이벤트
 * @author zisooya
 * @returns {void}
 */
function openToc() {
	$('.toc-container').addClass('active');
}

/**
 * [기능 함수] 목차리스트의 닫기 아이콘 클릭 or 터치시 이벤트
 * @author zisooya
 * @returns {void}
 */
function closeToc() {
	$('.toc-container').removeClass('active');
}

function goPage(pageNum) {
	//목차 리스트 관련 이벤트 초기화
	$('#book').turn('page', pageNum);
	closeToc();
}