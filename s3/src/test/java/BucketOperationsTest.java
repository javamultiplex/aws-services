import org.junit.jupiter.api.*;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteBucketResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Rohit Agarwal on 08/08/20 3:58 pm
 * @copyright www.javamultiplex.com
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BucketOperationsTest {
    private BucketOperations bucketOperations;

    @BeforeEach
    public void testUp(){
        bucketOperations=new BucketOperations();
    }

    @Test
    @Order(1)
    public void shouldCreateBucket(){
        CreateBucketResponse response = bucketOperations.createBucket("bucket-javamultiplex-12");
        assertNotNull(response);
        assertEquals("http://bucket-javamultiplex-12.s3.amazonaws.com/",response.location());
    }

    @Test
    @Order(2)
    public void shouldListBuckets(){
        ListBucketsResponse listBucketsResponse = bucketOperations.listBuckets();
        assertNotNull(listBucketsResponse);
        listBucketsResponse.buckets().forEach(bucket -> System.out.println(bucket.name()));
    }

    @Test
    @Order(3)
    public void shouldDeleteBucket(){
        DeleteBucketResponse deleteBucketResponse = bucketOperations.deleteBucket("bucket-javamultiplex-12");
        assertNotNull(deleteBucketResponse);
    }

}
