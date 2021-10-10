package com.tianchenxu.smallliuren.utils;

import com.tianchenxu.smallliuren.ResourceTable;
import ohos.agp.components.ComponentProvider;
import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.utils.zson.ZSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final String ROOTDIR = "/common/";


    public static void setImage(ZSONObject zsonObject, int stepNum, String componentId, int type) {
        if (type==1) {
            switch (stepNum) {
                case 0:
                    zsonObject.put(componentId, ROOTDIR+"kongwang.png");
                    break;
                case 1:
                    zsonObject.put(componentId, ROOTDIR+"daan.png");
                    break;
                case 2:
                    zsonObject.put(componentId, ROOTDIR+"liulian.png");
                    break;
                case 3:
                    zsonObject.put(componentId, ROOTDIR+"suxi.png");
                    break;
                case 4:
                    zsonObject.put(componentId, ROOTDIR+"chikou.png");
                    break;
                case 5:
                    zsonObject.put(componentId, ROOTDIR+"xiaoji.png");
                    break;
            }
        } else if (type == 2) {
            switch (stepNum) {
                case 1:
                    zsonObject.put(componentId, ROOTDIR+"yang.png");
                    break;
                case 2:
                    zsonObject.put(componentId, ROOTDIR+"yin.png");
                    break;
            }
        } else if (type == 0) {
            switch (stepNum) {
                case 99 :
                    if (componentId.equals("base")) {
                        zsonObject.put(componentId, ROOTDIR+componentId+".png");
                    } else {
                        zsonObject.put(componentId+"_label", ROOTDIR+componentId+".png");
                    }
                    break;
            }
        }
    }

    public static void setImage(Image image, int stepNum, Context context, int type) {
        PixelMap pixelMap = null;
        try {
            if (type==1) {
                switch (stepNum) {
                    case 0:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_kongwang, context);
                        break;
                    case 1:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_daan, context);
                        break;
                    case 2:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_liulian, context);
                        break;
                    case 3:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_suxi, context);
                        break;
                    case 4:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_chikou, context);
                        break;
                    case 5:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_xiaoji, context);
                        break;
                }
            } else if (type == 2) {
                switch (stepNum) {
                    case 1:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_yang, context);
                        break;
                    case 2:
                        pixelMap = getPixelMapFromResource(ResourceTable.Media_yin, context);
                        break;
                }
            }
            image.setPixelMap(pixelMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pixelMap.release();
        }
    }


    public static PixelMap getPixelMapFromResource(int resourceId, Context context) {
        InputStream inputStream = null;
        ImageSource imageSource = null;
        PixelMap pixelmap = null;
        try {
            inputStream = context.getResourceManager().getResource(resourceId);
            ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
            sourceOptions.formatHint = "image/png";
            imageSource = ImageSource.create(inputStream, sourceOptions);
            ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
            pixelmap = imageSource.createPixelmap(decodingOptions);
        } catch (IOException e) {
            HiLog.info(LABEL_LOG, "IOException");
        } catch (NotExistException e) {
            HiLog.info(LABEL_LOG, "NotExistException");
        } finally {
            // 释放ImageSource对象
            imageSource.release();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    HiLog.info(LABEL_LOG, "inputStream IOException");
                }
            }
        }
        return pixelmap;
    }
}
