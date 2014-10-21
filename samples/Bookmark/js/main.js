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

var bookmarkList;
var ret;
var j;

function add() {
  var bookmarkTitle = document.getElementById("bookmarkTitle").value;
  var bookmarkURL = document.getElementById("bookmarkURL").value;
  bookmarkList[j] = new tizen.BookmarkItem(bookmarkTitle, bookmarkURL);
  tizen.bookmark.add(bookmarkList[j]);
  j++;
  $("#bookmarkTitle").attr("value", "");
  $("#bookmarkURL").attr("value", "");
  show();
}

function show() {
  ret = tizen.bookmark.get();
  var a = 0;
  $("#bookmarklist").html("");
  if (ret.length > 0) {
    $("#bookmarklist").html("<table id='list' style='width: 100%; text-align: center; word-break:break-all; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'><tr><td style='width: 20%; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'>Title</td><td style='width: 65%; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'>URL</td><td style='width: 15%; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'>Operation</td></tr></table>");
    var table = document.getElementById("list");
    for(var i = 0; i < ret.length; i++) {
      var tr = document.createElement("tr");
      tr.innerHTML = "<td style='font-weight: normal; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'>" + ret[i].title + "</td><td style='font-weight: normal; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'>" + ret[i].url + "</td><td style='font-weight: normal; border: 1px; border-collapse: collapse; border-width: thin; border-style: solid;'><a href='javascript: remove(" + i +");'>remove</a></td>";
      table.appendChild(tr);
    }
  }
  else {
    $("#bookmarklist").html("N/A");
  }
}

function remove(id) {
  tizen.bookmark.remove(ret[id]);
  show();
}

$(document).ready(function() {  
  tizen.bookmark.remove();
  bookmarkList = [];
  j = 0;
  show();
});
