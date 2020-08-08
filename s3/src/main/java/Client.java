import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author Rohit Agarwal on 08/08/20 4:48 pm
 * @copyright www.javamultiplex.com
 */
public class Client {

    public static S3Client create(){
        return S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create())
                .region(Constants.region)
                .build();
    }

}
