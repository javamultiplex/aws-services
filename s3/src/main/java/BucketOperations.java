import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

/**
 * @author Rohit Agarwal on 08/08/20 3:09 pm
 * @copyright www.javamultiplex.com
 */
public class BucketOperations {

    private final S3Client s3Client;

    public BucketOperations(){
        s3Client= Client.create();

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
                        .locationConstraint(Constants.region.id())
                        .build())
                .build();
        return s3Client.createBucket(createBucketRequest);

    }

    public ListBucketsResponse listBuckets(){
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        return s3Client.listBuckets(listBucketsRequest);
    }

    public DeleteBucketResponse deleteEmptyBucket(String bucketName){
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();
        return s3Client.deleteBucket(deleteBucketRequest);
    }
}