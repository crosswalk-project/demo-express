/*
Copyright (c) 2014 Intel Corporation.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of works must retain the original copyright notice, this list
  of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the original copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
* Neither the name of Intel Corporation nor the names of its contributors
  may be used to endorse or promote products derived from this work without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY INTEL CORPORATION "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL INTEL CORPORATION BE LIABLE FOR ANY DIRECT,
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

Authors:
        Liu, yun <yun.liu@archermind.com>

*/

var documentsDir;
var fileName;

function createfile () {
  if (document.getElementById("fileName").value != "") {
    tizen.filesystem.resolve(
      'documents',
      function(dir) {
        documentsDir = dir; 
        dir.listFiles(createsuccess, onerror);
      }, function(e) {
        $("#filePreview").html("Error: " + e.message);
      }, "rw");
  } else {
    $("#filePreview").html("Please input file name!");
  }
}

function createsuccess(files) {
  fileName = document.getElementById("fileName").value + ".txt";
  if (files.length > 0) {
    for(var i = 0; i < files.length; i++) {
      if (files[i].isDirectory == false && files[i].name == fileName) {
        documentsDir.deleteFile(files[i].fullPath, function () {}, function(e) {
          $("#filePreview").html("DeleteFile error: " + e.message);
        });
      }
    }
  }
  var testFile = documentsDir.createFile(fileName);
  if (testFile != null) {
    testFile.openStream(
      "w",
      function(fs) {
        fs.write(document.getElementById("fileWrite").value);
        fs.close();
        $("#fileName").attr("value", "");
        $("#fileWrite").attr("value", "");
        $("#filePreview").html("Create " + fileName + " file successfully.");
        $("#readfile").removeClass("ui-disabled");
      }, function(e) {
        $("#filePreview").html("CreateFile error: " + e.message);
      }, "UTF-8");
  }
}

function readfile () {
  tizen.filesystem.resolve(
    'documents',
    function(dir) { 
      dir.listFiles(onsuccess, onerror);
    }, function(e) {
      $("#filePreview").html("Error: " + e.message);
    }, "rw");
}

function onsuccess(files) {
  for(var i = 0; i < files.length; i++) {
    if (files[i].isDirectory == false && files[i].name == fileName) {
      files[i].readAsText(
        function(str) {
          $("#filePreview").html("<p>File Name: " + fileName + "</p><p>File Content: " + str);
        }, function(e) {
          $("#filePreview").html("Readfile error: " + e.message);
        }, "UTF-8");
    }
  }
}

function onerror(error) {
  $("#filePreview").html("The error " + error.message + " occurred when listing the files in the selected folder");
}

$(document).ready(function() {
  $("#readfile").addClass("ui-disabled");
  $("#createfile").click(createfile);
  $("#readfile").click(readfile);
});
