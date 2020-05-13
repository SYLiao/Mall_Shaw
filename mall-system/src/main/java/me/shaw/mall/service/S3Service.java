package me.shaw.mall.service;

import me.shaw.mall.dto.S3CallbackResult;
import me.shaw.mall.dto.S3PolicyResult;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.servlet.http.HttpServletRequest;

public interface S3Service {

    ResponseInputStream<GetObjectResponse> downloadFile(String keyName) throws Exception;

    PutObjectResponse uploadFile(MultipartFile file) throws Exception;
}
