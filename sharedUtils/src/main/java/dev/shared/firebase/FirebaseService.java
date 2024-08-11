package dev.shared.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import shared.CustomLoggerFactory;

public class FirebaseService {

    private static final Logger logger = CustomLoggerFactory.getLogger(FirebaseService.class);

    public void initializeFirebaseApp(String tokenPath) throws IOException {
        FileInputStream serviceAccount = new FileInputStream(tokenPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public void createFirebaseUser(String email, String password, String userType, String tokenPath) throws IOException, FirebaseAuthException {
        initializeFirebaseApp(tokenPath);

        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(userType);  // Assuming 'userType' is used as the display name

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        logger.info("Successfully created new user: " + userRecord.getUid());
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            logger.info("Usage: java app.FirebaseService <email> <password> <user_type> <token_path>");
            return;
        }

        String email = args[0];
        String password = args[1];
        String userType = args[2];
        String tokenPath = args[3];

        FirebaseService service = new FirebaseService();
        try {
            service.createFirebaseUser(email, password, userType, tokenPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }
}