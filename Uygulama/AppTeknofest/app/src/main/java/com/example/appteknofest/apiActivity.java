package com.example.appteknofest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.FaceDetector;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.ColorInfo;
import com.google.api.services.vision.v1.model.DominantColorsAnnotation;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.ImageProperties;
import com.google.api.services.vision.v1.model.SafeSearchAnnotation;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class apiActivity extends AppCompatActivity {

    private ImageView textRecognitionIv;
    private ImageView labelDetectionIv;
    private ImageView detectLogoIv;
    private ImageView detectLandmarkIv;
    private ImageView imagePropertiesIv;
    private ImageView detectExplicitContentIv;
    private ImageView detectFaceIv;
    private TextToSpeech mTTS;

    private ActivityResultLauncher<String> permissionLauncher;

    private Bitmap bitmap;
    Feature feature;
    public String api;
    private String[] visionAPI = new String[]{"LANDMARK_DETECTION", "LOGO_DETECTION", "SAFE_SEARCH_DETECTION", "IMAGE_PROPERTIES", "LABEL_DETECTION","TEXT_DETECTION","FACE_DETECTION"};

    private static final String CLOUD_VISION_API_KEY = "AIzaSyDzDtS2FC5wvNMDm3NFPy5lEPgK2-RDamk";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        textRecognitionIv = findViewById(R.id.writingDiagnosticIv);
        labelDetectionIv = findViewById(R.id.objectDiagnosticIv);
        detectLogoIv = findViewById(R.id.detectLogoIv);
        detectLandmarkIv = findViewById(R.id.detectLandmarkIv);
        imagePropertiesIv = findViewById(R.id.imagePropertiesIv);
        detectExplicitContentIv = findViewById(R.id.detectExplicitContentIv);
        detectFaceIv = findViewById(R.id.faceDetectIv);


        mTTS = new TextToSpeech(this,i->{
           if (i==TextToSpeech.SUCCESS){
               int result = mTTS.setLanguage(Locale.ENGLISH);

               if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                   System.out.println("TTS language not supported");
               }
           }else{
               System.out.println("Initialization failed");
           }
        });

        feature = new Feature();

        registerLauncher();

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            registerLauncher();
        }else{
            getLastPicture();
        }

        textRecognitionIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[5];
                feature.setType(api);
                //feature.setMaxResults(10);

                callCloudVision(bitmap,feature);

            }
        });

        labelDetectionIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[4];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);

            }
        });

        detectLogoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[1];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);

            }
        });

        detectLandmarkIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[0];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);
            }
        });

        imagePropertiesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[3];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);

            }
        });

        detectExplicitContentIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[2];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);

            }
        });

        detectFaceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                api = visionAPI[6];
                feature.setType(api);
                feature.setMaxResults(10);

                callCloudVision(bitmap,feature);
            }
        });


    }

    private void registerLauncher(){
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission()
                , new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result){
                            getLastPicture();
                        }
                    }
                });
    }

    private void getLastPicture(){
        // Find the last picture
        String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE
        };
        final Cursor cursor = getApplicationContext().getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                        null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

        // adjust bitmap for using in callCloudVision method
        if (cursor.moveToFirst()) {
            String imageLocation = cursor.getString(1);
            File imageFile = new File(imageLocation);
            if (imageFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imageLocation);
                System.out.println("bitmap  : " + bitmap);
            }
        }
    }


    private void speak(String result){
        mTTS.speak(result,TextToSpeech.QUEUE_FLUSH,null);
    }




    private void callCloudVision(final Bitmap bitmap, final Feature feature) {
        final List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);

        final List<AnnotateImageRequest> annotateImageRequests = new ArrayList<>();

        //CREATE REQUEST for performing vision tasks over a user-provided image,with user-requested feature


        AnnotateImageRequest annotateImageReq = new AnnotateImageRequest();
        annotateImageReq.setFeatures(featureList);
        annotateImageReq.setImage(getImageEncodeImage(bitmap));
        annotateImageRequests.add(annotateImageReq);


        //process the image
        //You need to interact with the vision api.Start by creating a HttpTransport and VisionRequestInitializer
        // initializer that contains your Api key

        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {

                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    VisionRequestInitializer requestInitializer = new VisionRequestInitializer(CLOUD_VISION_API_KEY);

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    //
                    builder.setApplicationName("AppTeknofest");
                    //
                    builder.setVisionRequestInitializer(requestInitializer);

                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(annotateImageRequests);

                    Vision.Images.Annotate annotateRequest = vision.images().annotate(batchAnnotateImagesRequest);
                    annotateRequest.setDisableGZipContent(true);
                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToString(response);
                } catch (GoogleJsonResponseException e) {
                    System.out.println("failed to make API request because " + e.getContent());
                } catch (IOException e) {
                    System.out.println("failed to make API request because of other IOException " + e.getMessage());
                }
                return "Cloud Vision API request failed. Check logs for details.";
            }

            protected void onPostExecute(String result) {
                System.out.println("result " + result);
                speak(result);
            }
        }.execute();
    }

    //The Vision Api cannot use Bitmap objects directly.It expects a Base64-encoded string of
    //compressed image data.
    @NonNull
    private Image getImageEncodeImage(Bitmap bitmap) {
        Image base64EncodedImage = new Image();
        // Convert the bitmap to a JPEG
        // Just in case it's a format that Android understands but Cloud Vision
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        // Base64 encode the JPEG
        base64EncodedImage.encodeContent(imageBytes);
        return base64EncodedImage;
    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {

        AnnotateImageResponse imageResponses = response.getResponses().get(0);

        List<EntityAnnotation> entityAnnotations;
        List<EntityAnnotation> entityAnnotationForText;



        String message = "";
        switch (api) {
            case "LANDMARK_DETECTION":
                entityAnnotations = imageResponses.getLandmarkAnnotations();
                message = formatAnnotation(entityAnnotations);
                break;
            case "LOGO_DETECTION":
                entityAnnotations = imageResponses.getLogoAnnotations();
                message = formatAnnotation(entityAnnotations);
                break;
            case "SAFE_SEARCH_DETECTION":
                SafeSearchAnnotation annotation = imageResponses.getSafeSearchAnnotation();
                message = getImageAnnotation(annotation);
                break;
            case "IMAGE_PROPERTIES":
                ImageProperties imageProperties = imageResponses.getImagePropertiesAnnotation();
                message = getImageProperty(imageProperties);
                break;
            case "LABEL_DETECTION":
                entityAnnotations = imageResponses.getLabelAnnotations();
                message = formatAnnotation(entityAnnotations);
                break;

            case "TEXT_DETECTION":
                entityAnnotationForText = imageResponses.getTextAnnotations();
                message = formatAnnotationForText(entityAnnotationForText);
                break;

            case "FACE_DETECTION":
                List<FaceAnnotation> faceAnnotation = imageResponses.getFaceAnnotations();
                message = getFaceAnnotation(faceAnnotation);
        }
        return message;
    }

    private String getFaceAnnotation(List<FaceAnnotation> faceAnnotation) {
            return String.format("anger: %s%njoy: %s%nsurprise: %s%nposition: %s",
                    faceAnnotation.get(0).getAngerLikelihood(),
                    faceAnnotation.get(0).getJoyLikelihood(),
                    faceAnnotation.get(0).getSurpriseLikelihood(),
                    faceAnnotation.get(0).getBoundingPoly());
    }


    private String getImageAnnotation(SafeSearchAnnotation annotation) {
        return String.format("adult: %s\nmedical: %s\nspoofed: %s\nviolence: %s\n",
                annotation.getAdult(),
                annotation.getMedical(),
                annotation.getSpoof(),
                annotation.getViolence());
    }

    private String getImageProperty(ImageProperties imageProperties) {
        String message = "";
        DominantColorsAnnotation colors = imageProperties.getDominantColors();
        for (ColorInfo color : colors.getColors()) {
            message = message + "" + color.getPixelFraction() + " - " + color.getColor().getRed() + " - " + color.getColor().getGreen() + " - " + color.getColor().getBlue();
            message = message + "\n";
        }
        return message;
    }

    private String formatAnnotation(List<EntityAnnotation> entityAnnotation) {
        String message = "";

        if (entityAnnotation != null) {
            for (EntityAnnotation entity : entityAnnotation) {
                message = message + "    " + entity.getDescription() + " " + entity.getScore();
                message += "\n";
            }
        } else {
            message = "Nothing Found";
        }
        return message;
    }

    private String formatAnnotationForText(List<EntityAnnotation> entityAnnotation){
        String message = "";

        if (entityAnnotation != null) {

            for (EntityAnnotation entity : entityAnnotation) {
                message = entity.getDescription() ;
            }
        }
        return message;
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}