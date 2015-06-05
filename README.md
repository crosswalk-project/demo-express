## Introduction

DemoExpress is a central place to collect web feature samples that demonstrate use of W3C standard APIs, Tizen Extension APIs, embedding APIs, and web runtime features. The following samples are published in DemoExpress.
* Embedding API (Java): `XwalkView`, `LoadMultiPages`, `XWalkViewHideAndShow`, `XwalkExtension`, `MultipleSurfaceViews`, `MultipleTextureViews`
* Web API & Feature:
  * Runtime & Packaging: `App URI`
  * Multimedia & Graphics: `Animation Transform`, `Audio Play`, `Canvas`, `CSS Animation`, `CSS Style`, `CSS Style Attribute`, `Flexible Box`, `Media Queries`, `SVG Transformation`, `Transitions`,  `Video Play`, `WebRTC`
  * Networking & Storage: `FileReader`, `IndexedDB`, `Session History`, `Web Database`, `Web Messaging`, `Web Speech`, `Web Storage`
  * Performance & Optimization: `Animation Timing`,  `High Resolution Time`, `Navigation Timing`, `Performance Timeline`, `Resource Timing`, `Selectors`, `Typed Array`, `User Timing`, `Workers`
  * Device & Hardware: `Accelerometer`, `Browser State`, `Camera via UserMedia`, `Full Screen`, `Forms`, `HTML Template`, `Input`, `Notifications`, `Screen Orientation`, `ShadowDom`, `Touch`, `WebAudio`, `WebGL`
  * Social: `Contacts Manager`
  * Experimental: `Device Capabilities`, `Presentation`, `SIMD`
  * Security: `Sandbox`
  * UI: `Clipboard`
  * Android specific Web API & Features:
    * Device & Hardware: `Battery Status`, `Camera`, `GamePad`, `Location GPS`, `Vibration`
    * Performance & Optimization: `Page Visibility` 
    * Third Party Framework: `PDFjs`
    * Scheme: `SchemeContent`, `SchemesCheck`
    * Manifest: `ManifestDemo1`, `ManifestDemo2`, `ManifestDemo3`, `ManifestDemo4`, `ManifestDemo5`
    * Cordova: `CordovaInfo`, `CordovaAccelerometer`, `CordovaContacts`, `CordovaLazyLoadJS`, `CordovaNetwork`
  * Tizen Specific Web API & Features: 
    * Performance & Optimization: `ViewPort`
    * Tizen Extension APIs: `ApplicationManager`, `AudioSystem`, `Bookmark`, `Content`, `Download`, `Tizen Filesystem`, `MessagePort`, `NBS`, `SystemInfo`, `SystemSetting IncomingCall`, `SystemSetting Screen`
    * Other APIs: `Media Renderer`, `Media Server`, `NFC`, `Drag and Drop`

## Building
The DemoExpress would be built out to 3 Apps:  
* DemoEx: DemoExpress App, include webapi and web runtime feature samples. 
* DemoEx-Co: DemoExpress Cordova version App, include web api and web runtime feature samples which packed with Crosswalk-cordova, also include cordova specific features. 
* DemoEx-Em: DemoExpress Embedding App, include embedding api samples.

Here is the buiding steps:
* Pack the DemoExpress based on Crosswalk Android binary:
  * Download Crosswalk binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/).
  * Unzip Crosswalk binary:

    `$ mkdir -p /[userdir]/tools/crosswalk/`

    `$ unzip crosswalk-<version\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-<version\>\* crosswalk/`

  * Goto DemoExpress folder to pack DemoExpress package:

    `$ ./pack.py -t apk -m shared|embedded -a x86|arm --tools='/[userdir]/tools/'`

* Pack DemoExpress-Cordova Crosswalk Android binary, 
  * Download Crosswalk binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/).
  * Unzip Crosswalk binary:

    `$ mkdir -p /[userdir]/tools/crosswalk/`

    `$ unzip crosswalk-<version\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-<version\>\* crosswalk/`

  * Download Crosswalk Cordova binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/)
  * unzip Crosswalk Cordova binary:

    `$ mkdir -p /[userdir]/tools/cordova/`

    `$ unzip crosswalk-cordova-<version\>-<arch\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-cordova-<version\>\* cordova/`

  * Goto DemoExpress folder to Pack DemoExpress-Cordova package:

    `$ ./pack.py -t cordova --tools='/[userdir]/tools/'`
     
* Pack DemoExpress-Embedding Crosswalk Android binary,
  * Ensure that you have [set up your host environment for Android development](https://crosswalk-project.org/documentation/getting_started/linux_host_setup.html).
  * Set up an Android target to deploy the application to, as described on the [Android target setup](https://crosswalk-project.org/documentation/getting_started/android_target_setup.html) page.
  * Ensure that you have installed the necessary [ADT components for your host](http://developer.android.com/tools/sdk/eclipse-adt.html).
  * Download Crosswalk binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/).
  * Unzip Crosswalk binary:

    `$ mkdir -p /[userdir]/tools/crosswalk/`

    `$ unzip crosswalk-<version\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-<version\>\* crosswalk/`

  * Download Crosswalk-webview binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/)
  * unzip Crosswalk-webview binary:

    `$ mkdir -p /[userdir]/tools/crosswalk-webview/`

    `$ unzip crosswalk-webview-<version\>-<arch\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-webview-<version\>\* Crosswalk-webview/`

  * Goto DemoExpress folder to Pack DemoExpress-Embedding package:

    `$ ./pack.py -t embeddingapi --tools='/[userdir]/tools/'`
   
* The pack.py script calls the pack command from Android (for Crosswalk-Cordova apps and regular Crosswalk apps)
 * [Run Crosswalk App on Andriod](https://crosswalk-project.org/documentation/getting_started/run_on_android.html)
 * [Develop Crosswalk Cordova App] (https://crosswalk-project.org/documentation/cordova/develop_an_application.html)
 * [Embedding Crosswalk] (https://crosswalk-project.org/documentation/embedding_crosswalk.html)

## Execution
* If you build a package using the shared Crosswalk runtime (`--mode=shared` build option), you must ensure that the Crosswalk runtime library is installed on the target device before running DemoExpress. The library can be found at [https://download.01.org/crosswalk/releases/crosswalk/android/canary/&lt;version&gt;/&lt;arch&gt;/crosswalk-apks-&lt;version&gt;-&lt;arch&gt;.zip](https://download.01.org/crosswalk/releases/crosswalk/android/)
* Install and run DemoExpress on the target device

## Customization

* File 'tests.xml' is provided to customize the samples in DemoExpress. Feel free to disable/enable the samples before packing the package.
    
* Three sample lists [tests.tizen.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.tizen.xml) and [tests.android.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.android.xml), [tests.embedding.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.embedding.xml) are released for Tizen and Android. The Cordova samples are disabled in `tests.android.xml` by default.

## License

Except as noted in `COPYING` and/or `NOTICE` files, or in files with a license in the file header, test suite source code uses a BSD-3-Clause license described in the
`LICENSE` file.
