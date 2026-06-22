package proguard.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Stub of ProGuard's @Keep annotation so the decompiled jniproxy classes compile.
 * No @Target on purpose so it is applicable to any declaration context used in the dump.
 */
@Retention(RetentionPolicy.CLASS)
public @interface Keep {
}
