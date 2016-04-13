/*
Copyright (c) 2013 Intel Corporation.

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
        Xin, liu <xinx.liu@intel.com>
        Wang, Chunyan<chunyanx.wang@intel.com>

*/

var connection = null;
var presUrl = "contents.html";
var presId;
var request = null;
var data = "hello";

var btnPlay;
var btnClose;
var btnJoin;
var availabilityStatus;
var sessionState;
var presentationId;

window.onload = function() {
  init();
  request = new PresentationRequest(presUrl);
  //monitor the list of available presentation displays
  request.getAvailability().then(function(availability) {
    updateAvailabilityStatus(availability.value);
    availability.onchange = function() {
      updateAvailabilityStatus(this.value);
    };
  }, function(error) {
    txtMsg.textContent = error.name;
  });
}

function init() {
  btnPlay = document.getElementById("btnPlay");
  btnClose = document.getElementById("btnClose");
  btnJoin = document.getElementById("btnJoin");
  txtMsg = document.getElementById("log");
  availabilityStatus = document.getElementById("availabilityStatus");
  sessionState = document.getElementById("sessionState");
  presentationId = document.getElementById("presentationId");
  btnPlay.disabled = true;
  btnClose.disabled = true;
  btnJoin.disabled = true;
  txtMsg.innerHTML = "";
}

function onClickStart() {
  btnJoin.disabled = true;
  request.start().then(function(conn) {
    onConnectionStart(conn);
  }, function(error) {
    txtMsg.textContent = "Session Start error: " + error.message;
  });
}

function onClickReconnect() {
  request.reconnect(presId).then(function(conn) {
    onConnectionStart(conn);
  }, function(error) {
    txtMsg.textContent = "reconnect presentation get error:" + error.message;
	});
}

function onClickClose() {
  if(connection != null) {
    presId = connection.id;
    connection.terminate(); 
    //btnJoin.disabled = false;
  }
}

function onConnectionStart(conn) {
  txtMsg.textContent = "";
  connection = conn;
  updateStatus();
  connection.onstatechange = function() {
    updateStatus();
  };
  connection.onmessage = function(evt) {
    // receive message from receiver device
  }
  connection.send(data);
}

function updateStatus() {
  var isConnected = connection !=null &&
                      connection.state == "connected";
  btnClose.disabled = !isConnected;
  if(connection) {
      sessionState.innerHTML = connection.state;
      presentationId.innerHTML = connection.id;
  }
  if(!isConnected) {
      presentationId.innerHTML = "none";
  }
}

function updateAvailabilityStatus (value) {
  btnPlay.disabled = !value;
  availabilityStatus.innerHTML = value ? "available" : "unavailable";
}
