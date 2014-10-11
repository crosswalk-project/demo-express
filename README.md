## Introduction

DemoExpress demostrate how to use Web API in Crosswalk application and development based on JQuery framework, including:
* `Runtime & Packaging`: App URI
* `Multimedia & Graphics`:
  * CSS3 APIs
  * Graphics related APIs: Cavas, SVG
  * Muti-Media related APIs: HTML Audio/Video, Media Query, Web RTC
* `Networking & Storage`:
  * Networking related APIs: Web Messaging, Web Speech
  * Storage related APIs: Web Storage, Index DB, Web SQL, Session History
  * File related APIs: File API, File Directory & System, File Writer
* `Performance & Optimization`: Navigation Timing, PageVisibility, Resource Timing, Animation Timing, Typed Arrary, Workers, ViewPort...
* `Device & Hardware`: Screen Orientation, Device Orientation, Browser Status, Gamepad, WebGL, LocationGPS, Media Capture, Touch, Vibration, Web Notificaiton...
* `Socail` : Contacts
* `Experimental` : Device Capability, Presentation, SIMD
* `Security` : CSP, Sandbox
* `UI` : Clipboard, Drag&drop
* `Others` : DLNA Media Server, DLNA Media Renderer, NFC, Cordova Mobile Spec

More information about API support in Crosswalk, see https://crosswalk-project.org/#documentation/apis/web_apis

## Prerequisite
*   Python >=2.7
*   Following the instructions to set up the Crosswalk build enviornment for tizen or android at https://crosswalk-project.org/#documentation/getting_started
*   Install Crosswalk Runtime lib in android device, the lib in [https://download.01.org/crosswalk/releases/crosswalk/android/canary/&lt;version&gt;/&lt;arch&gt;/crosswalk-apks-&lt;version&gt;-&lt;arch&gt;.zip](https://download.01.org/crosswalk/releases/crosswalk/android/canary/)
*   Set up the DLNA server (e.g. dleyna server) before running DLNA samples(`media server`, `media renderer`), details at https://github.com/01org/cloud-dleyna/wiki.

## Building
* Run pack.py to pack DemoExpress package, e.g.:

    ./pack.py -t apk -m shared -a x86 --tools=$PATH_TO_TOOLS

* Check full options of `pack.py` by `--help` option.
* You are ready to install and run DemoExpress(in zip package) on a target device.
* If need test Cordova Mobile Spec, remove the comment of CordovaMobileSpec in `tests.android.xml`, then re-build the app.

## Organization
* Organize and filter the samples with tests.xml. 
* 2 samples are available:
 * `tests.tizen.xml` filter the APIs and features supported on Tizen platform.
 * `tests.android.xml` fliter the APIs and features supported on Android platform.
* Update tests.xml to make user customized samples filter.


## LICENSE

Except as noted in `COPYING` and/or `NOTICE` files, or as headed with license
info, test suite source code uses a BSD-3-Clause license that can be found in the
`LICENSE` file.


