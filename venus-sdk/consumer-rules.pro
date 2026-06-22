# ── CyberLink Venus/Perfect native engine — JNI binding is by EXACT name ──────────────
# Native code resolves symbols Java_com_cyberlink_youcammakeup_jniproxy_UIVenusJNI_* and
# FindClass/GetFieldID by FQN + field name. Renaming/stripping = UnsatisfiedLinkError / SIGSEGV.
# These rules ship with the SDK so any consuming app applies them automatically under R8.

# JNI proxy (SWIG) — package + class + method names are the native symbol contract.
-keep class com.cyberlink.youcammakeup.jniproxy.** { *; }

# Struct/enum carriers — native FindClass + GetFieldID by FQN + field m_*.
-keep class com.cyberlink.clgpuimage.** { *; }
-keepclassmembers class com.cyberlink.clgpuimage.** { *; }

# Native throws this; native FindClass on camera carriers.
-keep class com.cyberlink.youcammakeup.kernelctrl.FaceRectDeleteException { *; }
-keep class com.pf.makeupcam.camera.** { *; }

# Loader + stub annotations/interfaces referenced by the engine classes' static init.
-keep class ev.** { *; }
-keep class ya.** { *; }
-keep class proguard.annotation.** { *; }

# All native methods (belt-and-suspenders).
-keepclasseswithmembernames class * {
    native <methods>;
}
