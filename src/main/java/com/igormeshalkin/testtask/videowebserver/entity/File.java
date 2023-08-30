package com.igormeshalkin.testtask.videowebserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "filesize")
    private Long fileSize;

    @Column(name = "filepath")
    private String filePath;

    public File() {
    }

    public File(String userName, String fileName, Long fileSize, String filePath) {
        this.userName = userName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
