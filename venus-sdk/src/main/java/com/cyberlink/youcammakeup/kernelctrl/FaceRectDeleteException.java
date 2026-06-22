package com.cyberlink.youcammakeup.kernelctrl;

/**
 * Minimal stub of the original class. The native engine (libperfect.so) FindClass'es
 * `com.cyberlink.youcammakeup.kernelctrl.FaceRectDeleteException` and throws it to signal
 * a face-rect error from GetMakeupImage, so the class MUST exist with this exact FQN and a
 * no-arg constructor. The original `logWtf()` (which used com.pf.common.utility.Log) is omitted
 * because it is app-side only and never invoked by native code.
 */
public final class FaceRectDeleteException extends Throwable {
}
