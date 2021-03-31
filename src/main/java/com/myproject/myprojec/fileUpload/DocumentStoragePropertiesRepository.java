package com.myproject.myprojec.fileUpload;

import com.myproject.myprojec.fileUpload.DocumnentStorageProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentStoragePropertiesRepository extends JpaRepository<DocumnentStorageProperties, Integer> {

    @Query("Select a from DocumnentStorageProperties a where user_id = ?1 and document_type = ?2")
    DocumnentStorageProperties checkDocumentByUserId(Integer userId, String docType);

    @Query("Select fileName from DocumnentStorageProperties a where user_id = ?1 and document_type = ?2")
    String getUploadDocumnetPath(Integer userId, String docType);
}
