package com.cyberlink.youcammakeup.jniproxy.faceshape;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
/* loaded from: classes2.dex */
public final class Eyebrow {
    public String shortness;
    public String thinness;
    public ClassificationResult shape = new ClassificationResult();
    public ClassificationResult bodyThickness = new ClassificationResult();
    public ClassificationResult tailThickness = new ClassificationResult();
}
