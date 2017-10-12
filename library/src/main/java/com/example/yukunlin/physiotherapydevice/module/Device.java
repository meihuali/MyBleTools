package com.example.yukunlin.physiotherapydevice.module;





public class Device {
    private String name;
    private String macAddress;
    private String serviceUuid;
    private String characteristicUuid;
    private String content;
    private String id; // uuid
    private String machineId;
    private boolean online = true;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServiceUuid() {
        return serviceUuid;
    }

    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

    public String getCharacteristicUuid() {
        return characteristicUuid;
    }

    public void setCharacteristicUuid(String characteristicUuid) {
        this.characteristicUuid = characteristicUuid;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", serviceUuid='" + serviceUuid + '\'' +
                ", characteristicUuid='" + characteristicUuid + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
