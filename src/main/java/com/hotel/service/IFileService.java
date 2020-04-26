package com.hotel.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: HotelOrder1
 * @description:
 * @author: yhh
 * @create: 2020-04-23 16:33
 **/
public interface IFileService {
    String upload(MultipartFile file, String path);
}

