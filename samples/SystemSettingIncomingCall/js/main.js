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
  fileAudio();
};

function setCall() {
  path = $("#list").find("div").data("url");
  if(path == null || path =="") {
    $("#list").html("Error: there is no any audio file found.");
  }
  setSystemProperty("INCOMING_CALL", path, onIncomingCallSetSuccess);
}

function getPath() {
  try {
    tizen.systemsetting.getProperty("INCOMING_CALL", function(value) {
      $("#popup_info").modal(showMessage("success", "Sound(Get) path : " + value));
    }, onError);
  } catch (e) {
    $("#popup_info").modal(showMessage("error", "Exception: " + e.message));
  }
}

function play() {
  getSystemProperty("INCOMING_CALL", onIncomingCallGetSuccess);
}

function onError(e) {
    $("#popup_info").modal(showMessage("error", "Error: " + e.message));
}

function onIncomingCallSetSuccess() {
    $("#popup_info").modal(showMessage("success", "Change of INCOMING_CALL ringtone"));
}

function onIncomingCallGetSuccess(value) {
    var audio = document.getElementById("MyAudio");
    audio.src = value;
    audio.type = "audio/*";
    audio.play();
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

function fileAudio() {
    var documentsDir, length = 0, str = "";
    function onsuccess(files) {
        if(files.length > 0) {
          $("#list").html("");
          for (var i = 0; i < files.length; i++)
          {
              if(files[i].isFile == true)
              {
                  var Url = files[i].toURI();
                  Url = Url.replace("file:///", "/");
                  str += '<div class="panel-body" data-url="' + Url + '">' + files[i].name + '</div>';
                  length++;
                  if(length >= 9)
                      break;
              }
          }
        }
        if(length == 0)
            $("#popup_info").modal(showMessage("error", "Not found Sound files\nPlease add sound files.\nAdd Path: " + documentsDir.toURI() + "/"));
        $("#list").html(str);
    }

    function onerror(error) {
        $("#popup_info").modal(showMessage("error", "The error " + error.message + " occurred when listing the files in the selected folder"));
    }

    tizen.filesystem.resolve(
            'ringtones',
            function(dir){
                documentsDir = dir;
                dir.listFiles(onsuccess, onerror);
            }, function(e) {
                $("#popup_info").modal(showMessage("error", "Error " + e.message));
            }, "r"
    );
}
