package com.extraleaderboard.model.nadeoresponse;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "mapId",
        "name",
        "author",
        "submitter",
        "authorTime",
        "goldTime",
        "silverTime",
        "bronzeTime",
        "nbLaps",
        "valid",
        "downloadUrl",
        "thumbnailUrl",
        "uploadTimestamp",
        "updateTimestamp",
        "fileSize",
        "public",
        "favorite",
        "playable",
        "mapStyle",
        "mapType",
        "collectionName"
})
@Generated("jsonschema2pojo")
public class NadeoMapResponse extends NadeoResponse{

    @JsonProperty("uid")
    private String uid;
    @JsonProperty("mapId")
    private String mapId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("author")
    private String author;
    @JsonProperty("submitter")
    private String submitter;
    @JsonProperty("authorTime")
    private Integer authorTime;
    @JsonProperty("goldTime")
    private Integer goldTime;
    @JsonProperty("silverTime")
    private Integer silverTime;
    @JsonProperty("bronzeTime")
    private Integer bronzeTime;
    @JsonProperty("nbLaps")
    private Integer nbLaps;
    @JsonProperty("valid")
    private Boolean valid;
    @JsonProperty("downloadUrl")
    private String downloadUrl;
    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;
    @JsonProperty("uploadTimestamp")
    private Integer uploadTimestamp;
    @JsonProperty("updateTimestamp")
    private Integer updateTimestamp;
    @JsonProperty("fileSize")
    private Object fileSize;
    @JsonProperty("public")
    private Boolean _public;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("playable")
    private Boolean playable;
    @JsonProperty("mapStyle")
    private String mapStyle;
    @JsonProperty("mapType")
    private String mapType;
    @JsonProperty("collectionName")
    private String collectionName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("mapId")
    public String getMapId() {
        return mapId;
    }

    @JsonProperty("mapId")
    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("submitter")
    public String getSubmitter() {
        return submitter;
    }

    @JsonProperty("submitter")
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    @JsonProperty("authorTime")
    public Integer getAuthorTime() {
        return authorTime;
    }

    @JsonProperty("authorTime")
    public void setAuthorTime(Integer authorTime) {
        this.authorTime = authorTime;
    }

    @JsonProperty("goldTime")
    public Integer getGoldTime() {
        return goldTime;
    }

    @JsonProperty("goldTime")
    public void setGoldTime(Integer goldTime) {
        this.goldTime = goldTime;
    }

    @JsonProperty("silverTime")
    public Integer getSilverTime() {
        return silverTime;
    }

    @JsonProperty("silverTime")
    public void setSilverTime(Integer silverTime) {
        this.silverTime = silverTime;
    }

    @JsonProperty("bronzeTime")
    public Integer getBronzeTime() {
        return bronzeTime;
    }

    @JsonProperty("bronzeTime")
    public void setBronzeTime(Integer bronzeTime) {
        this.bronzeTime = bronzeTime;
    }

    @JsonProperty("nbLaps")
    public Integer getNbLaps() {
        return nbLaps;
    }

    @JsonProperty("nbLaps")
    public void setNbLaps(Integer nbLaps) {
        this.nbLaps = nbLaps;
    }

    @JsonProperty("valid")
    public Boolean getValid() {
        return valid;
    }

    @JsonProperty("valid")
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @JsonProperty("downloadUrl")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonProperty("downloadUrl")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnailUrl")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @JsonProperty("uploadTimestamp")
    public Integer getUploadTimestamp() {
        return uploadTimestamp;
    }

    @JsonProperty("uploadTimestamp")
    public void setUploadTimestamp(Integer uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }

    @JsonProperty("updateTimestamp")
    public Integer getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonProperty("updateTimestamp")
    public void setUpdateTimestamp(Integer updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @JsonProperty("fileSize")
    public Object getFileSize() {
        return fileSize;
    }

    @JsonProperty("fileSize")
    public void setFileSize(Object fileSize) {
        this.fileSize = fileSize;
    }

    @JsonProperty("public")
    public Boolean getPublic() {
        return _public;
    }

    @JsonProperty("public")
    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("playable")
    public Boolean getPlayable() {
        return playable;
    }

    @JsonProperty("playable")
    public void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    @JsonProperty("mapStyle")
    public String getMapStyle() {
        return mapStyle;
    }

    @JsonProperty("mapStyle")
    public void setMapStyle(String mapStyle) {
        this.mapStyle = mapStyle;
    }

    @JsonProperty("mapType")
    public String getMapType() {
        return mapType;
    }

    @JsonProperty("mapType")
    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    @JsonProperty("collectionName")
    public String getCollectionName() {
        return collectionName;
    }

    @JsonProperty("collectionName")
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}