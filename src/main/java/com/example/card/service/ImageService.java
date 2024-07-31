//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class ImageService {
//    private final AmazonS3 s3client;
//    private final String bucketName = "your-bucket-name";
//
//    @Autowired
//    public ImageService(AmazonS3 s3client) {
//        this.s3client = s3client;
//    }
//
//    public String uploadImage(MultipartFile file) {
//        String fileName = generateFileName(file);
//        try {
//            s3client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), new ObjectMetadata()));
//            return s3client.getUrl(bucketName, fileName).toString();
//        } catch (IOException e) {
//            throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
//        }
//    }
//
//    private String generateFileName(MultipartFile file) {
//        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
//    }
//}
