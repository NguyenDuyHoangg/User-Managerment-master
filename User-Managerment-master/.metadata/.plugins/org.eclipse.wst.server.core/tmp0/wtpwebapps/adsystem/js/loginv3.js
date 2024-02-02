//Các xử lý kịch bản cho loginv3.html

function checkValidLogin(){
	
	//Lấy thông tin trên giao diện
	let name = document.getElementById("username").value;
	let pass = document.getElementById("userpass").value;
	
	//Khai báo biến xác nhận sự hợp lệ
	var validUserName = true;
	var validUserPass = true;
	
	//Tham chiếu đối tượng hiển thị lỗi
	var viewErrName = document.getElementById("errName");
	var viewErrPass = document.getElementById("errPass");
	
	//Biến ghi nhận thông báo
	var message = "";
	
	//Kiểm tra name
	if(name.trim()==""){
		validUserName = false;
		message = "Thiếu tên đăng nhập vào hệ thống.";
	}else{
		if((name.length<5) || (name.length>50)){
			validUserName = false;
			message = "Tên đăng nhập nên có độ dài trong khoảng 5-50 ký tự.";
		}else{
			if(name.indexOf(" ")!=-1){
				validUserName = false;
				message = "Tên đăng nhập chứa dấu cách.";
			}else{
				if(name.indexOf("@")!=-1){
					var parttern = /\w+@\w+[.]\w/;
					if(!name.match(parttern)){
						validUserName = false;
						message = "Không đúng cấu trúc hộp thư";
					}
				}
				
			}
		}
	}
	
	//Xuất thông báo lỗi tên đăng nhập
	if(validUserName){
		viewErrName.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrName.style.backgroundColor = "transparent";
		viewErrName.style.color = "blue";
	}else{
		viewErrName.innerHTML = message;
		viewErrName.style.backgroundColor = "red";
		viewErrName.style.color = "yellow";
	}
	
	//Kiểm tra mật khẩu
	if(pass.trim()==""){
		validUserPass = false;
		message = "Thiếu mật khẩu vào hệ thống.";
	}else{
		if(pass.length<6){
			validUserPass = false;
			message = "Mật khẩu chưa chính xác.";
		}else{
			
			
		}
		
	}
	
	//Thông báo lỗi mật khẩu
	if(validUserPass){
		viewErrPass.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrPass.style.backgroundColor = "transparent";
		viewErrPass.style.color = "blue";
	}else{
		viewErrPass.innerHTML = message;
		viewErrPass.style.backgroundColor = "red";
		viewErrPass.style.color = "yellow";
	}
	
	//Trả về kết quả kiểm tra
	return validUserName && validUserPass;
}

function login(fn){
	if(this.checkValidLogin()){
		fn.method= "post";
		fn.action = "/adv/user/login";
		fn.submit();
	}
}