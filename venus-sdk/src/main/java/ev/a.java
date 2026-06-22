package ev;

/**
 * Minimal replacement for the original CyberLink native loader (ev.a).
 *
 * The decompiled jniproxy classes (UIVenusJNI, CommonJNI, ...) call {@code ev.a.a()} or
 * {@code ev.a.b()} in their static initializers to load the native engine. The original
 * implementation went through a ReLinker chain ({@code ev.b}/{@code ev.c}/{@code ej.*}) and a
 * lazy holder whose construction performed the load — so BOTH {@code a()} and {@code b()}
 * triggered loading. We reproduce that: both entry points run a single load attempt.
 *
 * "perfect" (libperfect.so) exports the JNI symbols
 * {@code Java_com_cyberlink_youcammakeup_jniproxy_UIVenusJNI_*}. The Android dynamic linker
 * auto-resolves each library's DT_NEEDED from jniLibs, so strict ordering is not required;
 * leaf deps are listed first as belt-and-suspenders.
 */
public final class a {

    private static final String TAG = "VenusNativeLoader";

    /** Libraries loaded best-effort before the JNI library. */
    private static final String[] DEPS = {
            "c++_shared",
            "tensorflowlite_pf",
            "PFMNN",
            "PFMNN_Express",
            "athena",
            "venus",
            "cliofxsdk",
    };

    private static boolean attempted = false;
    private static boolean loaded = false;

    private a() {
    }

    /** Mirrors the original: throws if the native engine could not be loaded. */
    public static synchronized void a() {
        ensureLoaded();
        if (!loaded) {
            throw new UnsatisfiedLinkError("Could not load the native engine libraries.");
        }
    }

    /** Mirrors the original: returns whether the native engine is loaded (also triggers loading). */
    public static synchronized boolean b() {
        ensureLoaded();
        return loaded;
    }

    private static void ensureLoaded() {
        if (attempted) {
            return;
        }
        attempted = true;
        for (String lib : DEPS) {
            try {
                System.loadLibrary(lib);
            } catch (Throwable t) {
                // Non-fatal: pulled transitively via DT_NEEDED, or optional.
                System.err.println(TAG + ": optional native lib not loaded: lib" + lib + ".so (" + t.getMessage() + ")");
            }
        }
        try {
            System.loadLibrary("perfect"); // carries the UIVenusJNI symbols
            loaded = true;
            System.out.println(TAG + ": native engine libraries loaded.");
        } catch (Throwable t) {
            System.err.println(TAG + ": failed to load libperfect.so: " + t);
        }
    }
}
