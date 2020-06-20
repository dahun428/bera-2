
$(document).ready(function(){
	
	
	$('#custom-nav').bind({
		mouseenter: function() {
			var $btn=$('.nav-sub');
			if(!$btn.is(':animated')) $btn.slideDown(400);
		},
		mouseleave: function() {
			$('.nav-sub').slideUp(400);
		}
	});
	
	var count = 1;
	$('#count-plus').click(function(){
		var totalPrice = 0;
		var price = $('#product-price').text();
		
		count++;
		$('#count').val(count);
		
		totalPrice = count * price;
		$('#product-total-price').text(totalPrice);
	});
	$('#count-minus').click(function(){
		var totalPrice = 0;
		var price = $('#product-price').text();

		if(!(count == 0)){
			count--;
			$('#count').val(count);
		}

		totalPrice = count * price;
		$('#product-total-price').text(totalPrice);
		
	});
	var sel_file;
	//notice 이미지 업로드 미리보기  기능
	$('#imageUpload').on('change', handleImgFileSelect);
	//notice 이미지 파일이름 미리보기 기능
	$("#imageUpload").on('change', function(){
		var fileName;
		if(window.FileReader){
			fileName = $(this)[0].files[0].name;
		} else {
			fileName = $(this).val().split('/').pop().split('\\').pop();
		}
		$("label[for = 'imageUpload' ]").text(fileName);
	});
	$('#notice-submit').click(function(){
		var title = $('#notice-title');
		var content = $('#notice-content');
		
		if(title.val() < 1 ){
			alert('제목을 입력해주세요');
			title.focus();
			return;
		}
	
		$('#notice-form').submit();
		
	});
	//notice 블랭크 이미지 클릭시 파일 업로드 팝업창
	$('.blank_img').click(function(){
		$('#imageUpload').trigger('click');
	});
	
	//파일 삭제 ajax 기능
	//Notice 수정 페이지에서 이미지 파일 삭제 버튼 클릭 이벤트
	$('#notice-image-delete-btn').click(function(){
		$("label[for = 'imageUpload' ]").text("이미지를 첨부해주세요.");
		$('#change-image').attr('src','../image/no_detail_img.jpg');
		$('#notice-image-delete-tr').remove();
	
		var no = $('input[name="no"]').val();
		$.ajax({
			type:"POST",
			data: {no:no},
			url:"../NoticeFileDelete",
			success:function(){
			}
		});
	});
	
	
	
//event function
	var eventCheckboxThumbnail = $('#event-thumbnail-checkbox');
	var eventCheckboxContent = $('#event-content-checkbox');
	var eventCheckboxBanner = $('#event-banner-checkbox');
	
	eventCheckboxThumbnail.eventImgEvent('thumbnail');
	eventCheckboxThumbnail.eventImgPreview('thumbnail');
	eventCheckboxContent.eventImgEvent('content');
	eventCheckboxContent.eventImgPreview('content');
	eventCheckboxBanner.eventImgEvent('banner');
	eventCheckboxBanner.eventImgPreview('banner');
	
	$('#thumbnail-img-delete-btn').eventImgDelete('thumbnail');
	$('#content-img-delete-btn').eventImgDelete('content');
	$('#banner-img-delete-btn').eventImgDelete('banner');
	
	
	eventCheckboxThumbnail.prop('disabled',true);
	eventCheckboxContent.prop('disabled',true);
	eventCheckboxBanner.prop('disabled',true);

	if($('#event-manager-event').is(':checked')){
		eventCheckboxThumbnail.prop('disabled',false);
		eventCheckboxContent.prop('disabled',false);
	} else {
		eventCheckboxBanner.prop('disabled',false);
	}
	$('input[name="event-manager"]').click(function(){
			eventCheckboxThumbnail.prop('checked',false);
			eventCheckboxContent.prop('checked',false);
			eventCheckboxBanner.prop('checked',false);
		
		if($('#event-manager-event').is(':checked')){
			eventCheckboxThumbnail.prop('disabled',false);
			eventCheckboxContent.prop('disabled',false);
			eventCheckboxBanner.prop('disabled',true);
			$('#event-banner-inputbox').remove();
			$('#event-banner-imgbox').remove();	
		} else {
			eventCheckboxBanner.prop('disabled',false);
			eventCheckboxThumbnail.prop('disabled',true);
			eventCheckboxContent.prop('disabled',true);
			$('#event-thumbnail-inputbox').remove();
			$('#event-content-inputbox').remove();
			$('#event-thumbnail-imgbox').remove();
			$('#event-content-imgbox').remove();
			
		}	
	});
	
	
	
	
	
	
});

//event modify 이미지 삭제 function
$.fn.eventImgDelete = function(type){
	this.click(function(){
		var checkDelete = confirm('해당 이미지를 삭제하시겠습니까?');
		if(!checkDelete){
			return;
		}
		$("#event-"+type+"-checkbox").attr("disabled", false);
		$('#event-imageUpload-'+type).attr("disabled",false);
		$("#event-"+type+"-checkbox").attr("checked", false);
		$('#event-'+type+'-inputbox').remove();
		$('#event-'+type+'-imgbox').remove();
		$('#'+type+'-img-delete-btn').remove();
		
		var eventno = $('input[name="eventNo"]').val();
		var eventType = type;
		// var imageName = $('label[for="event-imageUpload-'+type+'"]').val()
		
		var obj = new Object();
		obj.eventno = eventno;
		obj.eventType = eventType;
		// obj.imageName;
		var jsonData = JSON.stringify(obj);
		$.ajax({
			type:"POST",
			dataType:"json",
			data: {jsonData:jsonData},
			url:"../EventFileDelete",
			success:function(){
			}
		});
		
		
	});
}
//event 이미지 등록 html 추가 function
$.fn.eventImgEvent = function(type){
	this.click(function(){
		var eventId = $('#'+this.id);
		if(eventId.is(":checked")){
			var eventTarget = $('#event-'+type);
			var htmlText = '<tr id="event-'+type+'-inputbox"><td colspan="4"><div class="input-group">'
			+'<div class="custom-file">'
			+'<input type="file" class="custom-file-input" name="'+type+'" id="event-imageUpload-'+type+'"/>'
			+'<label for="event-imageUpload-'+type+'" class="custom-file-label">이미지 등록'																
			+ '</label></div></div>'
			+'</td></tr>'
			+'<tr id="event-'+type+'-imgbox"><td colspan="4">'
			+'<div class="blank_img">'
			+'<img src="../image/no_detail_img.jpg" class="card-img" id="change-image-'+type+'" alt="" />'
			+'</div></td></tr>';
			eventTarget.after(htmlText);
			
		} else {
			$('#event-'+type+'-inputbox').remove();
			$('#event-'+type+'-imgbox').remove();
		}
	});
	
}

//event 이미지 미리보기 함수
$.fn.eventImgPreview = function(type){
	this.click(function(){
		$('#event-imageUpload-'+type).on('change', function(e){
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f){
				if(!f.type.match("image.*")){
					alert('확장자는 이미지만 가능합니다.');
					return;
				}
				sel_file = f;
				var reader = new FileReader();
				reader.onload = function(e){
					$('#change-image-'+type).attr("src", e.target.result);
				}
				reader.readAsDataURL(f);
				
			});
			
		});
		var eventTargetId = "event-imageUpload-"+type;
		$('#event-imageUpload-'+type).on('change', function(){
			var fileName;
			if(window.FileReader){
				fileName = $(this)[0].files[0].name;
			} else {
				fileName = $(this).val().split('/').pop().split('\\').pop();
			}
			$("label[for = '"+eventTargetId+"' ]").text(fileName);
		});
	})
	
	
}

$.fn.blankImgTrigger = function(id){
	$(id).trigger('click');
}
//파일 미리보기 기능
function handleImgFileSelect(e){
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f){
		if(!f.type.match("image.*")){
			alert('확장자는 이미지만 가능합니다.');
			return;
		}
		sel_file = f;
		var reader = new FileReader();
		reader.onload = function(e){
			$('#change-image').attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
		
	});
}

