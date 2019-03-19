#include <jni.h>
#include <string>
#include "dcolorComb.h"
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_divid_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_divid_MainActivity_FindFeatures(JNIEnv *env, jobject instance, jlong matAddrGr,
                                                 jlong matAddrRgba) {
    Mat& mGr  = *(Mat*)matAddrGr;
    Mat& mRgb = *(Mat*)matAddrRgba;
    Canny(mGr, mGr, 50, 250);
    vector<KeyPoint> v;

    Ptr<FeatureDetector> detector = FastFeatureDetector::create(50);
    detector->detect(mGr, v);
    for( unsigned int i = 0; i < v.size(); i++ )
    {
        const KeyPoint& kp = v[i];
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, Scalar(255,0,0,255));

    }



}extern "C"
JNIEXPORT void JNICALL
Java_com_example_divid_MainActivity_process(JNIEnv *env, jobject instance, jlong src, jlong out) {

    // TODO
    Mat &msrc = *(Mat*)src;
    Mat &mout= *(Mat*)out;

    int wid = msrc.size().width;
    int hei = msrc.size().height;

    Mat imgL = Mat(msrc, Rect(0, 0, wid / 2, hei));
    Mat imgR = Mat(msrc, Rect(wid / 2, 0, wid / 2, hei));

    transpose(imgL, imgL);
    flip(imgL, imgL, 1);
    transpose(imgR, imgR);
    flip(imgR, imgR, 0);


   // dcolorComb dc(msrc,5,0.02,0.08);
    //dc.process(mout);
}