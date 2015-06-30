
//校园寻宝
function treasure() {
	$(".donghua").animate({
		translate3d : '0,600%,0',
	}, 500, 'ease-in', function() {
		$(".donghua").find(".aa").hide();
		$(".after").show();
		$(".ms-container ").addClass("shake");
		$(".treasure .close").show();
		$(".oo").animate({
			translate3d : '0,-80%,0',
			scale : '5'
		}, 300, 'ease-in')
		$(".gg").animate({
			translate3d : '-150%,-180%,0',
			scale : '4'
		}, 300, 'ease-in')
		$(".yy").animate({
			translate3d : '190%,-180%,0',
			scale : '4'
		}, 350, 'ease-in')
		$(".rr").animate({
			translate3d : '150%,-300%,0',
			scale : '4'
		}, 300, 'ease-in')

		$(".hb").animate({
			translate3d : '0,-600%,0',
			scale : '7'
		}, 800, 'ease-in')
	})
}

$(function() {
	$(".treasure>div.close").tap(function() {
		$(".treasure").hide();
		$(".treasure-plug ").show() 
		$(".treasure-plug .flash").addClass("bounceInRight");
	});
	$(".treasure .close").hide();
	$(".treasure-plug").hide();
	$(".more").tap(function() {
		Ms.loadUrl("${base}/html/83/1951/8421.html");
	});
	$(".treasure").hide();
	// setTimeout("treasure()",1000); //开启寻宝与关闭

	// 获取当前系统时间，计算单双周时使用
	var systemTime = $(".month").attr("data-date");
	
	// 根据cookie值判断使用是否使用过，如果使用过就要进行单双周的判断否则默认为“单周”
	// 并且将默认“单周”保存cookie
	//cookie的格式:数字|时间　
	var cookieWeek = $.fn.cookie('cookieWeek');
	var weekType = 1;//保存周数，被２整除表示双周 默认１表示单周
	if (typeof (cookieWeek) == "undefined" || cookieWeek==null) {
		$.fn.cookie('cookieWeek', "1|"+systemTime, {
			expires : 150
		});// 默认单周
		$(".weekType").html("单周");
	} else  {//cookie存在就显示保存在cookie里面的值，并且需要用cookie里面的时间与现在的服务器时间对比进行单双周的计算
		var cookieWeekDate = $.fn.cookie('cookieWeek');//历史保存在cookie的时间			
		var _week = new Date(cookieWeekDate.split("|")[1]).getDay();//获取cookie中日期的星期几
		 weekType = Math.floor(((dateDiff(cookieWeekDate.split("|")[1],systemTime)+_week-1)/7))+parseInt(cookieWeekDate.split("|")[0]);//根据cookie里面的历史时间与当前服务器时间计算周数
		 if (weekType%2==0){
		 	$(".weekType").html("双周");
		 } else {
		 	$(".weekType").html("单周");
		 }
		
	}

	//根据服务器的时间计算当前星期几，并且将光标移动到对于的星期
	// 获取当前星期几
	var week = new Date(systemTime).getDay();
	// 计算这一周的日期
	$(".week").each(function() {
				if (week == 0) {
					week = 7;
				}
				var day = dateDiffDay(systemTime, eval(parseInt($(this).attr("data-id")) - parseInt(week)));
				$(".week[data-id='" + $(this).attr("data-id") + "'] .day").html("<p>" + day + "</p>");
	});
	
	moveSel(week - 1);//移动光标
	$(".curriculum").each(function(i) {
		var curriculumHtml = curriculum((i + 1), weekType);
		$(this).html(curriculumHtml); 
	});

	// 课表拖动效果
	Ms.msIscroll[0].on('scrollEnd', function() {
		var num = this.x / $(window).width();
		moveSel(num * -1);
	});

	// 用户点击切换单双周，切换后将结果保存在cookie
	$(".weekType").tap(function() {
		var _weekType =1;
		if ($(this).html() == "双周") {
			$(this).html("单周");
			Ms.msg("已经切换至单周<br/>提示:如果是临时切换课表,记得切换回来哦！",{"autoHide":false});
		} else {
			_weekType = 2;
			Ms.msg("已经切换至双周<br/>提示:如果是临时切换课表,记得切换回来哦！",{"autoHide":false});
			$(this).html("双周");
		}
		$.fn.cookie('cookieWeek', _weekType+"|"+systemTime, {
			expires : 150
		}); // 更新cookie
		// 刷新课表
		$(".curriculum").html("...");
		$(".curriculum").each(function(i) {
			var curriculumHtml = curriculum((i + 1), _weekType);
			$(this).html(curriculumHtml);
		});
	});

	// 获取并显示历史课表数据
	$(".reselect")
			.on(
					"tap",
					function() {
						var historyClassHtml = "";// 课表记录的HTML代码

						$("#reselect .ms-modal-body")
								.html(
										'<div class="ms-font-size2"><div style="border-bottom:1px solid #CCCCCC;"></div><div class="ms-w100 ms-text-center">暂无历史记录</div></div>	');
						$
								.ajax({
									type : "POST",
									url : "${base}/mobile/curriculum/historyClassIds.do",
									beforeSend : function() {
										$(
												".contentHistoryClass .histiryClassName")
												.attr("data-classId", 0);
										$(
												".contentHistoryClass .histiryClassName")
												.html("数据加载中...");
										historyClassHtml = $(
												".contentHistoryClass").html();
										$("#reselect  .ms-modal-body").html(
												historyClassHtml);
										historyClassHtml = "";
									},
									success : function(msg) {
										if ($.parseJSON(msg).result == true) {
											var historyClassIds = $.parseJSON($
													.parseJSON(msg).resultMsg);
											for ( var i = historyClassIds.length; i > 0; i--) {
												$(
														".contentHistoryClass .histiryClassName")
														.attr(
																"data-classId",
																historyClassIds[i - 1].categoryId);
												$(
														".contentHistoryClass .histiryClassName")
														.html(
																historyClassIds[i - 1].categoryTitle);
												historyClassHtml += $(
														".contentHistoryClass")
														.html();
											}
										} else {
											$(
													".contentHistoryClass .histiryClassName")
													.attr("data-classId", 0);
											$(
													".contentHistoryClass .histiryClassName")
													.html(
															$.parseJSON(msg).resultMsg);
											historyClassHtml = $(
													".contentHistoryClass")
													.html();
										}
										$("#reselect  .ms-modal-body").html(
												historyClassHtml);
									}

								});
					});

	$(".histiryClassName").live("tap", function() {
		$(".curriculumClassId").val($(this).attr("data-classId"));
		$(".curriculumClass").submit();
	});

	// 自定义课表
	$(".diyClass").on("tap", function() {
		// alert($(this).html());
	});
});

// 显示当前选中课表特效
function moveSel(i) {
	var num = i;
	var mw = $(".tab").width(); // 头部月份显示宽度
	var week = $(".week").width(); // 星期的宽度
	$('#selector').animate({
		translate3d : (num * week) + 'px,0,0'
	}, 150, 'ease-in');
	// alert(".week[data-id='"+(i+1)+"']");
	$(".week").removeClass("sel");
	$(".week[data-id='" + (i + 1) + "']").addClass("sel");
}

/*
 * 加载当前课表数据 week:用户选中的日期
 */
function curriculum(week, weekType) {
	// 上课时间
	var courseTime = [ "0102", "0304", "0506", "0708", "0910" ];

	// 控制课程摆放顺序的Css
	var courseDivCss = [ "one", "two", "three", "four", "five" ];

	// 清楚原来课表数据
	for ( var f = 0; f < courseDivCss.length; f++) {
		$(".contentCurriculum ." + courseDivCss[f]).html('');
	}

	// 加载当前课表
	for ( var i = 0; i < curriculumJson.length; i++) {

		className = curriculumJson[i].className;

		for ( var n = 0; n < courseTime.length; n++) {// 判断上课时间
			if (curriculumJson[i].classTime == (week + courseTime[n])) {
				// 判断当前为单周还是双周
				var classWeek = (weekType%2==0)?"双周":"单周";
				if (weekType %2==0) {
					if (curriculumJson[i].week == 1) {
						continue;
					}
				} else {
					if (curriculumJson[i].week == 2) {
						continue;
					}
				}


				$(".contentCurriculum ." + courseDivCss[n])
						.html(
								'<div  style="color: #0eb705;font-size:1.2em">'
										+ curriculumJson[i].courseName
										+ '</div><div style="font-size:1em">'
										+ curriculumJson[i].classRoomName
										+ '/'
										+ curriculumJson[i].teacherName
										+ '</div><div class="ms-font-size1" style="font-size:0.8em">1-18周'
										+ classWeek + '</div>');
			}
		}
	}
	return $(".contentCurriculum").html();
}

/**
 * 计算天数差的函数，通用 sDate1:开始日期 sDate2:结束日期 返回:相差的天数
 */
function dateDiff(sDate1, sDate2) { // sDate1和sDate2是2006-12-18格式
	var aDate, oDate1, oDate2, iDays
	aDate = sDate1.split("-");
	oDate1 = new Date() // 转换为12-18-2006格式
	oDate1.setFullYear(aDate[0]);
	oDate1.setMonth(eval(aDate[1] - 1));
	oDate1.setDate(aDate[2]);
	aDate = sDate2.split("-")
	oDate2 = new Date();
	oDate2.setFullYear(aDate[0]);
	oDate2.setMonth(eval(aDate[1] - 1));
	oDate2.setDate(aDate[2]);
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24) // 把相差的毫秒数转换为天数
	return iDays
}

/**
 * 计算天数差的函数，通用 date:当前日期 n:相差的天数 返回:只返回日，并不返回年月
 */
function dateDiffDay(date, n) {
	var _date = date.split("-");
	var time = new Date();
	time.setFullYear(_date[0]);
	time.setMonth(eval(_date[1] - 1));
	time.setDate(_date[2]);
	var newTime = time.getTime() + n * 24 * 60 * 60 * 1000;
	return new Date(newTime).getDate();
};