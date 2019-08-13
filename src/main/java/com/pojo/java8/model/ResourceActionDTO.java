package com.pojo.java8.model;

/**
 * Created by sunitc on 8/2/17.
 */
public class ResourceActionDTO {

    private String object;
    private String permission;
    private long resourceActionId;
    private String packageName;
    private long bitwise;
    private String actionId;

    public ResourceActionDTO(String object, String permission, long resourceActionId, String packageName, long bitwise, String actionId) {
        this.object = object;
        this.permission = permission;
        this.resourceActionId = resourceActionId;
        this.packageName = packageName;
        this.bitwise = bitwise;
        this.actionId = actionId;
    }

    public String getObject() {
        return object;
    }

    public String getPermission() {
        return permission;
    }

    public long getResourceActionId() {
        return resourceActionId;
    }

    public String getPackageName() {
        return packageName;
    }

    public long getBitwise() {
        return bitwise;
    }

    public String getActionId() {
        return actionId;
    }

    @Override
    public String toString() {
        return "ResourceActionDTO{" +
                "object='" + object + '\'' +
                ", permission='" + permission + '\'' +
                ", resourceActionId=" + resourceActionId +
                ", packageName='" + packageName + '\'' +
                ", bitwise=" + bitwise +
                ", actionId='" + actionId + '\'' +
                '}';
    }
}
