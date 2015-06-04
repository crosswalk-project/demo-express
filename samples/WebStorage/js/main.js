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
       Yao,Yi <yix.yao@intel.com>

*/

Array.prototype.indexof = function(key){
  for(var i=0; i<this.length; i++){
    if(this[i] == key){
      return i;
    }
  }
  return -1;
}

Array.prototype.remove = function(key){
  var index = this.indexof(key);
  if( index !== -1){
    this.splice(index,1)
  }
}

Array.prototype.push = function(key){
  var index = this.indexof(key);
  if(index == -1){
    this[this.length] = key;
  }
}

localKeyArray = new Array();
sessionKeyArray = new Array();

function status(){
  $("#tableList").empty();
  var table = document.querySelector("table");
  table.insertRow(0)
  table.rows[0].insertCell(0).innerHTML = "Storage Type";
  table.rows[0].insertCell(1).innerHTML = "Key";
  table.rows[0].insertCell(2).innerHTML = "Value";

  var storages = ["localStorage", "sessionStorage"];
  var arrays = ["localKeyArray", "sessionKeyArray"];
  for(var i=0; i<storages.length; i++){
    var storage = window[storages[i]];
    var keyArray = window[arrays[i]];
    for(var j=0; j<keyArray.length; j++){
      var len = table.rows.length;
      var key = keyArray[j];
      var value = storage.getItem(key);
      if(value !== null){
        table.insertRow(len);
        table.rows[len].insertCell(0).innerHTML = storages[i];
        table.rows[len].insertCell(1).innerHTML = key;
        table.rows[len].insertCell(2).innerHTML = value;
      }
    }
  }
}

function onAdd(){
  var session = $("#session").val();
  var local = $("#local").val();

  if (session !== "") {
    sessionStorage.setItem(session, session);
    sessionKeyArray.push(session);
  }

  if (local !== "") {
    localStorage.setItem(local, local);
    localKeyArray.push(local);
  }

  status();
  $("#status").text('Data Stored');
}

function onRemove(){
  var session = $("#session").val();
  var local = $("#local").val();

  if (session !== "") {
    sessionStorage.removeItem(session);
    sessionKeyArray.remove(session);
  }

  if (local !== "") {
    localStorage.removeItem(local);
    localKeyArray.remove(local);
  }

  status();
  $("#status").text('Left Data');
}

$(document).ready(function () {
    $("#add").click(onAdd);
    $("#remove").click(onRemove);
});
