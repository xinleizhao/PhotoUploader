package hk.hku.cs.photouploader.provider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;


public class ImageFinder {

    public static String extractImageUrl(String url) throws IOException {
        String contentType = new URL(url).openConnection().getContentType();
        if (contentType != null) {
            if (contentType.startsWith("images/")) {
                return url;
            }
        }
        Document document = Jsoup.connect(url).get();
        String imageUrl = null;
        imageUrl = getImageFromcs(document);
        if (imageUrl != null) {
            return imageUrl;
        }
        return imageUrl;
    }


    private static String getImageFromcs(Document document) {
        Element container =
                document.select("*[itemscope][itemtype=http://i.cs.hku.hk/~xlzhao/ImageObject]").first();
        if (container == null) {
            return null;
        }
        Element image = container.select("img[itemprop=contentUrl]").first();
        if (image == null) {
            return null;
        }
        return image.absUrl("src");
    }
}