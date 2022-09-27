package com.cs203g1t2.springjwt.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FullItemResponse extends ItemResponse {

    private MultipartFile file;
}
