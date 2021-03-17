package com.example.detectfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

//import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
//import com.amplifyframework.AmplifyException;
//import com.amplifyframework.auth.AuthUserAttributeKey;
//import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
//import com.amplifyframework.auth.options.AuthSignUpOptions;
//import com.amplifyframework.core.Amplify;
//import com.amplifyframework.datastore.AWSDataStorePlugin;
//import com.amplifyframework.predictions.models.EntityDetails;
//import com.amplifyframework.predictions.models.IdentifyActionType;
//import com.amplifyframework.predictions.result.IdentifyEntitiesResult;

//import com.amplifyframework.AmplifyException;
//import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
//import com.amplifyframework.core.Amplify;       //s3
//import com.amplifyframework.storage.options.StorageDownloadFileOptions;
//import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.TextDetection;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;     //s3

//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.rekognition.RekognitionClient;
//import software.amazon.awssdk.services.rekognition.model.RekognitionException;
//import software.amazon.awssdk.services.rekognition.model.DetectFacesRequest;
//import software.amazon.awssdk.services.rekognition.model.DetectFacesResponse;
//import software.amazon.awssdk.services.rekognition.model.Image;
//import software.amazon.awssdk.services.rekognition.model.Attribute;
//import software.amazon.awssdk.services.rekognition.model.FaceDetail;
//import software.amazon.awssdk.services.rekognition.model.AgeRange;
//import software.amazon.awssdk.core.SdkBytes;
//import software.amazon.awssdk.services.s3.S3Client;

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
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

//import software.amazon.awssdk.core.SdkBytes;
//import software.amazon.awssdk.services.rekognition.RekognitionClient;
//import software.amazon.awssdk.services.rekognition.model.DetectFacesResponse;
//import software.amazon.awssdk.services.rekognition.model.RekognitionException;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Faces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        detectface();


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


//    private void uploadFile() {
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");
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
//                "ExampleKey",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }



//    public static void detectFacesinImage(RekognitionClient rekClient, String sourceImage ) {
//
//        try {
//            InputStream sourceStream = new FileInputStream(new File(sourceImage));
//            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);
//
//            // Create an Image object for the source image
//            Image souImage = Image.builder()
//                    .bytes(sourceBytes)
//                    .build();
//
//            DetectFacesRequest facesRequest = DetectFacesRequest.builder()
//                    .attributes(Attribute.ALL)
//                    .image(souImage)
//                    .build();
//
//            DetectFacesResponse facesResponse = rekClient.detectFaces(facesRequest);
//            List<FaceDetail> faceDetails = facesResponse.faceDetails();
//
//            for (FaceDetail face : faceDetails) {
//                AgeRange ageRange = face.ageRange();
//                System.out.println("The detected face is estimated to be between "
//                        + ageRange.low().toString() + " and " + ageRange.high().toString()
//                        + " years old.");
//
//                System.out.println("There is a smile : "+face.smile().value().toString());
//            }
//
//        } catch (RekognitionException | FileNotFoundException e) {
//            System.out.println(e.getMessage());
//            System.exit(1);
//        }
//    }

//    public static void main(String[] args) {
//
//        final String USAGE = "\n" +
//                "Usage: " +
//                "DetectFaces <sourceImage>\n\n" +
//                "Where:\n" +
//                "sourceImage - the path to the image (/Users/hongzhenqi/Desktop/WUSA/AnalysisTest/laugh.jpeg). \n\n";
//
//        if (args.length != 1) {
//            System.out.println(USAGE);
//            System.exit(1);
//        }
//
//        String sourceImage = args[0];
//        Region region = Region.US_EAST_1;
//        RekognitionClient rekClient = RekognitionClient.builder()
//                .region(region)
//                .build();
//
//        detectFacesinImage(rekClient, sourceImage );
//        rekClient.close();
//    }
//
//    public void detectEntities(Bitmap image) {
//        Amplify.Predictions.identify(
//                IdentifyActionType.DETECT_ENTITIES,
//                image,
//                result -> {
//                    IdentifyEntitiesResult identifyResult = (IdentifyEntitiesResult) result;
//                    EntityDetails metadata = identifyResult.getEntities().get(0);
//                    Log.i("MyAmplifyApp", metadata.getBox().toShortString());
//                },
//                error -> Log.e("MyAmplifyApp", "Entity detection failed", error)
//        );
//    }

    private void detectface()  {
        // Change bucket to your S3 bucket that contains the image file.
        // Change photo to your image file.


        try {
            String photo = "laugh.jpeg";
            String bucket = "homekit163121-dev/face/";

            AmazonRekognition rekognitionClient = null;





            DetectFacesRequest request = new DetectFacesRequest()
                    .withImage(new Image()
                            .withS3Object(new S3Object()
                                    .withName(photo)
                                    .withBucket(bucket)))
                    .withAttributes(Attribute.ALL.toString());
            //Replace Attribute.ALL with Attribute.DEFAULT to get default values.

            DetectFacesResult result = rekognitionClient.detectFaces(request);
            List<FaceDetail> faceDetails = result.getFaceDetails();

            for (FaceDetail face : faceDetails) {
                if (request.getAttributes().contains("ALL")) {
                    AgeRange ageRange = face.getAgeRange();
                    Log.i("The face is estimated", ageRange.getLow().toString());
                    Log.i("and", ageRange.getHigh().toString() + "years old");
//                    System.out.println("The detected face is estimated to be between "
//                            + ageRange.getLow().toString() + " and " + ageRange.getHigh().toString()
//                            + " years old.");
                    Log.i(TAG, "Here's the complete set of attributes:");
//                    System.out.println("Here's the complete set of attributes:");
                } else { // non-default attributes have null values.
                    Log.i(TAG, "Here's the default set of attributes:");
//                    System.out.println("Here's the default set of attributes:");
                }

                // ObjectMapper objectMapper = new ObjectMapper();
                // System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(face));
            }

        } finally {
        }


//        DetectTextRequest request = new DetectTextRequest()
//                .withImage(new Image()
//                        .withS3Object(new S3Object()
//                                .withName(photo)
//                                .withBucket(bucket)));
//
//        try {
//            DetectTextResult result = rekognitionClient.detectText(request);
//            List<TextDetection> textDetections = result.getTextDetections();
//
//            System.out.println("Detected lines and words for " + photo);
//            for (TextDetection text: textDetections) {
//
//                System.out.println("Detected: " + text.getDetectedText());
//                System.out.println("Confidence: " + text.getConfidence().toString());
//                System.out.println("Id : " + text.getId());
//                System.out.println("Parent Id: " + text.getParentId());
//                System.out.println("Type: " + text.getType());
//                System.out.println();
//            }
//        } catch(AmazonRekognitionException e) {
//            e.printStackTrace();
//        }
    }
}