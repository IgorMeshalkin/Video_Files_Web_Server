package com.igormeshalkin.testtask.videowebserver.fileupload;

public class UploadTracker {
    public String owner;
    public String fileName;
    public Long fileSize;
    public int progress;

    public UploadTracker(String owner, String fileName, Long fileSize) {
        this.owner = owner;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.progress = 0;
    }

    public String getOwner() {
        return owner;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadTracker)) return false;

        UploadTracker that = (UploadTracker) o;

        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        return fileSize != null ? fileSize.equals(that.fileSize) : that.fileSize == null;
    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        return result;
    }
}
