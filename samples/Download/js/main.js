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

var gDownloadId, gDocumentsDir, gFiles;

window.onload = function(){
    $("#download").click(downloaded);
    $("#resume").click(resume);
    $("#pause").click(pause);
    $("#cancel").click(cancel);
    $("#delete").click(deleteAllFile);
    prepareDirsAndFiles();
};

function downloaded() {
    if (gDownloadId != undefined) {
        var state = tizen.download.getState(gDownloadId);

        if (state == "DOWNLOADING") {
            $("#popup_info").modal(showMessage("success", "Already downloading"));
        } else {
            $("#popup_info").modal(showMessage("error", "Another download in progress"));
        }
    } else {
        download();
    }
}

function resume() {
    if (gDownloadId != undefined) {
        var state = tizen.download.getState(gDownloadId);

        if (state == "PAUSED") {
            try {
                tizen.download.resume(gDownloadId);
                //$("#popup_info").modal(showMessage("success", "Resumed"));
            } catch (exc) {
                $("#popup_info").modal(showMessage("error", "download.resume failed: " + exc.message));
            }
        } else {
            $("#popup_info").modal(showMessage("error", "Another download in progress"));
        }
    } else {
        $("#popup_info").modal(showMessage("error", "No download in progress"));
    }
}

function pause() {
    if (gDownloadId == undefined) {
        $("#popup_info").modal(showMessage("error", "No download in progress"));
        return false;
    }

    var state = tizen.download.getState(gDownloadId);

    if (state == "PAUSED") {
        $("#popup_info").modal(showMessage("success", "Already paused"));
    } else {
        try {
            tizen.download.pause(gDownloadId);
            //$("#popup_info").modal(showMessage("success", "Paused"));
        } catch (exc) {
            $("#popup_info").modal(showMessage("error", "download.pause failed: " + exc.message));
        }
    }
}

function cancel() {
    if (gDownloadId == undefined) {
        $("#popup_info").modal(showMessage("error", "No download in progress"));
        return false;
    }

    try {
        tizen.download.cancel(gDownloadId);
    } catch (exc) {
        $("#popup_info").modal(showMessage("error", "download.cancel failed: " + exc.message));
    }
}

function onError(err) {
    $("#popup_info").modal(showMessage("error", "Error: " + err.message));
}

function prepareDirsAndFiles() {
    try {
        tizen.filesystem.resolve("downloads", function(dir) {
            gDocumentsDir = dir;
            showFileList();
        }, onError, "rw");
    } catch (exc) {
        $("#popup_info").modal(showMessage("error", "tizen.filesystem.resolve(\"downloads\") exc: " + exc.message));
    }
}

function makeFileList(files) {
    var str = "";

    gFiles = files;

    if (files.length == 0) {
        str = '<div class="panel-body">N/A</div>';
    }

    for (var i = 0; i < files.length; i++) {
        if (files[i].isDirectory == false) {
            str += '<div class="panel-body">'
                + files[i].name
                + '<button type="button" class="btn btn-default btn-block" onclick="deleteFileFromFolder('+ i +')">Delete</button></div>';
        }
    }
    $("#downloadFolderList").html(str);
}

function showFileList() {
    if(gDocumentsDir) {
        gDocumentsDir.listFiles(function(files) {
            makeFileList(files);
        }, onError);
    }
}

function deleteFileFromFolder(id) {
    if (id == null) {
        return;
    }

    try {
        gDocumentsDir.deleteFile(gFiles[Number(id)].fullPath, showFileList, onError);
        //$("#popup_info").modal(showMessage("success", "Download delete"));
    } catch (exc) {
        $("#popup_info").modal(showMessage("error", "deleteFile exc: " + exc.message));
    }
}

function deleteAllFile() {
    $("#popup_info").modal(showMessage("success", "Download delete all"));
    if(gFiles.length > 0)
        for(var i = 0; i < gFiles.length; i++)
            if(gFiles[i].isFile == true)
                gDocumentsDir.deleteFile(gFiles[i].fullPath, showFileList, onError);
}

function download() {
    var url = $("#url").val();

    if (url == "") {
        $("#popup_info").modal(showMessage("success", "Input target URL"));
        return;
    }

    var downloadRequest = new tizen.DownloadRequest(url, "downloads"),
    listener = {
        onprogress: function(id, receivedSize, totalSize) {
            //$("#progressbar").progressbar("option", "value", receivedSize/totalSize*100);
            if(receivedSize > 0)
                $("#progressbar").reportprogress(receivedSize/totalSize*100);
            console.log('Received with id: ' + id + ', ' + receivedSize + '/' + totalSize);
        },
        onpaused: function(id) {
            console.log('Paused with id: ' + id);
            //showFileList();
        },
        oncanceled: function(id) {
            $("#popup_info").modal(showMessage("success", "Canceled"));
            //showFileList();
            console.log(id);
            gDownloadId = undefined;
            $("#progressbar").reportprogress(0);
        },
        oncompleted: function(id, fullPath) {
            $("#popup_info").modal(showMessage("success", "Completed! Full path: " + fullPath));
            showFileList();
            gDownloadId = undefined;
        },
        onfailed: function(id, error) {
            $("#popup_info").modal(showMessage("error", "Failed! Err: " + error.name));
            //showFileList();
            gDownloadId = undefined;
            $("#progressbar").reportprogress(0);
        }
    };

    //$("#progressbar").progressbar("option", "value", 0);
    $("#progressbar").reportprogress(0);

    try {
        gDownloadId = tizen.download.start(downloadRequest, listener);
        //$("#popup_info").modal(showMessage("success", "Download"));
    } catch (exc) {
        $("#popup_info").modal(showMessage("error", "download.start failed : " + exc.message));
    }
}

var pct=0;
var handle=0;
function update(){
    $("#progressbar").reportprogress(++pct);
    if(pct==100){
        clearInterval(handle);
        $("#run").val("start");
        pct=0;
    }
}
jQuery(function($){
    $("#run").click(function(){
        if(this.value=="start"){
            handle=setInterval("update()",100);
            this.value="stop";
        }else{
            clearInterval(handle);
            this.value="start";
        }
    });
    $("#reset").click(function(){
        pct=0;
        $("#progressbar").reportprogress(0);
    });
});
