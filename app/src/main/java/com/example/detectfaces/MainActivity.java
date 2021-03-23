package com.example.detectfaces;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

//import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.util.IOUtils;
import com.amplifyframework.AmplifyException;
//import com.amplifyframework.auth.AuthUserAttributeKey;
//import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

import com.amazonaws.services.rekognition.AmazonRekognition;
//import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
//import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.AgeRange;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.AmplifyConfiguration;
import com.amplifyframework.predictions.aws.AWSPredictionsPlugin;
import com.amplifyframework.predictions.models.LabelType;
import com.amplifyframework.predictions.result.IdentifyLabelsResult;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

//import com.amazonaws.services.rekognition.AmazonRekognition;
//import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
//import com.amazonaws.services.rekognition.model.Image;
//import com.amazonaws.services.rekognition.model.S3Object;
//import com.amazonaws.services.rekognition.model.AgeRange;
//import com.amazonaws.services.rekognition.model.Attribute;
//import com.amazonaws.services.rekognition.model.DetectFacesRequest;
//import com.amazonaws.services.rekognition.model.DetectFacesResult;
//import com.amazonaws.services.rekognition.model.FaceDetail;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.app.Service.START_STICKY;


//import software.amazon.awssdk.core.SdkBytes;
//import software.amazon.awssdk.services.rekognition.RekognitionClient;
//import software.amazon.awssdk.services.rekognition.model.DetectFacesResponse;
//import software.amazon.awssdk.services.rekognition.model.RekognitionException;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Faces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//        } catch (AmplifyException e) {
//            e.printStackTrace();
//        }
//        try {
//            Amplify.configure(getApplicationContext());
//        } catch (AmplifyException e) {
//            e.printStackTrace();
//        }

         TextView test=(TextView) findViewById(R.id.textView);

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());//without credential log in
            Amplify.addPlugin(new AWSPredictionsPlugin());//rekognition translate polly high level client

//            AmplifyConfiguration config = AmplifyConfiguration.builder(getApplicationContext())
//                    .devMenuEnabled(false)
//                    .build();
//            Amplify.configure(config, getApplicationContext());
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        //create DetectFace folder in android for picture
        String DetectFacedir = "/DetectFace/";
        File PrimaryStorage = Environment.getExternalStorageDirectory();
        File PICDir = new File("/storage/emulated/0/DetectFace/");
        if (!PICDir.exists()) {
            Log.e("DIR", "MKDIRVAS");
            PICDir.mkdir();
        }
        /*
        File ReadyPath =new File("/storage/emulated/0/DetectFace/"+"Ready.txt");
        try {
            String deleteCmd = "rm -r " + ReadyPath;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(deleteCmd);
            Log.i("test", "delete CMD");

        } catch (FileNotFoundException e) {
            Log.e("NOTFOUND", "file notfound");
        } catch (IOException e) {
            Log.e("IOERROR", "some IO error");
        }

         */
        /*
        File test2 = new File("/storage/emulated/0/DetectFace/" );

        if(!test2.exists()) {
            test2.mkdir();
        }

         */
        Task task = new Task();

        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(0);
        executor.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);


//        s3 bucket create====================================================================
//        try {
//            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }
//        s3 bucket create end================================================================


//        download===================================================================
//        Amplify.Storage.downloadFile(
//                "ExampleKey",
//                new File(getApplicationContext().getFilesDir() + "/download.txt"),
//                StorageDownloadFileOptions.defaultInstance(),
//                progress -> Log.i("MyAmplifyApp", "Fraction completed: " + progress.getFractionCompleted()),
//                result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName()),
//                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
//        );
//        download end===============================================================


        //uploadFile();


        //detectface();


//        sign in================================================
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
//        Amplify.Auth.signIn(
//                "homekit1234",
//                "homekit1234",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        sign in end================================================
    }

    public class detectBinder extends Binder {
        public MainActivity getService() {
            return MainActivity.this;
        }
    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        //DriveServiceHelper mDriveServiceHelper = (DriveServiceHelper) intent.getExtras().get("test");
        return START_STICKY;
    }
    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class Task implements Runnable {
        public void run() {
            Log.i("test","run started");
            File PrimaryStorage = Environment.getExternalStorageDirectory();
            Log.e("str", String.valueOf(PrimaryStorage));
            String Facedir = "/DetectFace/";
            String ReadyFil = "READY.txt";
            //File imageFile = new File("/storage/emulated/0/Detectface/" );
            Log.i("test","create file");
            //File imageFile = new File(System.currentTimeMillis() + ".jpg");

            /*
            if (ReadyPath.exists()) {
                Log.e("try","ReadyPath exists");
                try {
                    String deleteCmd = "rm -r " + ReadyPath;
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec(deleteCmd);

                } catch (FileNotFoundException e) {
                    Log.e("NOTFOUND", "file notfound");
                } catch (IOException e) {
                    Log.e("IOERROR", "some IO error");
                }
                try {
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap image = BitmapFactory.decodeFile(String.valueOf(imageFile), bmOptions);
                    DetectLabelsLocalFile(image);
                } catch (Exception e) {
                    Log.e("DETECT", "detect error");
                }
            }

             */
        }
    }


//    private void uploadFile() {
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "Example");
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }
//
//        Amplify.Storage.uploadFile(
//                "Example",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }


//    private void detectface() {
////     Change bucket to your S3 bucket that contains the image file.
////     Change photo to your image file.
//
//
//        try {
//            String photo = "laugh.jpeg";
//            String bucket = "homekit163121-dev/face/";
//
//            AmazonRekognition rekognitionClient = null;
//
//
//            DetectFacesRequest request = new DetectFacesRequest()
//                    .withImage(new Image()
//                            .withS3Object(new S3Object()
//                                    .withName(photo)
//                                    .withBucket(bucket)))
//                    .withAttributes(Attribute.ALL.toString());
//            //Replace Attribute.ALL with Attribute.DEFAULT to get default values.
//
//            DetectFacesResult result = rekognitionClient.detectFaces(request);
//            List<FaceDetail> faceDetails = result.getFaceDetails();
//
//            for (FaceDetail face : faceDetails) {
//                if (request.getAttributes().contains("ALL")) {
//                    AgeRange ageRange = face.getAgeRange();
//                    Log.i("The face is estimated", ageRange.getLow().toString());
//                    Log.i("and", ageRange.getHigh().toString() + "years old");
////                    System.out.println("The detected face is estimated to be between "
////                            + ageRange.getLow().toString() + " and " + ageRange.getHigh().toString()
////                            + " years old.");
//                    Log.i(TAG, "Here's the complete set of attributes:");
////                    System.out.println("Here's the complete set of attributes:");
//                } else { // non-default attributes have null values.
//                    Log.i(TAG, "Here's the default set of attributes:");
////                    System.out.println("Here's the default set of attributes:");
//                }
//
//                // ObjectMapper objectMapper = new ObjectMapper();
//                // System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(face));
//            }
//
//        } finally {
//        }
//    }

        private void DetectLabelsLocalFile(Bitmap image) throws Exception {
            String photo = "/0/DetectFace/test1.jpeg";
            Log.i("test","DetectLabelsLocalFile");

            ByteBuffer imageBytes;
            try (InputStream inputStream = new FileInputStream(new File(photo))) {
                imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
            }

            AmazonRekognition rekognitionClient = null;

            DetectLabelsRequest request = new DetectLabelsRequest()
                    .withImage(new Image()
                            .withBytes(imageBytes))
                    .withMaxLabels(10)
                    .withMinConfidence(77F);

            Amplify.Predictions.identify(
                    LabelType.LABELS,
                    image,
                    result -> LabelDataHold((IdentifyLabelsResult) result, image),//pass to LabelDataHold function
                    error -> Log.e("MyAmplifyApp", "Label detection failed", error)
                    );

            try {

                DetectLabelsResult result = rekognitionClient.detectLabels(request);
                List<Label> labels = result.getLabels();

                Log.e("Detected labels for", photo);
                System.out.println("Detected labels for " + photo);
                for (Label label : labels) {
                    Log.i(TAG, label.getName() + ": " + label.getConfidence().toString());
                    System.out.println(label.getName() + ": " + label.getConfidence().toString());
                    String labelname = label.getName();
                    String labelconfidence = label.getConfidence().toString();
                }

            } catch(Exception e) {
                Log.e("DETECT","DetectLabelsLocalFile failed");
            }
        }

        private void LabelDataHold(IdentifyLabelsResult result,Bitmap image){
            final String[] printout = new String[result.getLabels().size()];
            double[][] Xnumber = new double[result.getLabels().size()][];
            int max = result.getLabels().size();

            for (int m=0; m<max; m++){
                printout[m] = result.getLabels().get(m).getName();
                System.out.println(m);
            }

        }


}