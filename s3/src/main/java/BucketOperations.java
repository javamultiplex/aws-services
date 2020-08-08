import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

/**
 * @author Rohit Agarwal on 08/08/20 3:09 pm
 * @copyright www.javamultiplex.com
 */
public class BucketOperations {

    private final S3Client s3Client;
    private static final Region region = Region.AP_SOUTH_1;

    public BucketOperations(){
        s3Client= S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create())
                .region(region)
                .build();

    }

    /**
     *
     * @param bucketName
     * @return
     */
    public CreateBucketResponse createBucket(String bucketName){
        CreateBucketRequest createBucketRequest=CreateBucketRequest.builder()
                .bucket(bucketName)
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(region.id())
                        .build())
                .build();
        return s3Client.createBucket(createBucketRequest);

    }

    public ListBucketsResponse listBuckets(){
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        return s3Client.listBuckets(listBucketsRequest);
    }

    public DeleteBucketResponse deleteBucket(String bucketName){
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();
        return s3Client.deleteBucket(deleteBucketRequest);
    }
}