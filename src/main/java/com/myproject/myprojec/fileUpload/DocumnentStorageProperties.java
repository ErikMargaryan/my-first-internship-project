//package com.myproject.myprojec.fileUpload;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.*;
//
//@Configuration
//@ConfigurationProperties(prefix = "file")
//@Entity
//@Table(name = "bookstore_management_dockuments")
//public class DocumnentStorageProperties {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "document_id")
//    private Integer documentId;
//many to one for user
//    @Column(name = "user_id")
//    private Integer userId;
//
//    @Column(name = "file_name")
//    private String fileName;
//
//    @Column(name = "document_type")
//    private String documentType;
//
//    @Column(name = "document_format")
//    private String documentFormat;
//
//    @Column(name = "upload_dir")
//    private String uploadDir;
//
//    public Integer getDocumentId() {
//        return documentId;
//    }
//
//    public void setDocumentId(Integer documentId) {
//        this.documentId = documentId;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getDocumentType() {
//        return documentType;
//    }
//
//    public void setDocumentType(String documentType) {
//        this.documentType = documentType;
//    }
//
//    public String getDocumentFormat() {
//        return documentFormat;
//    }
//
//    public void setDocumentFormat(String documentFormat) {
//        this.documentFormat = documentFormat;
//    }
//
//    public String getUploadDir() {
//        return uploadDir;
//    }
//
//    public void setUploadDir(String uploadDir) {
//        this.uploadDir = uploadDir;
//    }
//}
