package com.structurizr.util;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;

/**
 * Some utility methods for dealing with images.
 */
public class ImageUtils {

    /**
     * Gets the content type of the specified file representing an image.
     *
     * @param   file            a File pointing to an image
     * @return  a content type (e.g. "image/png")
     * @throws IOException      if there is an error reading the file
     */
    public static String getContentType(@Nonnull File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("A file must be specified.");
        } else if (!file.exists()) {
            throw new IllegalArgumentException(file.getCanonicalPath() + " does not exist.");
        } else if (!file.isFile()) {
            throw new IllegalArgumentException(file.getCanonicalPath() + " is not a file.");
        }

        String contentType = URLConnection.guessContentTypeFromName(file.getName());
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException(file.getCanonicalPath() + " is not a supported image file.");
        }

        return contentType;
    }

    /**
     * Gets the content of an image as a Base64 encoded string.
     *
     * @param   file            a File pointing to an image
     * @return  a Base64 encoded version of that image
     * @throws IOException      if there is an error reading the file
     */
    public static String getImageAsBase64(@Nonnull File file) throws IOException {
        String contentType = getContentType(file);
        BufferedImage bufferedImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, contentType.split("/")[1], bos);
        byte[] imageBytes = bos.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    /**
     * Gets the content of an image as a data URI; e.g. "data:image/png;base64,iVBORw0KGgoAA..."
     *
     * @param   file        a File pointing to an image
     * @return              a data URI
     * @throws IOException  if there is an error reading the file
     */
    public static String getImageAsDataUri(File file) throws IOException {
        String contentType = getContentType(file);
        String base64Content = getImageAsBase64(file);

        return "data:" + contentType + ";base64," + base64Content;
    }

    public static void validateImage(String imageDescriptor) {
        if (StringUtils.isNullOrEmpty(imageDescriptor)) {
            return;
        }

        imageDescriptor = imageDescriptor.trim();

        if (Url.isUrl(imageDescriptor)) {
            // all good
            return;
        }

        if (imageDescriptor.toLowerCase().endsWith(".png") || imageDescriptor.toLowerCase().endsWith(".jpg") || imageDescriptor.toLowerCase().endsWith(".jpeg") || imageDescriptor.toLowerCase().endsWith(".gif")) {
            // it's just a filename
            return;
        }

        if (imageDescriptor.startsWith("data:image")) {
            if (ImageUtils.isSupportedDataUri(imageDescriptor)) {
                // it's a PNG/JPG data URI
                return;
            } else {
                // it's a data URI, but not supported
                throw new IllegalArgumentException("Only PNG and JPG data URIs are supported: " + imageDescriptor);
            }
        }

        throw new IllegalArgumentException("Expected a URL or data URI");
    }

    public static boolean isSupportedDataUri(String uri) {
        return uri.startsWith("data:image/png;base64,") || uri.startsWith("data:image/jpeg;base64,");
    }

}