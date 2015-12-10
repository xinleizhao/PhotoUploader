package hk.hku.cs.photouploader;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

public class CamActivity extends AppCompatActivity {

    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    private static final String TAG = CamActivity.class.getSimpleName();

    private static final int CAMERA_PIC_REQUEST = 1111;
    private ImageView mImage;

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + timeStamp + "image.jpg");
        System.out.println("timeStamp on galleryAddPic = " + timeStamp);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cam);

        Log.d(TAG, "************************************** enter create...");
        mImage = (ImageView) findViewById(R.id.camera_image);
        //1
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == CAMERA_PIC_REQUEST) {
                //2
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                mImage.setImageBitmap(thumbnail);
                //3
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                //4
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + timeStamp + "image.jpg");
                System.out.println("timeStamp on pic = " + timeStamp);

                //File file = new File("/storage/sdcard0/Download" + File.separator + "image.jpg");


                Log.d(TAG, "output filename: " + file.getPath());
                try {
                    file.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file);
                    //5
                    fo.write(bytes.toByteArray());
                    fo.close();

                    Log.d(TAG, "save succ");
                    galleryAddPic();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


}
