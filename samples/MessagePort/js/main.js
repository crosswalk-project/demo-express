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

var localMsgPort;
var remoteMsgPort;
var showId;

function show() {
  $("#test").html("Fail to send message!");
  clearTimeout(showId);
}

function send() {
  clearTimeout(showId);
  showId = setTimeout("show()", 5000);
  localMsgPort = tizen.messageport.requestLocalMessagePort('MessagePortA');
  var app = tizen.application.getCurrentApplication();
  remoteMsgPort = tizen.messageport.requestRemoteMessagePort(app.appInfo.id, 'MessagePortB');
  localMsgPort.addMessagePortListener(function(items, remoteport) {
    if(remoteport !== null) {
      clearTimeout(showId);
      $("#test").html("Success to send message!");
      $("#pc1_input").attr("value", "");
    }
  });
  remoteMsgPort.sendMessage([
    {key : 'SEND', value : $("#pc1_input").val()}
  ], localMsgPort);
}

$(document).ready(function() {
  $("#pc1_send").click(send);
  $("#test").html("");
});
