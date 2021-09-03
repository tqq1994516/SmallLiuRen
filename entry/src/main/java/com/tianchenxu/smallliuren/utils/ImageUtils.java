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

import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    public static void setImage(ComponentProvider componentProvider, int stepNum, int componentId, int type, Context context) {
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
            componentProvider.setImagePixelMap(componentId, pixelMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pixelMap.release();
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
