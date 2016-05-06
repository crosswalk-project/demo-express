## Introduction

DemoExpress is a central place to collect web feature samples that demonstrate use of W3C standard APIs, embedding APIs, and web runtime features. The following samples are published in DemoExpress.
* Embedding API (Java):
  * XwalkView Basic: `LayoutActivity`, `MultiInstanceActivity`, `OnHideOnShow`, `VersionAndAPIVersion`, `MultiSurfaceViews`, `MultiTextureViews`, `SetZOrderOnTop`, `ClearCache`, `ClearCacheForSingleFile`, `ZoomInAndOut`, `SaveState`, `SetLanguage`, `Transparent`, `SetInitialScale`, `EncodingDisplay`, `MultiInstanceOverlay`, `WindowSecure`
  * XwalkView Extened: `InputConnection`, `DispatchKeyEvent`, `DispatchDraw`, `OnTouchEvent`, `FocusChanged`, `ScrollChanged`, `SizeChanged`, `VisibilityChanged`, `WindowFocusChanged`, `ClearFormData`, `WindowsVisibilityChanged`, `DisableLongClick`, `.extended.BlankWindowForVisibilityTesting`, `LongClick`, `RequestFocus`, `NetworkAvailable`
  * XwalkUIClient & XwalkResourceClient: `ResourceAndUIClient`, `OnIconAvailableOnReceivedIcon`, `OnCreateWindowRequested`, `ShouldOverrideUrlLoading`, `Redirection`, `BlockAndErrorRedirection`, `OnReceivedLoadError`, `ClientOnRequestFocus`, `ConsoleLog`, `ReceivedTitle`, `ClientKeyEvent`, `ClientReceivedSSLError`, `OnUnhandledKeyEvent`, `OpenFileChooser`, `OnJavascriptModalDialog`, `OnJavascriptCloseWindow`
  * MISC: `Preferences`, `EchoExtension`, `DownloadListenerActivity`, `CookieManagerTest`, `AcceptFileSchemeCookies`
  `LoadMultiPages`, `XWalkViewHideAndShow`, `XwalkExtension`, `MultipleSurfaceViews`, `MultipleTextureViews`
* Web API & Feature:
  * Runtime & Packaging: `App URI`
  * Multimedia & Graphics: `Animation Transform`, `Audio Play`, `Canvas`, `CSS Animation`, `CSS Style`, `CSS Style Attribute`, `Flexible Box`, `Media Queries`, `SVG Transformation`, `Transitions`, `Video Play`, `WebRTC`
  * Networking & Storage: `FileReader`, `IndexedDB`, `Session History`, `Web Database`, `Web Messaging`, `Web Speech`, `Web Storage`
  * Performance & Optimization: `Animation Timing`,  `High Resolution Time`, `Navigation Timing`, `Performance Timeline`, `Resource Timing`, `Selectors`, `Typed Array`, `User Timing`, `Workers`
  * Device & Hardware: `Accelerometer`, `Browser State`, `Camera via UserMedia`, `Full Screen`, `Forms`, `HTML Template`, `Input`, `Notifications`, `Screen Orientation`, `Touch`, `WebAudio`, `WebGL`
  * Experimental: `Device Capabilities`, `Presentation`, `SIMD`, `FingerPrint`
  * Security: `Sandbox`
  * UI: `Clipboard`
  * Android specific:
    * Device & Hardware: `Battery Status`, `Camera`, `GamePad`, `Geolocation`, `Vibration`
    * Performance & Optimization: `Page Visibility`
    * Third Party Framework: `PDFjs`
    * Scheme: `SchemeContent`, `SchemesCheck`
    * Manifest: `ManifestDemo1`, `ManifestDemo2`, `ManifestDemo3`, `ManifestDemo4`, `ManifestDemo5`
    * Cordova: `CordovaInfo`, `CordovaAccelerometer`, `CordovaContacts`, `CordovaLazyLoadJS`, `CordovaNetwork`, `CordovaInAppBrowser`, `CordovaNotification`,`CordovaAdMob`

## Building
The DemoExpress would be built out to 3 Apps:
* webapi_demo: Web API Demo App, include webapi and web runtime feature samples.
* webapi_cordova_demo: Web API Cordova Demo App, include web api and web runtime feature samples which packed with Crosswalk-cordova, also include cordova specific features.
* embeddingapi_demo: Embedding API Demo App, include embedding api samples.

Here is the buiding steps:
* Pack the webapi_demo based on Crosswalk Android binary:
  * Download Crosswalk binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/).
  * Unzip Crosswalk binary:

    `$ mkdir -p /[userdir]/tools/crosswalk/`

    `$ unzip crosswalk-<version\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-<version\>\* crosswalk/`

  * The webapi_demo include the FingerPrint example by default, you need to get the FingerPrint extension from [fingerprint extension](https://github.com/crosswalk-project/crosswalk-android-extensions/releases)

    `$ unzip fingerprint.zip -d </path/to/samples/FingerPrint/fingerprint>`

    If you want to authenticate with fingerprint, you need an Android device with touch id sensor and shipped with Android 6.0+ system.

  * Goto DemoExpress folder to pack demoexpress package:

    `$ ./tools/pack.py -t apk -m shared|embedded -a x86|arm --tools='/[userdir]/tools/'`

* Pack cordova webapi_cordova_demo Crosswalk Android binary,
  * Create "cordova_plugins" directory in /[userdir]/tools/:

    `$ mkdir -p /[userdir]/tools/cordova_plugins`

  * Git clone cordova-plugin-crosswalk-webview from https://github.com/crosswalk-project/cordova-plugin-crosswalk-webview.git

    `$ cd /[userdir]/tools/cordova_plugins/`

    `$ git clone https://github.com/crosswalk-project/cordova-plugin-crosswalk-webview.git`

  * Create "extra_plugins" directory in /[userdir]/demo-express:

    `$ mkdir -p /[userdir]/demo-express/crosswalk/extra_plugins/`

  * Git clone cordova-admob-pro from https://github.com/floatinghotpot/cordova-admob-pro
    `$ cd /[userdir]/demo-express/crosswalk/extra_plugins/`

    `$ git clone https://github.com/floatinghotpot/cordova-admob-pro`

  * Rename cordova-admob-pro to cordova-admob:
    `$ mv cordova-admob-pro cordova-admob`

  * Configure the main-version(Crosswalk Version) and the crosswalk-branch(stable/beta) in the /[userdir]/demo-express/tools/VERSION file

  * Goto DemoExpress folder to Pack webapi_cordova_demo package:

    `$ ./tools/pack.py -t cordova -m shared|embedded -a x86|arm --tools='/[userdir]/tools/'`

* Pack embeddingapi_demo Crosswalk Android binary,
  * Ensure that you have [set up your host environment for Android development](https://crosswalk-project.org/documentation/getting_started/linux_host_setup.html).
  * Set up an Android target to deploy the application to, as described on the [Android target setup](https://crosswalk-project.org/documentation/getting_started/android_target_setup.html) page.
  * Ensure that you have installed the necessary [ADT components for your host](http://developer.android.com/tools/sdk/eclipse-adt.html).
  * Download the Crosswalk WebView binary from [Crosswalk release](https://download.01.org/crosswalk/releases/crosswalk/android/)
  * Unzip the Crosswalk WebView binary:

    `$ mkdir -p /[userdir]/tools/crosswalk-webview/`

    `$ unzip crosswalk-webview-<version\>-<arch\>.zip -d /[userdir]/tools/`

    `$ cd /[userdir]/tools/`

    `$ mv crosswalk-webview-<version\>\* crosswalk-webview/`

  * Go back to DemoExpress folder to create the embeddingapi_demo package:

    `$ ./tools/pack.py -t embeddingapi --tools='/[userdir]/tools/'`

* The pack.py script calls the pack command from Android (for Crosswalk-Cordova apps and regular Crosswalk apps)
 * [Run Crosswalk App on Andriod](https://crosswalk-project.org/documentation/getting_started/run_on_android.html)
 * [Develop Crosswalk Cordova App] (https://crosswalk-project.org/documentation/cordova/develop_an_application.html)
 * [Embedding Crosswalk] (https://crosswalk-project.org/documentation/embedding_crosswalk.html)

## Execution
* If you build a package using the shared Crosswalk runtime (`--mode=shared` build option), you must ensure that the Crosswalk runtime library is installed on the target device before running DemoExpress. The library can be found at [https://download.01.org/crosswalk/releases/crosswalk/android/canary/&lt;version&gt;/&lt;arch&gt;/crosswalk-apks-&lt;version&gt;-&lt;arch&gt;.zip](https://download.01.org/crosswalk/releases/crosswalk/android/)
* Install and run DemoExpress on the target device

## Customization

* File 'tests.xml' is provided to customize the samples in DemoExpress. Feel free to disable/enable the samples before packing the package.

* Two sample lists [tests.android.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.android.xml), [tests.embedding.xml](https://github.com/crosswalk-project/demo-express/blob/master/tests.embedding.xml) are released for Android. The Cordova samples are disabled in `tests.android.xml` by default.

## License

Except as noted in `COPYING` and/or `NOTICE` files, or in files with a license in the file header, test suite source code uses a BSD-3-Clause license described in the
`LICENSE` file.
