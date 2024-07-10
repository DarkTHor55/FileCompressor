package com.VideoCompressor.Repository;

import com.VideoCompressor.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByFileName(String fileName);
}
