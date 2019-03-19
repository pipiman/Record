#pragma once

#include <opencv2/opencv.hpp>  
#include <vector>  
using namespace std;
using namespace cv;


class dcolorComb
{
public:

	dcolorComb(Mat &src,float rad,float h,float v); 
	~dcolorComb();

	void process(Mat &out);
	void makeMask();
	Mat T_adjust(Mat &sr);
	Mat C_adjust(Mat & inputL, Mat & inputR);

private:

	Mat src;

	static int *m_Data;

	int hei;
	int wid;
	int dhei;
	float rad;
	float hor_rate;
	float ver_rate;
};
