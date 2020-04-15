package edu.byu.cs.tweeter.server.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import edu.byu.cs.tweeter.server.dao.AuthsDAO;

public class ServiceImpl {
    public void validateToken(String token) {
        long diff = 3600000L;   // Tokens valid for one hour

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("401");
        }

        AuthsDAO authsDAO = new AuthsDAO();
        String resp = authsDAO.validateToken(token);
        if (resp == null || resp.isEmpty()) {
            throw new RuntimeException("401");
        }

        long timestamp = Long.parseLong(resp);
        long curr_time = new Timestamp(System.currentTimeMillis()).getTime();
        if (curr_time - timestamp > diff) {
            throw new RuntimeException("401");
        }
    }

    public String generateHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("501");
        }
    }

    public String uploadImage(String imageURL, String alias) throws IOException {
        URL url = new URL(imageURL);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] image = out.toByteArray();

        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .build();
        String file_name = alias + ".jpg";
        String bucket_name = "cs340-tweeter/profile-pics";

        InputStream stream = new ByteArrayInputStream(image);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(image.length);
        meta.setContentType("image/png");

        s3.putObject(new PutObjectRequest(bucket_name, file_name, stream, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        stream.close();

        return "https://cs340-tweeter.s3-us-west-2.amazonaws.com/profile-pics/" + file_name;
    }
}
