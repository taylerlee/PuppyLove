<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width">
<meta name="robots" content="noindex">
<title>領養寵物</title>
<link rel="canonical" href="http://codepen.io/desandro/pen/iHevA" />
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">
<link rel="stylesheet" href="css/animsition.css">
<style>
body {
	width: 100%;
}

.navbar-default {
	box-shadow: 0px 3px 20px #333333;
	-webkit-box-shadow: 0px 3px 20px #333333;
	-moz-box-shadow: 0px 3px 20px #333333;
}

.btn-group-justified {
	margin-top: 100px;
	margin-left: auto;
	margin-right: auto;
	width: 80%;
}

@media ( min-width :768px) {
	#container {
		margin-top: 10px;
		margin-bottom: 0;
		margin-left: auto;
		margin-right: auto;
		width: 80%;
	}
	#load-images {
		position: relative;
		left: 42%;
		box-shadow: 3px 3px 7px #6B6B6B;
		-webkit-box-shadow: 3px 3px 7px #6B6B6B;
		-moz-box-shadow: 3px 3px 7px #6B6B6B;
		background: #3cb0fd;
		background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
		background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
		background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
		background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
		background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
		-webkit-border-radius: 10;
		-moz-border-radius: 10;
		border-radius: 10px;
		color: #ffffff;
		font-size: 20px;
		padding: 10px 20px 10px 20px;
		text-decoration: none;
		cursor: pointer;
		display: inline-block;
	}
	#load-images:hover {
		background: #f0b624;
		background-image: -webkit-linear-gradient(top, #f0b624, #de8621);
		background-image: -moz-linear-gradient(top, #f0b624, #de8621);
		background-image: -ms-linear-gradient(top, #f0b624, #de8621);
		background-image: -o-linear-gradient(top, #f0b624, #de8621);
		background-image: linear-gradient(to bottom, #f0b624, #de8621);
		text-decoration: none;
	}
	.item {
		width: 280px;
		float: left;
	}
	.item img {
		width: 100%;
		margin-bottom: 20px;
	}
	#load_pre {
		position: fixed;
		top: 50%;
		left: 0px;
		width: 50px;
		z-index: 1000;
		color: #600;
		visibility: hidden;
	}
	#load_next {
		position: fixed;
		top: 50%;
		right: 0px;
		width: 50px;
		z-index: 1000;
		color: #009;
		visibility: hidden;
	}
	table {
		font-size: 20px;
		width: 60%;
		margin: 0 auto;
	}
}

@media ( max-width :767px) {
	#container {
		margin-top: 70px;
		margin-bottom: 0;
		margin-left: auto;
		margin-right: auto;
		width: 80%;
	}
	.item {
		width: 250px;
		margin: 0 auto;
	}
	.item img {
		width: 100%;
		margin-bottom: 20px;
	}
	#load_pre {
		position: fixed;
		top: 50%;
		left: -20px;
		width: 50px;
		height: 45px;
		z-index: 1000;
		color: #600;
		visibility: visible;
		cursor: pointer;
	}
	#load_next {
		position: fixed;
		top: 50%;
		right: -20px;
		width: 50px;
		height: 45px;
		z-index: 1000;
		color: #009;
		visibility: visible;
		cursor: pointer;
	}
	#load-images {
		display: none;
	}
	table {
		font-size: 14px;
		width: 100%;
		margin: 0 auto;
	}
}
</style>
<script	src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js'></script>
<script src='http://masonry.desandro.com/masonry.pkgd.js'></script>
<script src='http://imagesloaded.desandro.com/imagesloaded.pkgd.js'></script>
<script src="js/jquery.animsition.js"></script>
<script>
$(document).ready(function() {
	
	$(".animsition").animsition({
		  
	    inClass               :   'fade-in-up-lg',
	    outClass              :   'fade-out-down-lg',
	    inDuration            :    1500,
	    outDuration           :    800,
	    linkElement           :   '.animsition-link',
	    // e.g. linkElement   :   'a:not([target="_blank"]):not([href^=#])'
	    loading               :    true,
	    loadingParentElement  :   'body', //animsition wrapper element
	    loadingClass          :   'animsition-loading',
	    unSupportCss          : [ 'animation-duration',
	                              '-webkit-animation-duration',
	                              '-o-animation-duration'
	                            ],
	    //"unSupportCss" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
	    //The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
	    
	    overlay               :   false,
	    
	    overlayClass          :   'animsition-overlay-slide',
	    overlayParentElement  :   'body'
	  });
	
	
		$('#all').attr('data-animsition-out','fade-out-up-lg');
		$('#dog').attr('data-animsition-out','fade-out-up-lg');
		$('#cat').attr('data-animsition-out','fade-out-up-lg');
		$('#oth').attr('data-animsition-out','fade-out-up-lg');
	
	});
</script>
<script>

	$(function() {
		var $container = $('#container').masonry({
			itemSelector : '.item',
			columnWidth : 300,
			gutter: 1
		});

		$(document).delegate("#container img", "click", function() {
			loadInfo(this);
			//$( "#dialog-form" ).dialog();
		});

		load_animal('default');

		if (location.href.match('type=dog') != null) {
			$('#dog').addClass('active');
		} else if (location.href.match('type=cat') != null) {
			$('#cat').addClass('active');
		} else if (location.href.match('type=other') != null) {
			$('#oth').addClass('active');
		} else if (location.href.match('type=all') != null) {
			$('#all').addClass('active');
		}

		$('#load-images').click(function() {
			load_animal('next');
		});

		$('#load_pre').click(function() {

			$('#container').empty();
			$('#container').masonry('reloadItems');
			$('#container').masonry('layout');
			load_animal('pre');
		});
		$('#load_next').click(function() {
			$('#container').empty();
			$('#container').masonry('reloadItems');
			$('#container').masonry('layout');
			load_animal('next');
		});

		function load_animal(page) {
			var items = '';
			$
					.ajax({
						type : "GET",
						async : false,
						url : "LoadPuppy?type=${param['type']}&page=" + page,
						dataType : "json",
						success : function(Jdata) {
							var NumOfJData = Jdata.length;
							for (var i = 0; i < NumOfJData; i++) {
								//console.log(Jdata[i].id);
								//console.log(Jdata[i].img);
								var item = '<div class="item"><a href="#adoptionModal" class="adoption-link" data-toggle="modal"><img src="' + Jdata[i].img + '" id="' + Jdata[i].id + '"  style="cursor: pointer;"></a></div>';
								items += item;
								//$("#loadImg").append('<img src="' + Jdata[i].img + '" id="' + Jdata[i].id + '"  style="cursor: pointer;">');
							}
						},
						error : function() {
							alert("ERROR!!!");
						}
					});

			var $items = $(items);
			$items.hide();
			$container.append($items);
			$items.imagesLoaded().progress(function(imgLoad, image) {
				var $item = $(image.img).parents('.item');
				$item.show();
				$container.masonry('appended', $item);
			});
		}

		function loadInfo(obj) {
			var id = $(obj).attr('id');
			console.log(id);
			$.ajax({
				type : "POST",
				url : "LoadPuppy",
				data : {
					id : id
				},
				success : function(Jdata) {
					$("#img_src").attr("src", Jdata.album);
					$("#check_id").text(Jdata.check_id);
					$("#sub_id").text(Jdata.sub_id);
					$("#shelter_id").text(Jdata.shelter_id);
					$("#shelter_name").text(Jdata.shelter_name);
					$("#place").text(Jdata.place);
					$("#area").text(Jdata.area);
					$("#kind").text(Jdata.kind);
					$("#sex").text(Jdata.sex);
					$("#bodyType").text(Jdata.bodyType);
					$("#colour").text(Jdata.colour);
					$("#age").text(Jdata.age);
					$("#sterilization").text(Jdata.sterilization);
					$("#bacterin").text(Jdata.bacterin);
					$("#foundPlace").text(Jdata.foundPlace);
					$("#status").text(Jdata.status);
					$("#openDate").text(Jdata.openDate);
					$("#closedDate").text(Jdata.closedDate);
					$("#remark").text(Jdata.remark);
				},
				error : function() {
					alert("ERROR!!!");
				}
			});
		}
	});
</script>
<script
	src='http://codepen.io/assets/editor/live/css_live_reload_init.js'></script>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="index.html#page-top">寵物緣</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="index.html#news">最新公告</a></li>
					<li><a class="page-scroll" href="index.html#adoption">寵物領養</a>
					</li>
					<li><a class="page-scroll" href="index.html#search">搜尋寵物</a></li>
					<li><a class="page-scroll" href="index.html#hospital">寵物醫院</a>
					</li>
					<li><a class="page-scroll" href="index.html#contact">聯絡我們</a>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="btn-group btn-group-justified animsition" style="">
		<a class="btn btn-default animsition-link"
    data-animsition-out="fade-out-left-lg"
    data-animsition-out-duration="2000" role="button" id="all"
			onclick="location.href='SearchPuppy?type=all'">全部</a> <a class="btn btn-default animsition-link"
    data-animsition-out="fade-out-left-lg"
    data-animsition-out-duration="2000" role="button" id="dog"
			onclick="location.href='SearchPuppy?type=dog'">狗</a> <a
			 class="btn btn-default animsition-link"
    data-animsition-out="fade-out-left-lg"
    data-animsition-out-duration="2000" role="button" id="cat"
			onclick="location.href='SearchPuppy?type=cat'">貓</a> <a
			 class="btn btn-default animsition-link"
    data-animsition-out="fade-out-left-lg"
    data-animsition-out-duration="2000" role="button" id="oth"
			onclick="location.href='SearchPuppy?type=other'">其他</a>
	</div>
	<div id="container" class="animsition"></div>


	<p>
		<span id="load-images" class="animsition">載入更多</span>
	</p>
	<br>
	<div id="load_pre">
		<img alt="上一頁" src="img/prev.png">
	</div>
	<div id="load_next">
		<img alt="下一頁" src="img/next.png">
	</div>


	<div class="adoption-modal modal fade" id="adoptionModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body">
							<!-- Project Details Go Here -->
							<img class="img-responsive img-centered" src="" id="img_src"
								width="90%">
							<h4 id="remark"></h4>
							<table class="table table-hover">
								<tbody>
									<tr>
										<td align="left" class="success">流水編號</td>
										<td class="success"><span id="check_id"></span></td>
									</tr>
									<tr>
										<td align="left">區域編號
										<td><span id="sub_id"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">所屬縣市
										<td class="success"><span id="area"></span></td>
									</tr>
									<tr>
										<td align="left">所屬收容所
										<td><span id="shelter_name"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">實際所在地
										<td class="success"><span id="place"></span></td>
									</tr>
									<tr>
										<td align="left">類型
										<td><span id="kind"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">性別
										<td class="success"><span id="sex"></span></td>
									</tr>
									<tr>
										<td align="left">體型
										<td><span id="bodyType"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">毛色
										<td class="success"><span id="colour"></span></td>
									</tr>
									<tr>
										<td align="left">年紀
										<td><span id="age"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">是否絕育
										<td class="success"><span id="sterilization"></span></td>
									</tr>
									<tr>
										<td align="left">是否施打狂犬病疫苗
										<td><span id="bacterin"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">尋獲地
										<td class="success"><span id="foundPlace"></span></td>
									</tr>
									<tr>
										<td align="left">狀態
										<td><span id="status"></span></td>
									</tr>
									<tr>
										<td align="left" class="success">開放認養時間(起)
										<td class="success"><span id="openDate"></span></td>
									</tr>
									<tr>
										<td align="left">開放認養時間(迄)
										<td><span id="closedDate"></span></td>
									</tr>
								</tbody>
							</table>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<i class="fa fa-times"></i>關閉
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/bootstrap.js"></script>
</body>
</html>