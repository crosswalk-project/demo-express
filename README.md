## Introduction

DemoExpress is a centralized place to collect web feature samples, including W3C standard APIs, Tizen Extension APIs, embedded APIs, web runtime features. Following samples are published in DemoExpress([t] = Tizen only; [a] = Android only).

* Runtime & Packaging: `App URI`
* Multimedia & Graphics: `Animation Transform`, `Audio Play`, `Canvas`, `CSS Animation`, `CSS Style`, `CSS Style Attribute`, `Flexible Box`, `Media Queries`, `SVG Transformation`, `Transitions`, `[t]Video Play`, `[a]Video Play`, `WebRTC`
* Networking & Storage: `FileReader`, `IndexedDB`, `Session History`, `Web Database`, `Web Messaging`, `Web Speech`, `Web Storage`
* Performance & Optimization: `Animation Timing`, `[a]High Resolution Time`, `[t]High Resolution Time`, `Navigation Timing`, `[a]Page Visibility`, `Performance Timeline`, `Resource Timing`, `Selectors`, `Typed Array`, `User Timing`, `[t]Viewport`, `Workers`
* Device & Hardware: `Accelerometer`, `[a]Battery Status`, `Browser State`, `[a]Camera`, `Camera via UserMedia`, `Full Screen`, `Forms`, `[a]GamePad`, `HTML Template`, `Input`, `[a]Location GPS`, `Notifications`, `Screen Orientation`, `ShadowDom`, `Touch`, `[a]Vibration`, `WebAudio`, `WebGL`
* Socail: `Contacts Manager`
* Experimental: `Device Capabilities`, `[a]Presentation`, `SIMD`
* Security: `Sandbox`
* UI: `Clipboard`, `[t]Drag and Drop`
* Tizen Extension APIs: `[t]ApplicationManager`, `[t]AudioSystem`, `[t]Bookmark`, `[t]Content`, `[t]Download`, `[t]Tizen Filesystem`, `[t]MessagePort`, `[t]NBS`, `[t]SystemInfo`, `[t]SystemSetting IncomingCall`, `[t]SystemSetting Screen`
* Third Party Framework: `[a]PDFjs`
* Scheme: `[a]SchemeContent`, `[a]SchemesCheck`
* Manifest: `[a]ManifestDemo1`, `[a]ManifestDemo2`, `[a]ManifestDemo3`, `[a]ManifestDemo4`, `[a]ManifestDemo5`
* Cordova: `[a]CordovaInfo`, `[a]CordovaAccelerometer`, `[a]CordovaContacts`, `[a]CordovaLazyLoadJS`, `[a]CordovaNetwork`
* Others APIs: `[t]Media Renderer`, `[t]Media Server`, `[t]NFC`


## Building
* Pack the DemoExpress based on Crosswalk Android binary:
  * Download Crosswalk binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/).
  * Unzip Crosswalk binary:
   
    `$ mkdir -p /[userdir]/tools/crosswalk/`

    `$ unzip crosswalk-<version\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-<version\>\* crosswalk/`

    For packing DemoExpress Crosswalk Cordova package, also need to unzip Crosswalk Cordova binary:

    `$ mkdir -p /[userdir]/tools/cordova/`

    `$ unzip crosswalk-cordova-<version\>-<arch\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-cordova-<version\>\* cordova/`

  * Goto DemoExpress folder to pack Android version:

    `$ ./pack.py -t apk -m shared|embedded -a x86|arm --tools='/[userdir]/tools/'`
    
    Pack DemoExpress Android Cordova version:

    `$ ./pack.py -t cordova --tools='/[userdir]/tools/'`
   
* pack.py script combine the pack command for Android, Android cordova version, the original pack methods could be checked in the Crosswalk website
 * [Run Crosswalk App on Andriod](https://crosswalk-project.org/documentation/getting_started/run_on_android.html)
 * [Develop Crosswalk Cordova App] (https://crosswalk-project.org/documentation/cordova/develop_an_application.html)

## EXECUTION
* If shared mode package, Install Crosswalk Runtime lib on target device before running DemoExpress, the lib in [https://download.01.org/crosswalk/releases/crosswalk/android/canary/&lt;version&gt;/&lt;arch&gt;/crosswalk-apks-&lt;version&gt;-&lt;arch&gt;.zip](https://download.01.org/crosswalk/releases/crosswalk/android/)
* Install and Run DemoExpress on target device

## Customization

* File 'tests.xml' is provided to customize the samples in DemoExpress. Feel free to disable/enable the samples before packing the package.
    
* Two sample lists [tests.tizen.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.tizen.xml) and [tests.android.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.android.xml) are released for Tizen and Android. The Cordova samples are disabled in `tests.android.xml` by default.

## LICENSE

Except as noted in `COPYING` and/or `NOTICE` files, or as headed with license
info, test suite source code uses a BSD-3-Clause license that can be found in the
`LICENSE` file.
