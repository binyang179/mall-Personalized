(function() {
    var width = 320;    // We will scale the photo width to this
    var height = 0;     // This will be computed based on the input stream

    // |streaming| indicates whether or not we're currently streaming
    // video from the camera. Obviously, we start at false.

    var streaming = false;

    // The various HTML elements we need to configure or control. These
    // will be set by the startup() function.
    var video = null;
    var canvas = null;
    var photo = null;
    var startButton = null;
    var registerButton = null;
    var btnloginFace = null;


    var ImgData = null;
    var blob = null;

    function startup() {
        video = document.getElementById('video');
        canvas = document.getElementById('canvas');
        photo = document.getElementById('photo');
        startbutton = document.getElementById('startbutton');
        registerbutton = document.getElementById('btn-regface');
        btnloginFace= document.getElementById('btn-loginFace');

        navigator.mediaDevices.getUserMedia({video: true, audio: false})
            .then(function (stream) {
                video.srcObject = stream;
                video.play();
            })
            .catch(function (err) {
                console.log("An error occurred: " + err);
            });

        video.addEventListener('canplay', function (ev) {
            if (!streaming) {
                height = video.videoHeight / (video.videoWidth / width);

                // Firefox currently has a bug where the height can't be read from
                // the video, so we will make assumptions if this happens.

                if (isNaN(height)) {
                    height = width / (4 / 3);
                }

                video.setAttribute('width', width);
                video.setAttribute('height', height);
                canvas.setAttribute('width', width);
                canvas.setAttribute('height', height);
                streaming = true;
            }
        }, false);

        startbutton.addEventListener('click', function (ev) {
            ev.preventDefault();
            takepicture();
            // var xhr = new XMLHttpRequest();
            // xhr.open("post", "/user/uploadImg", true);
            // xhr.send()
            // uploadImg();
            // ev.preventDefault();
        }, false);


        registerbutton.addEventListener('click', function (ev) {
            ev.preventDefault();
            // REGISTERFACE.reg();
            uploadImg();
        }, false);


        btnloginFace.addEventListener('click', function (ev) {
            ev.preventDefault();
            uploadImgLogin();
        },false);

        // clearphoto();
    }

    function dataURLtoBlob(dataurl) {
        var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
        while(n--){
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {type:mime});
    }

    function takepicture() {
        var context = canvas.getContext('2d');
        // var registerPicName = document.getElementById('regNameface');

        // alert("hello");
        // alert(regName);
        if (width && height) {
            canvas.width = width;
            canvas.height = height;
            context.drawImage(video, 0, 0, width, height);
            // canvas.setAttribute(hidden = "true");

            ImgData = canvas.toDataURL("image/png");

            document.getElementById('photo').src = ImgData;
            // var event = MouseEvent('click');
            // photo.download = "adf.png";
            // photo.href = ImgData;
            // photo.dispatchEvent(event);
            // console.log(ImgData);
        } else {
            // clearphoto();
        }
    }








    function uploadImg() {
        console.log("上传文件");
        // var registerpicname = document.getelementbyid('regnameface');
        var regname = $("#regnameface").val();
        alert(regname);
        ImgData = canvas.toDataURL("image/png");
        // blob = canvas.toblob(function (blob1) {
        //     console.log(blob1.size);
        // }, "image/jpeg");


        // console.log(imgdata);

        blob = dataURLtoBlob(ImgData);
        var uploadpicandname = new FormData();
        console.log("show");
        // console.log(ImgData);
        // console.log(blob);
        console.log(uploadpicandname);
        uploadpicandname.append("registerpic", blob);
        uploadpicandname.append("regsiterpicname", regname);
        console.log(uploadpicandname);

        var registerface={
            inputcheck:function(){
                //不能为空检查
                if ($("#regnameface").val() == "") {
                    showTip("用户名不能为空");
                    $("#regnameface").focus();
                    return false;
                }
                return true;
            },
            beforesubmit:function() {
                console.log("上传文件before");
                console.log(uploadpicandname.keys());

                //检查用户是否已经被占用
                $.ajax({
                    type:"post",
                    url : "/user/check/"+escape($("#regnameface").val()),
                    success : function(result) {
                        if (result==true) {
                            showTip("未注册");
                            //注册
                            // var regdata={
                            //     username:$("#regname").val(),
                            //     urls: imgdata,
                            // }
                            $.ajax({
                                type:"post",
                                url:"/user/reguploadphoto",
                                // data:json.stringify(regdata),
                                // datatype:"json",
                                data: uploadpicandname,
                                // datatype: formdata,
                                processdata: false,
                                contenttype: false,
                                // contenttype:"application/json",
                                success : function(result) {
                                    if (result == true) {
                                        showTip("用户注册成功，请登录！");
                                        $('#mylogin').modal('show') //显示模态框
                                        $('#myreg').modal('hide') //隐层模态框
                                    }
                                    else {
                                        showTip("注册失败！");
                                    }
                                }
                            });
                            // var regData={
                            //     userName: $("#regnameface").val(),
                            //     password: "123456",
                            // }
                            // $.ajax({
                            //     type:"POST",
                            //     url:"/user/register",
                            //     data:JSON.stringify(regData),
                            //     dataType:"json",
                            //     contentType:"application/json",
                            //     success : function(result) {
                            //         if (result == true) {
                            //             showTip("用户注册成功，请登录！");
                            //             $('#myLogin').modal('show') //显示模态框
                            //             $('#myReg').modal('hide') //隐层模态框
                            //         }
                            //         else {
                            //             showTip("注册失败！");
                            //         }
                            //     }
                            // });

                        } else {
                            showTip("此用户名已经被占用，请选择其他用户名");
                            $("#regnameface").select();
                        }
                    }
                });
            },
            reg:function() {
                if (this.inputcheck() ) {
                    this.beforesubmit();
                }
            }
        };
        registerface.reg();
    }








    function uploadImgLogin() {
        var data={
            userName:"admin",
            password:"admin",
        }
        $.ajax({
            type:"POST",
            url:"/user/login",
            data:JSON.stringify(data),
            dataType:"json",
            contentType:"application/json",
            success : function(result) {
                if (result == true) {
                    isLoginShow(false);
                    $('#myLogin').modal('hide') //隐层模态框
                    $.ajax({
                        type: "POST",
                        url: "/user/checkPrefer",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (choose) {
                            if (choose == true) {
                                $('#myPrefer').modal('show');
                            } else {
                                showTip("登录成功！");
                            }
                        }
                    });
                }
                else {
                    showTip("用户名或者密码错误");
                }
            }
        });
    }



    // Set up our event listener to run the startup process
    // once loading is complete.
    window.addEventListener('load', startup, false);
})();