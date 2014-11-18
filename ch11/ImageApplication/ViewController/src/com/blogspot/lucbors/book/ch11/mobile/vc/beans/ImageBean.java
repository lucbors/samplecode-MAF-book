package com.blogspot.lucbors.book.ch11.mobile.vc.beans;




import oracle.adf.model.datacontrols.device.DeviceManager;
import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

/**
 * The PictureBean is an exact copy of the bean in the ADF Mobile Device Demo and is exposed
 * as a DataControl to gather picture settings like width, height, quality and more. You can
 * look at this class as the "memory" object that remembers user settings for producing the
 * picture and - required in thi sexample - uploading the picture.
 *
 * The getPictureSource method returns a reference to the selected album picture or camera
 * picture. The camera picture is saved in a temporary location on th emobile device.
 *
 */
public class ImageBean {
    public ImageBean() {
        super();
    }
    
    //quality decides about the image size. Initially its set to 75% 
    private int quality = 75;
    
    private int destination = DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL;
    private int source = DeviceManager.CAMERA_SOURCETYPE_CAMERA;
    private boolean allowEdit = false;
    //encoding can be set to JPG or PNG. Use JPG for smaller file sizes
    private int encoding = DeviceManager.CAMERA_ENCODINGTYPE_JPEG;
    
    
    //the initial image size is set to the available screen resolution of the
    //mobile device. This size can be changed in the AMX page from which the
    //photo is taken or image is chosen
    DeviceManager deviceManager = DeviceManagerFactory.getDeviceManager();    
    private int targetWidth = deviceManager.getScreenWidth();
    private int targetHeight= deviceManager.getScreenHeight();

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    //in this sample, desitination is not exposed to the
    //user. The change between a image URL and Image data
    //is an implementation detail and there is no benefit 
    //I see in the user selecting between the two formats
    //Maybe for large pictures its better to read them from
    //the hard drive than laod them in memory
    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    //The default image source is camrea. This can be changed by the
    //user to select an image from the gallery
    public void setSource(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }

    public boolean getAllowEdit() {
        return allowEdit;
    }

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }

    public int getEncoding() {
        return encoding;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    public int getTargetHeight() {
        return targetHeight;
    }
    
    /**
     * Method that returns the absolut image URL on the local device
     * @param result the result string of the "getPicture" Device DC method
     * @return String with the URL pointing to the image. 
     *         This URL is used in the PhotoProcessing bean (exposed as DC) to upload the image.
     */
    public String getPictureSource(String result) {
        String src = "";
        //obtains a base 64 encoded string of the image
        if( getDestination() == DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL ) {
            src = "data:image/gif;base64,";
        }
        //else returns an absolute file reference to the picture. This sample 
        //uses CAMERA_DESTINATIONTYPE_FILE_URI and thus obtains a picture 
        //reference
        src = src + result;
        return src;
    }
}