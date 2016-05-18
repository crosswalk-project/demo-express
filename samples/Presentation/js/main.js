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
var request = null;
var data = "hello";

var btnPlay;
var btnClose;
var btnJoin;
var btnTerminate;
var availabilityStatus;
var sessionState;
var presentationId;

window.onload = function() {
  init();
  request = new PresentationRequest(presUrl);
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
  btnTerminate = document.getElementById("btnTerminate");
  txtMsg = document.getElementById("log");
  availabilityStatus = document.getElementById("availabilityStatus");
  sessionState = document.getElementById("sessionState");
  presentationId = document.getElementById("presentationId");
  btnPlay.disabled = true;
  btnClose.disabled = true;
  btnJoin.disabled = true;
  btnTerminate.disabled = true;
  txtMsg.textContent = "";
}

function onClickStart() {
  onClickClose();
  request = new PresentationRequest(presUrl);
  request.start().then(function(conn) {
    onConnectionStart(conn);
  }, function(error) {
    txtMsg.textContent = "Session Start error: " + error.message;
  });
}

function onClickReconnect() {
  var presId = localStorage.getItem("presId");
  request.reconnect(presId).then(function(conn) {
    onConnectionStart(conn);
  }, function(error) {
    txtMsg.textContent = "reconnect presentation get error:" + error.message;
  });
}

function onClickClose() {
  if(connection != null) {
    connection.close();
  }
}

function onClickTerminate() {
  if(connection != null) {
    connection.terminate();
  }
}

function onConnectionStart(conn) {
  txtMsg.textContent = "";
  connection = conn;
  localStorage.setItem("presId", connection.id);
  updateStatus();
  connection.onconnect = function() {
    btnClose.disabled = false;
    btnTerminate.disabled = false;
    btnJoin.disabled = true;
    updateStatus();
    connection.onmessage = function(evt) {
    }
    connection.send(data);
  }
  connection.onclose = function() {
    updateStatus();
    reset();
  };
  connection.onterminate = function () {
    localStorage.removeItem("presId");
    updateStatus();
    reset();
  };
}

function reset() {
  connection = null;
  btnClose.disabled = true;
  btnTerminate.disabled = true;
  //btnJoin.disabled = !localStorage.getItem("presId");
};

function updateStatus() {
  var isConnected = connection != null &&
                      connection.state == "connected";
  btnClose.disabled = !isConnected;
  btnTerminate.disabled = !isConnected;
  //btnJoin.disabled = isConnected;
  if(connection) {
    sessionState.textContent = connection.state;
    presentationId.textContent = connection.id;
  }
  if(!isConnected) {
    presentationId.textContent = "none";
  }
}

function updateAvailabilityStatus (value) {
  btnPlay.disabled = !value;
  availabilityStatus.textContent = value ? "available" : "unavailable";
}

window.onbeforeunload = onClickTerminate;
