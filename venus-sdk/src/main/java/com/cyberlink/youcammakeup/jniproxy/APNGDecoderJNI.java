package com.cyberlink.youcammakeup.jniproxy;

/* loaded from: classes2.dex */
public class APNGDecoderJNI {
    public static final native boolean APNGDecoderWrapper_EndDecode(long j11, APNGDecoderWrapper aPNGDecoderWrapper);

    public static final native boolean APNGDecoderWrapper_GetNextFrame(long j11, APNGDecoderWrapper aPNGDecoderWrapper, Object obj, int i11, long j12, FrameInfo frameInfo);

    public static final native boolean APNGDecoderWrapper_InitializedAPNGHeader(long j11, APNGDecoderWrapper aPNGDecoderWrapper, byte[] bArr, long j12, HeaderInfo headerInfo);

    public static final native short FrameInfo_delay_den_get(long j11, FrameInfo frameInfo);

    public static final native short FrameInfo_delay_num_get(long j11, FrameInfo frameInfo);

    public static final native int HeaderInfo_height_get(long j11, HeaderInfo headerInfo);

    public static final native int HeaderInfo_image_count_get(long j11, HeaderInfo headerInfo);

    public static final native int HeaderInfo_width_get(long j11, HeaderInfo headerInfo);

    public static final native void delete_APNGDecoderWrapper(long j11);

    public static final native void delete_FrameInfo(long j11);

    public static final native void delete_HeaderInfo(long j11);

    public static final native long new_APNGDecoderWrapper__SWIG_0();

    public static final native long new_FrameInfo__SWIG_0();

    public static final native long new_HeaderInfo__SWIG_0();
}
