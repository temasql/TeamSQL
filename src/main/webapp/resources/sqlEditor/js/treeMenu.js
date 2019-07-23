$(document).ready(function() {
	function tree_menu() {
		  // $('.depth_2');
		  $('ul.depth_2 >li > a').click(function(e) {

		    var temp_el = $(this).next('ul');
		    var depth_3 = $('.depth_3');

		    // 처음에 모두 슬라이드 업 시켜준다.
		    depth_3.slideUp(300);
		    // 클릭한 순간 모두 on(-)을 제거한다.// +가 나오도록
		    depth_3.parent().find('em').removeClass('on');

		    if (temp_el.is(':hidden')) {
		      temp_el.slideDown(300);
		      $(this).find('em').addClass('on').html('하위폴더 열림');
		    } else {
		      temp_el.slideUp(300);
		      $(this).find('em').removeClass('on').html('하위폴더 닫힘');
		    }

		    return false;

		  });
		}
		if ($('#tree_menu').is(':visible')) {
		  tree_menu();
		}
});

