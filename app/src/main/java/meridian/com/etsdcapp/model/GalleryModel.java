package meridian.com.etsdcapp.model;

/**
 * Created by user 1 on 08-04-2016.
 */
public class GalleryModel {
    private int albumid;

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }
    private String gallerytitl;
    private String albumname;

    public String getGallerytitl() {
        return gallerytitl;
    }

    public void setGallerytitl(String gallerytitl) {
        this.gallerytitl = gallerytitl;
    }

    private String gallerydescrptn;

    public String getGallerydescrptn() {
        return gallerydescrptn;
    }

    public void setGallerydescrptn(String gallerydescrptn) {
        this.gallerydescrptn = gallerydescrptn;
    }

    public int getAlbumid() {
        return albumid;
    }

    private String albumthmb;
    private  String galid;

    public String getGalid() {
        return galid;
    }

    public void setGalid(String galid) {
        this.galid = galid;
    }

    public String getGalimag() {
        return galimag;
    }

    public void setGalimag(String galimag) {
        this.galimag = galimag;
    }

    private  String galimag;



    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getAlbumthmb() {
        return albumthmb;
    }

    public void setAlbumthmb(String albumthmb) {
        this.albumthmb = albumthmb;
    }
}
