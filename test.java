ackage com.kmust.ocr.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.kmust.ocr.Fragments.RecogFragment;
import com.kmust.ocr.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ImageUpload {

    public static File qualityCompress(File file) {
        // 0-100 100为不压缩

        Bitmap bmp = BitmapFactory.decodeFile(file.getPath());
        int quality = 20;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中

        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        Bitmap b = BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.size());




        try {

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static final String TAG = "UPload";

    // 0 : 中文  1 : 缅甸 2 : 泰语  3 : Vei
    private static String URL;
    private static RecogFragment show;
    private static Handler handler=new Handler();
    private static Thread th = null;

    private static boolean con;

    private static String[] Res;

    private static String src, tgt, URL;    

    private static int length;
    
    public static void setContext(String src, String tgt, String[] Res) throws InstantiationException, IllegalAccessException {
	ClassName.length = Res.length(); 
 	// set data
	
	String[] ressa;
	ClassName.run(0, ressa);
    
    }

    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    private static final OkHttpClient client = new OkHttpClient.Builder().readTimeout(90, TimeUnit.SECONDS).build();

    public static void run(final int index,  String[] AfterTrans) throws Exception {
        final File file=f;
	if(index < ClassName.length)
	    {
		String now = ClassName.Res[index];
		String URL =  ;
		        th = new Thread() {
            @Override
            public void run() {
                //子线程需要做的工作
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", UUID.randomUUID().toString()+".jpg",
                                RequestBody.create(MEDIA_TYPE_JPG, file))
                        .addFormDataPart("lang",lang)
                        .addFormDataPart("type","1")
                        .build();
                //设置为自己的ip地址
                Request request = new Request.Builder()
                        .url(URL)
                        .post(requestBody)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        Log.i(TAG, response.toString());
                                             throw new IOException("Unexpected code " + response);
                    }
                    else {
                        final String r = response.body().string();
                        System.out.print(index);
			//	AfterTrans.set(r);
			ClassName.run(index + 1, AfterTrans);
                    }

                } catch (IOException e) {
                     int a = 0;
                    e.printStackTrace();
		    //Net problem;
                }
            }
        };
        th.start(); 

	    }
	else
	    {
		return ;
	    }
    }
}
