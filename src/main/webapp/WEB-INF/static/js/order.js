var obj='';
function ShoppingCarObserver(elInput) {
	this.elInput = elInput;
	var ids = elInput.id;//count:购物车Id
	this.cartId=ids.split(',')[0];
	this.goodsId=ids.split(',')[1];
	this.changeButton = function() {
		$("#evaluate-"+this.cartId).css("display","none");
		$("#detail-"+this.cartId).css("display","");
	}
}

function InitStar(index){
	$(".order-evaluation ul li:eq(0)").find("img").attr("src","/static/images/x1.png");
	for (var i=0;i<index;i++){

		$(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","/static/images/x2.png");
	}

}

$('.shopping-car-container').on('click', '.item-evaluate', function() {
	var goodsInput = $(this).parents('.goods-item').find('.goods-operate')[0]
	addevaluation=new ShoppingCarObserver(goodsInput);
	obj=$(this);
	$("#order_evaluation").css('display','')
	InitStar(0);
	$("#TextArea1").text('');
	$('#myEva').modal('show');
})

;
//评价功能
var Evaluate = {
	checkInput:function() {
		/*if ($("#TextArea1").val()== "") {
			alert('内容不能为空');

			$("#TextArea1").focus();
			return false;
		}*/
		return true;
	},
	doEvaluate:function() {
		if (addevaluation !== null) {
			var data = {
				cartId: addevaluation.cartId,
				goodsId: addevaluation.goodsId,
				grade: $(".block li").attr("data-default-index"),
				comment: $("#TextArea1").val(),
			}
			//ajax代码实现
			$.ajax({
				type: "POST",
				url: "/order/addEvaluation",
				data: JSON.stringify(data),
				dataType: "json",
				contentType: "application/json",
				success: function (result) {
					if (result == true) {
						$("#order_evaluate_modal").html("感谢您的评价！").show().delay(3000).hide(500);
						$('#myEva').modal('hide');
						addevaluation.changeButton();
					} else {
						showTip('请先登录');
					}
				}
			});
		}
	},
	evaluate:function() {
		if (this.checkInput()) {
			this.doEvaluate();
		}
	}

};
$("#order_evaluation").click(function(){
	Evaluate.evaluate();
})

$('.shopping-car-container').on('click', '.item-detail', function() {
	var goodsInput = $(this).parents('.goods-item').find('.goods-operate')[0]
	showevaluation=new ShoppingCarObserver(goodsInput);
	//ajax代码实现
	var data={
		cartId:showevaluation.cartId,
	}
	$.ajax({
		type:"POST",
		url:"/order/getByCartId",
		data:JSON.stringify(data),
		dataType:"text",
		contentType:"application/json",
		success : function(result) {
			var grade=result.split(',')[0];
			InitStar(grade);
			$("#TextArea1").text(result.split(',')[1]);
		}
	});
	$("#order_evaluation").css('display','none')
	$('#myEva').modal('show');
})