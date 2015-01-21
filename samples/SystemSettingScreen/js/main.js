/*
Copyright (c) 2013 Samsung Electronics Co., Ltd.

Licensed under the Apache License, Version 2.0 (the License);
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Authors:
        Choi, Jongheon <j-h.choi@samsung.com>

*/

var path;

window.onload = function(){
    fileImage();
};

$(document).on('click', '#imageHS', function () {
    path = $(this).data("url");
    setSystemProperty("HOME_SCREEN", path, onScreenSetSuccess);
});

$(document).on('click', '#imageLS', function () {
    path = $(this).data("url");
    setSystemProperty("LOCK_SCREEN", path, onScreenSetSuccess);
});

$(document).on('click', '#imageHG', function () {
    var hs_path = $("#imageHS").find("div").data("url");
    if(hs_path == "" || hs_path == null) {
        $("#list1").html("Error: there is no any picture file found.")
    }
    getSystemProperty("HOME_SCREEN", onScreenGetSuccess);
    return false;
});

$(document).on('click', '#imageLG', function () {
    var ls_path = $("#imageLS").find("li").data("url");
    if(ls_path == "" || ls_path == null) {
        $("#list2").html("Error: there is no any picture file found.");
    }
    getSystemProperty("LOCK_SCREEN", onScreenGetSuccess);
    return false;
});

function onError(e) {
    $("#popup_info").modal(showMessage("error", "Error: " + e.message));
}

function onScreenSetSuccess() {
    $("#popup_info").modal(showMessage("success", "Change of SCREEN image"));
}

function onScreenGetSuccess(value) {
    $("#popup_info").modal(showMessage("success", "Image(Get) path : " + value));
    var canvas = document.getElementById("canvas");
    var cx = canvas.getContext("2d");
    var image = new Image();
    image.src = value;
    cx.drawImage(image, 0, 0, 350, 200);
}

function setSystemProperty(property, path, onSuccess) {
    try {
        tizen.systemsetting.setProperty(property, path, onSuccess, onError);
        $("#popup_info").modal(showMessage("success", path));
    } catch (e) {
        $("#popup_info").modal(showMessage("error", "Exception: " + e.message));
    }
}

function getSystemProperty(property, onSuccess) {
    try {
        tizen.systemsetting.getProperty(property, onSuccess, onError);
    } catch (e) {
        $("#popup_info").modal(showMessage("error", "Exception: " + e.message));
    }
}

function fileImage() {
    var documentsDir, length = 0, str = "";
    var len, last, ext;
    function onsuccess(files) {
        if(files.length > 0) {
            $("#list1").html("");
            $("#list2").html("");
            for (var i = 0; i < files.length; i++)
            {
                if(files[i].isFile == true)
                {
                    len = files[i].name.length;
                    last = files[i].name.lastIndexOf(".");
                    ext = files[i].name.substring(last, len);
                    if(ext == ".jpg" || ext == ".jpeg" || ext == ".bmp" || ext == ".png" || ext == ".gif")
                    {
                        var Url = files[i].toURI();
                        Url = Url.replace("file:///", "/");
                        str += '<div class="panel-body" data-url="' + Url + '"><img src="' + files[i].toURI() + '" alt="" />' + "<span class='nbsp3'>" + files[i].name + '</span></div>';
                        length++;
                        if(length >= 6)
                            break;
                    }
                }
            }
        }
        if(length == 0)
            $("#popup_info").modal(showMessage("error", "Not found Image files.\nPlease add image files.\nAdd Path: " + documentsDir.toURI() + "/"));
        $("#imageHS").html(str);
        $("#imageLS").html(str);
    }

    function onerror(error) {
        $("#popup_info").modal(showMessage("error", "The error " + error.message + " occurred when listing the files in the selected folder"));
    }

    tizen.filesystem.resolve(
            'images',
            function(dir){
                documentsDir = dir;
                dir.listFiles(onsuccess, onerror);
            }, function(e) {
                $("#popup_info").modal(showMessage("error", "Error " + e.message));
            }, "r"
    );
}
