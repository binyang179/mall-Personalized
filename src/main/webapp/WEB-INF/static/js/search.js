
$('.btn-search').on('click', function() {

	if($('.search-query').val() !== "") {
		window.location.href="/home/product/search/"+$('.search-query').val()+"/9/0";
	}
})

/**
 * a 标签
 * form表单
 * location.href
 * window.open
 */
