package com.cyberlink.youcammakeup.jniproxy.faceshape;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
/* loaded from: classes2.dex */
public final class FacePredictResult {
    public EyePredictResult eye = new EyePredictResult();
    public EyebrowPredictResult eyebrow = new EyebrowPredictResult();
    public FaceShapePredictResult faceShape = new FaceShapePredictResult();
    public CheekbonePredictResult cheekbone = new CheekbonePredictResult();
    public LipShapePredictResult lipShape = new LipShapePredictResult();
    public NoseFeaturePredictResult noseFeature = new NoseFeaturePredictResult();
    public LipColorPredictResult lipColor = new LipColorPredictResult();
    public EyeColorPredictResult eyeColor = new EyeColorPredictResult();
    public AgeGenderPredictResult ageGender = new AgeGenderPredictResult();
    public HairColorPredictResult hairColor = new HairColorPredictResult();
}
