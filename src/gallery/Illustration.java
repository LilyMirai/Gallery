package gallery;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author hoshi
 */
public class Illustration {
    
    private int Integer;
    private String name;
    private List<String> tags;
    private List<String> links;
    private List<String> linkHost;
    private  Boolean isPublished;
    private String directory;
    public File image;
    public int count;
    
    OutputStreamWriter writer;

    public Illustration(String name, List<String> tags, String directory) {
        this.name = name;
        this.tags = tags;
        this.directory = directory;
    }

    public Illustration(int Integer, String name, List<String> tags, List<String> links, List<String> linkHost, Boolean isPublished, String directory) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new OutputStreamWriter(new FileOutputStream("count.txt"), "UTF-8");
        this.Integer = Integer;
        this.name = name;
        this.tags = tags;
        this.links = links;
        this.linkHost = linkHost;
        this.isPublished = isPublished;
        this.directory = directory;
    }

    public int getInteger() {
        return Integer;
    }

    public void setInteger(int Integer) {
        this.Integer = Integer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getLinkHost() {
        return linkHost;
    }

    public void setLinkHost(List<String> linkHost) {
        this.linkHost = linkHost;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }
    
    
    
}
