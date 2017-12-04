package javad2xxdemo.ftdi.com.cameratest;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView photo;  

    Drawable pt;
    Bitmap btBitmap;

    NotificationCompat.Builder notification;
    private static final int uniqueId = 45612;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

//        Button takePhoto = findViewById(R.id.takePhoto);
//        photo = findViewById(R.id.photo);
//
//        //check the camera
//        if(!hasCamera()){
//            takePhoto.setEnabled(false);
//        }
//
//        pt = getResources().getDrawable(R.drawable.pt);
//        btBitmap = ((BitmapDrawable) pt).getBitmap();
//        Bitmap newPhoto = invertImage(btBitmap);
//        Drawable drawable =new BitmapDrawable(newPhoto);
////        photo.setImageBitmap(newPhoto);
//
//        Drawable[] layers = new Drawable[2];
//        layers[1] = getResources().getDrawable(R.drawable.bg);
//        layers[0] = drawable;
//
//        LayerDrawable layerDrawable = new LayerDrawable(layers);
//        photo.setImageDrawable(layerDrawable);
//
//        MediaStore.Images.Media.insertImage(getContentResolver(), newPhoto, "title", "description");

    }

    private Bitmap invertImage(Bitmap btBitmap) {
        Bitmap finalImage = Bitmap.createBitmap(btBitmap.getWidth(), btBitmap.getHeight(), btBitmap.getConfig());
        int A,R,G,B;
        int pixelColor;

        for(int y=0; y<btBitmap.getHeight(); y++){
            for(int x=0; x<btBitmap.getWidth(); x++){
                pixelColor = btBitmap.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 255-Color.red(pixelColor);
                G = 255-Color.green(pixelColor);
                B = 255-Color.blue(pixelColor);
                finalImage.setPixel(x, y, Color.argb(A,R,G,B));
            }
        }
        return finalImage;
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void takePhoto(View view) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("this is the ticket");
        notification.setContentTitle("this is the title");
        notification.setContentText("this is content text");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueId, notification.build());

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
//            Bundle extras = data.getExtras();
//            Bitmap photoMap = (Bitmap) extras.get("data");
//            photo.setImageBitmap(photoMap);
//        }
//    }
}
