I have been trying to get the subprojects to build (so you don't have to first build an aar), but I haven't got it working yet.  The changes needed to do this start with:

$ git diff
diff --git a/library/build.gradle b/library/build.gradle
index 0b6e5ef..2e060d6 100644
--- a/library/build.gradle
+++ b/library/build.gradle
@@ -205,6 +205,6 @@ configurations {
     androidTestRuntime.extendsFrom androidTestCompile
 }
 
-test.mustRunAfter "::compileTestDebugJava"
+//test.mustRunAfter "::compileTestDebugJava"
 distribution.mustRunAfter ":proximitykit-android:distribution"
 build.mustRunAfter clean
diff --git a/library/proximitykit-android b/library/proximitykit-android
--- a/library/proximitykit-android
+++ b/library/proximitykit-android
@@ -1 +1 @@
-Subproject commit b1671430075ce20d595e180205f581f381c1c8af
+Subproject commit b1671430075ce20d595e180205f581f381c1c8af-dirty
diff --git a/reference-app/app/build.gradle b/reference-app/app/build.gradle
index 34a9b74..11220bc 100644
--- a/reference-app/app/build.gradle
+++ b/reference-app/app/build.gradle
@@ -41,5 +41,5 @@ allprojects {
 dependencies {
     // the support library is already bundled.  it probably should not be
     compile 'com.android.support:support-v4:21.0.2'
-    compile 'com.radiusnetworks:navigationkit-android-release:@aar'
+    //compile 'com.radiusnetworks:navigationkit-android-release:@aar'
 }
diff --git a/reference-app/settings.gradle b/reference-app/settings.gradle
index 916a4b8..1283feb 100644
--- a/reference-app/settings.gradle
+++ b/reference-app/settings.gradle
@@ -1,4 +1,4 @@
 include ':app'
-//include ':navigationkit-android'
-//include ':proximitykit-android'
-//include ':android-beacon-library'
+include ':navigationkit-android'
+include ':proximitykit-android'
+include ':android-beacon-library'
